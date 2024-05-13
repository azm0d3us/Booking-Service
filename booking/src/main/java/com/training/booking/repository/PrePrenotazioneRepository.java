package com.training.booking.repository;

import com.training.booking.entities.PrePrenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface PrePrenotazioneRepository extends JpaRepository<PrePrenotazione, Long> {

    @Transactional
    PrePrenotazione save(PrePrenotazione prenotazione);

    @Transactional
    void delete(PrePrenotazione prenotazione);
}
