package com.training.booking.repository;

import com.training.booking.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    boolean existsUtenteByUsername(String username);

    Optional<Utente> findByUsernameEquals(String username);

    Optional<Utente> findUserIdByUsername(String username);

    Optional<Utente> findByCf(String cf);

    @Transactional
    Utente save(Utente obj);

    @Transactional
    void delete(Utente obj);

}
