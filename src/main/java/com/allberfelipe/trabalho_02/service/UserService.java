package com.allberfelipe.trabalho_02.service;

import com.allberfelipe.trabalho_02.exception.UserNotFoundException;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.repository.UserRepository;
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

    public User createUser(User user) {
        return userRepository.save(user);
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
