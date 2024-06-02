package com.training.booking.controllers.business;

import com.training.booking.DTOs.GenericDTO;
import com.training.booking.DTOs.response.CameraResponse;
import com.training.booking.POJOs.CameraPOJO;
import com.training.booking.entities.Camera;
import com.training.booking.entities.ImmagineCamera;
import com.training.booking.entities.Residenza;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import com.training.booking.services.interfaces.ICameraService;
import com.training.booking.services.interfaces.IResidenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CameraBusiness {

    @Autowired
    private ICameraService cameraService;

    @Autowired
    private IResidenzaService residenzaService;

    public List<?> getAll() throws NotFoundException, InternalServerErrorException {
        List<Camera> cameraList = cameraService.getAll();
        return makeRecordList(cameraList);
    }

    public Camera getById(Long id) throws NotFoundException, InternalServerErrorException {
        return cameraService.getById(id);
    }

    public Camera getByNumero(String numero) throws NotFoundException, InternalServerErrorException {
        return cameraService.getByNumero(numero);

    }

    public List<?> getByPostiLetto(Integer postiLetto) throws InternalServerErrorException, NotFoundException {
        List<Camera> cameraList = cameraService.getByPostiLetto(postiLetto);
        return makeRecordList(cameraList);
    }

    public List<?> getByPrezzoBaseBetween(double prezzoMin, double prezzoMax) throws InternalServerErrorException, NotFoundException {
        List<Camera> cameraList = cameraService.getByPrezzoBaseBetween(prezzoMin, prezzoMax);
        return makeRecordList(cameraList);
    }

    public List<?> getCamereDisponibili(Date checkIn, Date checkOut) throws InternalServerErrorException, NotFoundException {
        List<Camera> cameraList = cameraService.getCamereDisponibili(checkIn, checkOut);
        if(cameraList.isEmpty()) {
            throw new NotFoundException();
        } else {
            return makeRecordList(cameraList);
        }
    }

    public List<String> getImmaginiCamera(Long idCamera) throws InternalServerErrorException, NotFoundException {
        Camera camera = cameraService.getById(idCamera);
        List<String> listaImmagini = new ArrayList<>();
        System.out.println("PROVA PROVA PROVA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        for (ImmagineCamera img : camera.getImmaginiCamera()) {
            System.out.println("********************************************************______________-------------§§§§§§§§§§");
            System.out.println(img.getUrl() + "<<<<<<<<<<<<<<");
            listaImmagini.add(img.getUrl());
        }
        return listaImmagini;
    }

    @Autowired
    IResidenzaService rs;
    public Camera newCamera(CameraPOJO camera) throws NotValidException, InternalServerErrorException {
        if(!validateCamera(camera)) {
            throw new NotValidException();
        } else {
            Residenza residenza = rs.getById(camera.getIdResidenza()).get();
            return cameraService.save(new Camera(null, camera.getPostiLetto(),
                    true, camera.getPrezzoBase(),
                    camera.getTipo(), camera.getNumero(),
                    camera.getInfoCheckOut(), residenza ,
                    null, null, null));
        }
    }

    public Camera editCamera(CameraPOJO cameraPOJO) throws InternalServerErrorException, NotFoundException {
        Camera camera = cameraService.getById(cameraPOJO.getIdCamera());
        return cameraService.update(new Camera(camera.getIdCamera(),
                camera.getPostiLetto(), camera.isDisponibile(),
                cameraPOJO.getPrezzoBase(),
                camera.getTipo(), camera.getNumero(),
                cameraPOJO.getInfoCheckOut(), camera.getResidenza(),
                (camera.getListini().isEmpty()) ? null : camera.getListini(),
                (camera.getPrenotazioni().isEmpty()) ? null : camera.getPrenotazioni(), null));
    }

    public Object deleteCamera(GenericDTO generic) throws InternalServerErrorException, NotFoundException {
        Camera camera = cameraService.getById(generic.getId());
        cameraService.delete(camera);
        return makeRecord(camera);
    }

    private boolean validateCamera(CameraPOJO camera) {
        if(camera.getTipo().trim().isEmpty() || camera.getPrezzoBase() == null || camera.getPostiLetto() == null)
            return false;
        else return true;
    }

    private List<?> makeRecordList(List<Camera> cameraList) {
        record r(
                Long idCamera,
                String nomeResidenza,
                String numeroCamera,
                int postiLetto,
                boolean disponibile,
                Double prezzoBase,
                String tipo,
                String infoCheckOut
        ){};

        List<r> lr = cameraList.stream().map(c ->
                new r(
                        c.getIdCamera(),
                        c.getResidenza().getNome(),
                        c.getNumero(), c.getPostiLetto(), c.isDisponibile(), c.getPrezzoBase(), c.getTipo(), c.getInfoCheckOut())).collect(Collectors.toList());

        return lr;
    }

    private Object makeRecord(Camera camera) {
        record r(Long id, String nomeResidenza, String numeroCamera, int postiLetto, boolean disponibile, Double prezzoBase, String tipo, String infoCheckOut){};
        return new r(camera.getIdCamera(), camera.getResidenza().getNome(), camera.getNumero(), camera.getPostiLetto(), camera.isDisponibile(), camera.getPrezzoBase(), camera.getTipo(), camera.getInfoCheckOut());
    }


    public List<Camera> getCameraByResidenza(Long id) throws NotValidException, NotFoundException, InternalServerErrorException {
        Optional<Residenza> residenzaOptional = residenzaService.getById(id);
        if(!residenzaOptional.isPresent()) {
            throw new NotFoundException("Residenza non trovata");
        }
        Residenza residenza = residenzaOptional.get();
        List<Camera> cameraList = cameraService.getCameraByResidenza(residenza);
        if(cameraList.isEmpty()) {
            throw new NotFoundException("Nessuna camera trovata");
        }
        return cameraList;
    }
}
