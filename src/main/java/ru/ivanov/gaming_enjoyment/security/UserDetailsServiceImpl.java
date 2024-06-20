package ru.ivanov.gaming_enjoyment.security;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ivanov.gaming_enjoyment.entities.User;
import ru.ivanov.gaming_enjoyment.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found with username: " + username)
                );
        return new UserDetailsImpl(user);
    }
}
