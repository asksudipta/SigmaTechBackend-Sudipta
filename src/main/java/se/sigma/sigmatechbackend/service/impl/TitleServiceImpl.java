package se.sigma.sigmatechbackend.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sigma.sigmatechbackend.exception.ArgumentException;
import se.sigma.sigmatechbackend.exception.DataNotFoundException;
import se.sigma.sigmatechbackend.models.dto.TitleDto;
import se.sigma.sigmatechbackend.models.entity.Title;
import se.sigma.sigmatechbackend.repository.TitleRepository;
import se.sigma.sigmatechbackend.service.TitleService;

import java.util.List;

@Service
public class TitleServiceImpl implements TitleService {
    private TitleRepository titleRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TitleServiceImpl(TitleRepository titleRepository, ModelMapper modelMapper) {
        this.titleRepository = titleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TitleDto findById(int titleId) throws DataNotFoundException {
        if (titleId < 0) throw new IllegalArgumentException("titleId must be 0 or more");
        Title title = titleRepository.findById(titleId).orElseThrow(() -> new DataNotFoundException("title not found"));
        return modelMapper.map(title, TitleDto.class);
    }

    @Override
    public TitleDto findByName(String name) throws DataNotFoundException {
        if (name == null || name.equals("")) throw new IllegalArgumentException("name was null or empty");
        Title title = titleRepository.findByName(name).orElseThrow(() -> new DataNotFoundException("title not found"));
        return modelMapper.map(title, TitleDto.class);
    }

    @Override
    public List<TitleDto> findAll() {
        return modelMapper.map(titleRepository.findAll(),
                new TypeToken<List<TitleDto>>() {
                }.getType());
    }

    @Override
    public TitleDto create(TitleDto titleDto) {
        if (titleDto == null) throw new IllegalArgumentException("TitleDto was null");
        if (titleDto.getTitleId() != 0) throw new IllegalArgumentException("id should be null");
        return save(titleDto);
    }

    @Override
    public TitleDto update(TitleDto titleDto){
        if (titleDto == null) throw new IllegalArgumentException("TitleDto was null");
        if (titleDto.getTitleId() == 0) throw new IllegalArgumentException("id should not be null");
        return save(titleDto);
    }

    @Override
    public boolean delete(int titleId) throws DataNotFoundException {
        if (titleId < 0) throw new IllegalArgumentException("id must be 0 or more");
        if (!titleRepository.existsById(titleId)) throw new DataNotFoundException("titleId was not be found");
        titleRepository.deleteById(titleId);
        return true;
    }

    TitleDto save(TitleDto titleDto)
    {
        Title titleToSave = modelMapper.map(titleDto, Title.class);
        Title savedTitle = titleRepository.save(titleToSave);
        return modelMapper.map(savedTitle, TitleDto.class);
    }
}
