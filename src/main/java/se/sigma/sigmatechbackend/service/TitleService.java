package se.sigma.sigmatechbackend.service;

import se.sigma.sigmatechbackend.exception.ArgumentException;
import se.sigma.sigmatechbackend.exception.DataNotFoundException;
import se.sigma.sigmatechbackend.models.dto.TitleDto;

import java.util.List;

public interface TitleService {
    TitleDto findById(int titleId) throws DataNotFoundException;

    TitleDto findByName(String name) throws DataNotFoundException;

    List<TitleDto> findAll();

    TitleDto create(TitleDto titleDto);

    TitleDto update(TitleDto titleDto) throws DataNotFoundException;

    boolean delete(int titleId) throws DataNotFoundException;
}
