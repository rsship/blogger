package com.blogger.bloggerplatform.services;

import com.blogger.bloggerplatform.models.Account;
import com.blogger.bloggerplatform.models.Authority;
import com.blogger.bloggerplatform.repositories.AccountRepository;
import com.blogger.bloggerplatform.repositories.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;

    public Account save(Account account) {

        if (account.getId() == null) {
            if (account.getAuthorities().isEmpty()) {
                Set<Authority> authorities = new HashSet<>();
                authorityRepository.findById("ROLE_USER").ifPresent(authorities::add);
                account.setAuthorities(authorities);
            }
            account.setCreatedAt(LocalDateTime.now());
        }
        account.setUpdatedAt(LocalDateTime.now());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Optional<Account> findOneByEmail(String email) {
        return accountRepository.findOneByEmailIgnoreCase(email);
    }
}
