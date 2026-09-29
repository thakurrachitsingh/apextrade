package com.rachit.apextrade.user.service;

import com.rachit.apextrade.account.service.AccountLedger;
import com.rachit.apextrade.user.dto.UserProfileResponse;
import com.rachit.apextrade.user.exception.UserNotFoundException;
import com.rachit.apextrade.user.model.User;
import com.rachit.apextrade.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountLedger accountLedger;

    public UserService(UserRepository userRepository, AccountLedger accountLedger) {
        this.userRepository = userRepository;
        this.accountLedger = accountLedger;
    }

    @Transactional
    public User createUser(String email, String passwordHash) {
        User user = User.builder()
                .email(email)
                .passwordHash(passwordHash)
                .createdAt(System.currentTimeMillis())
                .build();
        User savedUser = userRepository.save(user);
        accountLedger.createAccount(savedUser.getUserId());
        return savedUser;
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public UserProfileResponse getProfile(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + userId));

        return new UserProfileResponse(
                user.getUserId(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
