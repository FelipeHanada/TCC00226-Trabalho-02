package com.allberfelipe.trabalho_02.service;

import com.allberfelipe.trabalho_02.exception.InvalidPasswordException;
import com.allberfelipe.trabalho_02.exception.UserNotFoundException;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.repository.UserRepository;
import com.allberfelipe.trabalho_02.util.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Page<User> getUsersWithPagination(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User createUser(
            String email,
            String password,
            String firstName,
            String lastName,
            String aboutMe,
            String phoneNumber
    ) {
        final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$";

        if (!password.matches(passwordRegex)) {
            throw new InvalidPasswordException("A senha deve ter no mínimo 8 caracteres, contendo ao menos uma letra minúscula, uma maiúscula e um caractere especial.");
        }

        return userRepository.save(new User(
                email,
                PasswordHash.hashPassword(password),
                firstName,
                lastName,
                aboutMe,
                phoneNumber
        ));
    }

    @Transactional
    public User updateUser(User user) {
        userRepository.findByIdWithLock(user.getId())
                .orElseThrow(() -> new UserNotFoundException(
                        "User id: " + user.getId() + " not found."
                ));
        return userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
