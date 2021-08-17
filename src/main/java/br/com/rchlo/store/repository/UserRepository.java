package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<UserDetails> findByEmail(String username);

}
