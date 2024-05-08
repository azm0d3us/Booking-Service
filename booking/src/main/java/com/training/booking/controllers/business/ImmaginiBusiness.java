package com.training.booking.controllers.business;

import com.training.booking.entities.Camera;
import com.training.booking.entities.ImmagineCamera;
import com.training.booking.entities.ImmagineResidenza;
import com.training.booking.entities.Residenza;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import com.training.booking.services.CameraService;
import com.training.booking.services.interfaces.ICameraService;
import com.training.booking.services.interfaces.IImmaginiService;
import com.training.booking.services.interfaces.IResidenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImmaginiBusiness {

    @Autowired
    private IImmaginiService immaginiService;
    @Autowired
    private ICameraService cameraService;

    public List<String> getByIdCamera(Long idCamera) throws NotValidException, NotFoundException, InternalServerErrorException {
        if (idCamera == null) {
            throw new NotValidException("Ho bisogno di un parametro per trovare la camera che ti interessa.");
        }
        Camera camera = cameraService.getById(idCamera);
        List<ImmagineCamera> imgList = immaginiService.findByCamera(camera);
        List<String> listImg = new ArrayList<>();
        for(ImmagineCamera cam : imgList) {
            listImg.add(cam.getUrl());
        }
        return listImg;
    }
}
