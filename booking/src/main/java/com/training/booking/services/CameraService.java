package com.training.booking.services;

import com.training.booking.entities.Camera;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.handler.RestExceptionHandler;
import com.training.booking.repository.CameraRepository;
import com.training.booking.services.interfaces.ICameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CameraService implements ICameraService {

    @Autowired
    CameraRepository cameraRepository;

    private final String MSG = "Errore comunicazione con il database";

    @Override
    public List<Camera> getAll() throws NotFoundException, InternalServerErrorException {
        try {
            List<Camera> cameraList = cameraRepository.findAll();
            if (cameraList == null) {
                throw new NotFoundException();
            } else {
                return cameraList;
            }
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException(MSG);
        }
    }

    @Override
    public Camera getById(Long id) throws NotFoundException, InternalServerErrorException {
        try {
            Optional<Camera> cameraOptional = cameraRepository.findById(id);
            if (!cameraOptional.isPresent()) {
                throw new NotFoundException("Camera non trovata");
            } else {
                return cameraOptional.get();
            }
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException(MSG);
        }
    }

    @Override
    public Camera getByNumero(String numero) throws NotFoundException, InternalServerErrorException {
        try {
            Optional<Camera> cameraOptional = cameraRepository.findByNumero(numero);
            if (cameraOptional.isPresent()) {
                throw new NotFoundException();
            } else {
                return cameraOptional.get();
            }
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException(MSG);
        }
    }

    @Override
    public List<Camera> getByPostiLetto(Integer postiLetto) throws InternalServerErrorException, NotFoundException {
        try {
            List<Camera> cameraList = cameraRepository.findByPostiLetto(postiLetto);
            if(cameraList.isEmpty()) {
                throw new NotFoundException();
            } else {
                return cameraList;
            }
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException(MSG);
        }
    }

    @Override
    public List<Camera> getByPrezzoBaseBetween(Double prezzoMin, Double prezzoMax) throws InternalServerErrorException, NotFoundException {
        try {
            List<Camera> cameraList = cameraRepository.findByPrezzoBaseBetween(prezzoMin, prezzoMax);
            if(cameraList.isEmpty()) {
                throw new NotFoundException();
            } else {
                return cameraList;
            }
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException(MSG);
        }
    }

    @Override
    public List<Camera> getCamereDisponibili(Date checkIn, Date checkOut) throws InternalServerErrorException {
        try {
            return cameraRepository.findCamereDisponibili(checkIn, checkOut);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    public Camera save(Camera camera) throws InternalServerErrorException {
        try {
            return cameraRepository.save(camera);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    public Camera update(Camera camera) throws InternalServerErrorException {
        return cameraRepository.save(camera);
    }

    public void delete(Camera camera) throws InternalServerErrorException {
        try {
            delete(camera);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }
}
