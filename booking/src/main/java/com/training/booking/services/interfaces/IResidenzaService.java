package com.training.booking.services.interfaces;

import com.training.booking.entities.Residenza;
import com.training.booking.errors.InternalServerErrorException;

import java.util.List;
import java.util.Optional;

public interface IResidenzaService {

    List<Residenza> getAll() throws InternalServerErrorException;

    Optional<Residenza> getById(Long id) throws InternalServerErrorException;

    List<Residenza> getByNomeContainingIgnoreCase(String nome) throws InternalServerErrorException;

    List<Residenza> getByIndirizzoContainingIgnoreCase(String indirizzo) throws InternalServerErrorException;

    Optional<Residenza> getByNomeEqualsIgnoreCase(String nome) throws InternalServerErrorException;

    Residenza save(Residenza obj) throws InternalServerErrorException;

    Residenza update(Residenza obj) throws InternalServerErrorException;

    void delete(Residenza obj) throws InternalServerErrorException;

    boolean existsResidenzaByNomeOrIndirizzo(String nome, String indirizzo);

}
