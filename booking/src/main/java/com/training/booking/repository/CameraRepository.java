package com.training.booking.repository;

import com.training.booking.entities.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CameraRepository extends JpaRepository<Camera, Long> {

    Optional<Camera> findByNumero(String numero);

    List<Camera> findByPostiLetto(int postiLetto);

    List<Camera> findByPrezzoBaseBetween(double prezzoMin, double prezzoMax);

    @Transactional
    Camera save(Camera obj);

    @Transactional
    void delete(Camera obj);
}
