package udacity.project1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import udacity.project1.mapper.CredentialMapper;
import udacity.project1.model.Credential;

import java.util.Base64;
import java.security.SecureRandom;

@Service
public class CredentialService {

    private final EncryptionService encryptor;
    private final CredentialMapper credentials;

    public CredentialService(CredentialMapper mapper, EncryptionService encryptionService) {
        this.credentials = mapper;
        this.encryptor = encryptionService;
    }

    public String decryptCredential(Credential credential) {
        var found = credentials.find(credential);
        return encryptor.decryptValue(found.getPassword(), found.getKey());
    }

    public List<Credential> allBy(String UID) {
        return credentials.allFrom(UID);
    }

    public void remove(Credential credential) {
        credentials.delete(credential);
    }

    public void add(Credential credential) {
        if (credential.getId() == null) {
            var key = generateKey();
            var encryptedPassword = encryptor.encryptValue(credential.getPassword(), key);

            credentials.insert(
                new Credential(
                    null,
                    key,
                    credential.getUrl(),
                    credential.getUsername(),
                    encryptedPassword,
                    credential.getUserId()
                )
            );

            return;
        }

        var key = credentials.find(credential).getKey();
        var rawPassword = credential.getPassword();
        credential.setPassword(encryptor.encryptValue(rawPassword, key));

        credentials.update(credential);
    }

    private String generateKey() {
        try {
            SecureRandom random = new SecureRandom();
            byte[] key = new byte[16];
            random.nextBytes(key);
            return Base64.getEncoder().encodeToString(key);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

}