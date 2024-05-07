package com.training.booking.services;

import com.training.booking.entities.Camera;
import com.training.booking.entities.ImmagineCamera;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.repository.ImmagineCameraRepository;
import com.training.booking.services.interfaces.IImmaginiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Service
public class ImmaginiService implements IImmaginiService {

    @Autowired
    private ImmagineCameraRepository imgCameraRepository;

    @Override
    public List<ImmagineCamera> findByCamera(Camera camera) throws InternalServerErrorException {
        try {
            return imgCameraRepository.findByImmagineCamera(camera);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }
}
