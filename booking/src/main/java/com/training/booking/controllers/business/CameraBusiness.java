package com.training.booking.controllers.business;

import com.training.booking.DTOs.GenericDTO;
import com.training.booking.POJOs.CameraPOJO;
import com.training.booking.entities.Camera;
import com.training.booking.entities.Residenza;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import com.training.booking.services.interfaces.ICameraService;
import com.training.booking.services.interfaces.IResidenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CameraBusiness {

    @Autowired
    ICameraService cameraService;

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

    @Autowired
    IResidenzaService rs;
    public Camera newCamera(CameraPOJO camera) throws NotValidException, InternalServerErrorException {
        if(!validateCamera(camera)) {
            throw new NotValidException();
        } else {
            Residenza residenza = rs.getById(camera.getId()).get();
            return cameraService.save(new Camera(null, camera.getPostiLetto(),
                    true, camera.getPrezzoBase(),
                    camera.getTipo(), camera.getNumero(),
                    camera.getInfoCheckOut(), residenza ,
                    null, null));
        }
    }

    public Camera editCamera(CameraPOJO cameraPOJO) throws InternalServerErrorException, NotFoundException {
        Camera camera = cameraService.getById(cameraPOJO.getId());
        return cameraService.update(new Camera(camera.getIdCamera(),
                camera.getPostiLetto(), camera.isDisponibile(),
                cameraPOJO.getPrezzoBase(),
                camera.getTipo(), camera.getNumero(),
                cameraPOJO.getInfoCheckOut(), camera.getResidenza(),
                (camera.getListini().isEmpty()) ? null : camera.getListini(),
                (camera.getPrenotazioni().isEmpty()) ? null : camera.getPrenotazioni()));
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
        record r(String nomeResidenza, int postiLetto, boolean disponibile, Double prezzoBase, String tipo, String infoCheckOut){};
        List<r> lr = cameraList.stream().map(c -> new r(c.getResidenza().getNome(), c.getPostiLetto(), c.isDisponibile(), c.getPrezzoBase(), c.getTipo(), c.getInfoCheckOut())).collect(Collectors.toList());
        return lr;
    }

    private Object makeRecord(Camera camera) {
        record r(String nomeResidenza, int postiLetto, boolean disponibile, Double prezzoBase, String tipo, String infoCheckOut){};
        return new r(camera.getResidenza().getNome(), camera.getPostiLetto(), camera.isDisponibile(), camera.getPrezzoBase(), camera.getTipo(), camera.getInfoCheckOut());
    }

}
