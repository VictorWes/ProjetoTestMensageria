package dev.java10x.user.service;

import dev.java10x.user.domain.UserModel;
import dev.java10x.user.producer.UserProducer;
import dev.java10x.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    /**
     * Persists the given UserModel to the database and publishes an event with the saved user.
     * This method first saves the user using the UserRepository, then triggers the UserProducer
     * to publish an event containing the saved user data. The operation is transactional,
     * ensuring that the event is only published if the user is successfully saved.
     *
     * @param userModel the user to be saved and published
     * @return the persisted UserModel
     */
    @Transactional
    public UserModel saveAndPublish(UserModel userModel){
        userModel = userRepository.save(userModel);
        userProducer.publishEvent(userModel);
        return userModel;
    }

    public List<UserModel> listarTodosUsuarios() {
        return userRepository.findAll();
    }
}
