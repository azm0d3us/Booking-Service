package com.training.booking.services.interfaces;

import com.training.booking.entities.Camera;
import com.training.booking.entities.ListaPrezzi;
import com.training.booking.errors.InternalServerErrorException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IListaPrezziService {

    List<ListaPrezzi> getAll() throws InternalServerErrorException;

    Optional<ListaPrezzi> getById(Long id) throws InternalServerErrorException;

    Set<ListaPrezzi> getByCamera(Camera camera);

    List<ListaPrezzi> getByPrezzoBetween(Double prezzoMin, Double prezzoMax) throws InternalServerErrorException;

    List<ListaPrezzi> getByValiditaInizioBetweenOrValiditaFineBetween(Date inizioMin, Date inizioMax, Date fineMin, Date fineMax) throws InternalServerErrorException;

    ListaPrezzi save(ListaPrezzi listaPrezzi) throws InternalServerErrorException;

    ListaPrezzi update(ListaPrezzi listaPrezzi) throws InternalServerErrorException;

    void delete(ListaPrezzi listaPrezzi) throws InternalServerErrorException;
}
