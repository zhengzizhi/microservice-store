package com.contoso;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CacheEvict(value = "user", key = "#user.getId()")
    public User createUser(User user) {

        User result = null;

        if (!userRepository.existsById(user.getId())) {
            result = this.userRepository.save(user);
        }

        return result;
    }

    @Cacheable(value = "user")
    public Optional<User> getUser(String id) {
        return this.userRepository.findById(id);
    }

    @CachePut(value = "user", key = "#id")
    public User updateUser(String id, User user) {

        User result = null;

        if (userRepository.existsById(user.getId())) {
            result = this.userRepository.save(user);
        }

        return result;
    }

    @CacheEvict(value = "user", key = "#id")
    public boolean deleteUser(String id) {

        boolean deleted = false;

        if (userRepository.existsById(id)) {
            this.userRepository.deleteById(id);
            deleted = true;
        }

        return deleted;
    }
}
