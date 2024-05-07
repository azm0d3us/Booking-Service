package com.training.booking.repository;

import com.training.booking.entities.Camera;
import com.training.booking.entities.ImmagineCamera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ImmagineCameraRepository extends JpaRepository<ImmagineCamera, Long> {

    List<ImmagineCamera> findByImmagineCamera(Camera camera);

}
