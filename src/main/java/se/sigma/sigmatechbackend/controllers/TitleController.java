package se.sigma.sigmatechbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sigma.sigmatechbackend.exception.DataNotFoundException;
import se.sigma.sigmatechbackend.models.dto.TitleDto;
import se.sigma.sigmatechbackend.service.TitleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/title")
public class TitleController {

    TitleService titleService;

    @Autowired
    public TitleController(TitleService titleService) { this.titleService = titleService;}

    @GetMapping("/id/{titleId}")
    public ResponseEntity<TitleDto> findById(@PathVariable ("titleId") int titleId) throws DataNotFoundException {
        TitleDto titleDto = titleService.findById(titleId);
        return ResponseEntity.ok(titleDto);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<TitleDto> findByName(@PathVariable("name") String name) throws DataNotFoundException {
        TitleDto titleDto = titleService.findByName(name);
        return ResponseEntity.ok(titleDto);
    }

    @GetMapping
    public ResponseEntity<List<TitleDto>> findAll() {
        List<TitleDto> titleDtos = titleService.findAll();
        return ResponseEntity.ok(titleDtos);
    }

    @PostMapping
    public ResponseEntity<TitleDto> create(@Valid @RequestBody TitleDto titleDto)
    {
        System.out.println("titleDto = " + titleDto);
        TitleDto createdTitleDto = titleService.create(titleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTitleDto);
    }

    @PutMapping
    public ResponseEntity<TitleDto> update(@RequestBody @Valid TitleDto titleDto) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(titleService.update(titleDto));
    }

    @DeleteMapping("/{titleId}")
    public ResponseEntity<Boolean> delete(@PathVariable("titleId") int titleId) throws DataNotFoundException {
        titleService.delete(titleId);
        return ResponseEntity.noContent().build();
    }

}
