package com.training.booking.services.interfaces;

import com.training.booking.entities.Camera;
import com.training.booking.entities.Residenza;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;

import java.sql.Date;
import java.util.List;

public interface ICameraService {

    List<Camera> getAll() throws InternalServerErrorException, NotFoundException;

    Camera getById(Long id) throws InternalServerErrorException, NotFoundException;

    Camera getByNumero(String numero) throws InternalServerErrorException, NotFoundException;

    List<Camera> getByPostiLetto(Integer postiLetto) throws InternalServerErrorException, NotFoundException;

    List<Camera> getByPrezzoBaseBetween(Double prezzoMin, Double prezzoMax) throws InternalServerErrorException, NotFoundException;

    List<Camera> getCamereDisponibili(Date checkIn, Date checkOut) throws InternalServerErrorException;

    public Camera save(Camera camera) throws InternalServerErrorException;

    public Camera update(Camera camera) throws InternalServerErrorException;

    public void delete(Camera camera) throws InternalServerErrorException;

    List<Camera> getCameraByResidenza(Residenza residenza) throws InternalServerErrorException;
}
