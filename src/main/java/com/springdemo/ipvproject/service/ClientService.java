package com.springdemo.ipvproject.service;

import com.springdemo.ipvproject.dto.ClientDTO;
import com.springdemo.ipvproject.entity.Client;
import com.springdemo.ipvproject.entity.ModePaiement;
import com.springdemo.ipvproject.entity.Societe;
import com.springdemo.ipvproject.exception.EmailAlreadyExistsException;
import com.springdemo.ipvproject.exception.InvalidModePaiementException;
import com.springdemo.ipvproject.exception.SmsSendingException;
import com.springdemo.ipvproject.repository.ClientRepository;
import com.springdemo.ipvproject.repository.SocieteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SocieteRepository societeRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    private static final String VERIFICATION_CODE_PREFIX = "verification_code_";

    public Client register(@Valid ClientDTO clientDTO) {
        checkIfEmailAlreadyUsed(clientDTO.getEmail());
        validateModePaiement(clientDTO.getModePaiement());

        // Convertir DTO en entité Client
        Client client = convertToEntity(clientDTO);

        // Encoder le mot de passe
        encodeClientPassword(client);
        client.setVerified(false);

        Client savedClient = clientRepository.save(client);

        // Envoyer le SMS de vérification
        sendVerificationSms(client.getTelephone());

        return savedClient;
    }

    private void checkIfEmailAlreadyUsed(String email) {
        if (clientRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException(email);
        }
    }

    private void validateModePaiement(ModePaiement modePaiement) {
        if (modePaiement == null) {
            throw new InvalidModePaiementException("Le mode de paiement est obligatoire !");
        }
    }

    private void encodeClientPassword(Client client) {
        client.setMotdepasse(new BCryptPasswordEncoder(12).encode(client.getMotdepasse()));
    }

    public void sendVerificationSms(String phoneNumber) {
        Twilio.init(accountSid, authToken);
        String verificationCode = String.format("%06d", Integer.valueOf(new Random().nextInt(1000000)));
        Message message = Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioPhoneNumber),
                "Votre code de vérification est : " + verificationCode
        ).create();

        if (!message.getStatus().equals(Message.Status.QUEUED) && !message.getStatus().equals(Message.Status.DELIVERED)) {
            throw new SmsSendingException("Échec de l'envoi du SMS.");
        }

        redisTemplate.opsForValue().set(
                VERIFICATION_CODE_PREFIX + phoneNumber, verificationCode, 5, TimeUnit.MINUTES
        );
    }

    public boolean verifyCode(String phoneNumber, String code) {
        String storedCode = redisTemplate.opsForValue().get(VERIFICATION_CODE_PREFIX + phoneNumber);

        if (storedCode != null && storedCode.equals(code)) {
            Optional<Client> clientOpt = clientRepository.findByTelephone(phoneNumber);
            if (clientOpt.isPresent()) {
                Client client = clientOpt.get();
                client.setVerified(true);
                clientRepository.save(client);
            }
            redisTemplate.delete(VERIFICATION_CODE_PREFIX + phoneNumber);
            return true;
        }
        return false;
    }

    public List<Client> searchClientsBySociete(String keyword) {
        return clientRepository.searchBySociete(keyword);
    }

    // Convertir ClientDTO en Client avec conversion correcte du champ `societe`
    private Client convertToEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setEmail(clientDTO.getEmail());
        client.setMotdepasse(clientDTO.getMotdepasse()); // Encodé plus tard
        client.setNom(clientDTO.getNom());
        client.setPrenom(clientDTO.getPrenom());
        client.setAdresse(clientDTO.getAdresse());
        client.setTelephone(clientDTO.getTelephone());
        client.setCin(clientDTO.getCin());
        client.setModePaiement(clientDTO.getModePaiement());

        // Recherche de la société dans la base de données
        Societe societe = (Societe) societeRepository.findByNom(clientDTO.getSociete())
                .orElseThrow(() -> new IllegalArgumentException("Société non trouvée : " + clientDTO.getSociete()));
        client.setSociete(societe);

        return client;
    }
}
