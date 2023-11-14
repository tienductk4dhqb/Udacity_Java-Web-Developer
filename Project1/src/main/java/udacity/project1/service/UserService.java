package udacity.project1.service;

import org.springframework.stereotype.Service;

import udacity.project1.mapper.UserMapper;
import udacity.project1.model.User;

import java.util.Base64;
import java.security.SecureRandom;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;
    
    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }
    

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user) {
        String encodedSalt = createSalt();
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

        return userMapper.insert(
            new User(
                null,
                user.getUsername(),
                encodedSalt,
                hashedPassword,
                user.getFirstName(),
                user.getLastName()
            )
        );
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    private String createSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

}