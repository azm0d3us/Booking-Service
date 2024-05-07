package com.training.booking.repository;

import com.training.booking.entities.Camera;
import com.training.booking.entities.ImmagineCamera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CameraRepository extends JpaRepository<Camera, Long> {

    Optional<Camera> findByNumero(String numero);

    List<Camera> findByPostiLetto(int postiLetto);

//    List<ImmagineCamera> findImmaginiCameraByCamera(Camera camera);

    List<Camera> findByPrezzoBaseBetween(double prezzoMin, double prezzoMax);

    @Query("SELECT c FROM Camera c " +
            "WHERE c.idCamera NOT IN " +
            "(SELECT p.cameraPrenotata.idCamera FROM Prenotazione p " +
            "WHERE ((p.checkIn <= :checkOut) AND (p.checkOut >= :checkIn)))")
    List<Camera> findCamereDisponibili(Date checkIn, Date checkOut);

    @Transactional
    Camera save(Camera obj);

    @Transactional
    void delete(Camera obj);
}