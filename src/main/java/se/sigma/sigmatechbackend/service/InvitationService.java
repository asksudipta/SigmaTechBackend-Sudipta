package se.sigma.sigmatechbackend.service;

import se.sigma.sigmatechbackend.exception.ArgumentException;
import se.sigma.sigmatechbackend.exception.DataNotFoundException;
import se.sigma.sigmatechbackend.models.dto.InvitationDto;

import java.util.List;

public interface InvitationService {
    InvitationDto create (InvitationDto dto);
    InvitationDto findById(Integer invitationsId) throws DataNotFoundException;
    List<InvitationDto> findAll();
    InvitationDto update(InvitationDto dto);
    void delete (Integer invitationsId);
}
