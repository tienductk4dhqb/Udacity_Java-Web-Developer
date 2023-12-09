package udacity.project1.services;

import udacity.project1.mappers.CredentialMapper;
import udacity.project1.models.Credential;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CredentialService {
    private final CredentialMapper credentialMapper;

    public void add(Credential credential) {
        credentialMapper.insert(credential);
    }

    public boolean deleteById(int id) {
        return credentialMapper.deleteById(id) == 1;
    }

    public Credential get(int id) {
        return credentialMapper.findById(id);
    }

    public List<Credential> getAllByUser(int userId) {
        return credentialMapper.getAllByUserId(userId);
    }

    public void upsert(Credential credential) {
        if (Objects.isNull(credential.getCredentialId())) {
            credentialMapper.insert(credential);
        } else {
            credentialMapper.update(credential);
        }
    }
}
