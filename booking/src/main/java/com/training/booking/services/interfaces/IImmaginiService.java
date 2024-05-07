package com.training.booking.services.interfaces;

import com.training.booking.entities.Camera;
import com.training.booking.entities.ImmagineCamera;
import com.training.booking.errors.InternalServerErrorException;

import java.util.List;

public interface IImmaginiService {
    public List<ImmagineCamera> findByCamera(Camera camera) throws InternalServerErrorException;
}
