package com.training.booking.repository;

import com.training.booking.entities.Camera;
import com.training.booking.entities.ListaPrezzi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional(readOnly = true)
public interface ListaPrezziRepository extends JpaRepository<ListaPrezzi, Long> {

    Optional<ListaPrezzi> findById(Long id);

    List<ListaPrezzi> findByPrezzoBetween(Double prezzoMin, Double prezzoMax);

    List<ListaPrezzi> findByValiditaInizioBetweenOrValiditaFineBetween(Date inizioMin, Date inizioMax, Date fineMin, Date fineMax);

    Set<ListaPrezzi> findByCamera(Camera camera);

    @Transactional
    ListaPrezzi save(ListaPrezzi listaPrezzi);

    @Transactional
    void delete(ListaPrezzi listaPrezzi);

}
