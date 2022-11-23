package se.sigma.sigmatechbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sigma.sigmatechbackend.exception.ArgumentException;
import se.sigma.sigmatechbackend.exception.DataNotFoundException;
import se.sigma.sigmatechbackend.models.dto.InvitationDto;
import se.sigma.sigmatechbackend.service.InvitationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/invitation")
public class InvitationController {

    InvitationService invitationService;



    @Autowired
    InvitationController(InvitationService invitationService) { this.invitationService = invitationService; }

    @PostMapping
    public ResponseEntity<InvitationDto> create(@RequestBody @Valid InvitationDto invitationDto) throws ArgumentException {
        return ResponseEntity.status(HttpStatus.CREATED).body(invitationService.create(invitationDto));
    }

    @GetMapping("/id/{invitationsId}")
    public ResponseEntity<InvitationDto> findById(@PathVariable("invitationsId") Integer invitationsId) throws DataNotFoundException{
        InvitationDto invitationDto = invitationService.findById(invitationsId);
        return ResponseEntity.ok(invitationDto);
    }

    @GetMapping
    public ResponseEntity<List<InvitationDto>> findAll() {
        List<InvitationDto> invitationDtos = invitationService.findAll();
        return ResponseEntity.ok(invitationDtos);

    }

    @PutMapping
    public ResponseEntity<InvitationDto> update(@RequestBody @Valid InvitationDto invitationDto) throws ArgumentException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(invitationService.update(invitationDto));
    }

    @DeleteMapping("/{invitationId}")
    public ResponseEntity<Void> delete(@PathVariable("invitationId") Integer invitationId){
        invitationService.delete(invitationId);
        return ResponseEntity.noContent().build();
    }

}






















