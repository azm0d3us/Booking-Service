package com.training.booking.repository;

import com.training.booking.entities.Camera;
import com.training.booking.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    List<Prenotazione> findByCheckInBetweenOrCheckOutBetween(Date checkInMin, Date checkInMax, Date checkOutMin, Date checkOutMax);

    List<Prenotazione> findByNumPersone(Integer nPersone);

//    List<Prenotazione> findByCheckOutBeforeOrCheckInAfterOrCheckOutBeforeAndCheckInAfter(Date checkIn1, Date checkOut1, Date checkIn2, Date checkOut2);

    @Transactional
    Prenotazione save(Prenotazione prenotazione);

}
