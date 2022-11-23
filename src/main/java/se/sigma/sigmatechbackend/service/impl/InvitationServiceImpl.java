package se.sigma.sigmatechbackend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sigma.sigmatechbackend.exception.DataNotFoundException;
import se.sigma.sigmatechbackend.models.dto.InvitationDto;
import se.sigma.sigmatechbackend.models.entity.Invitation;
import se.sigma.sigmatechbackend.repository.InvitationRepository;
import se.sigma.sigmatechbackend.service.InvitationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvitationServiceImpl implements InvitationService {

    private final ModelMapper mapper;
    private final InvitationRepository invitationsRepository;
     @Autowired
    public InvitationServiceImpl(ModelMapper mapper, InvitationRepository invitationsRepository) {
        this.mapper = mapper;
        this.invitationsRepository = invitationsRepository;
    }

    @Override
    public InvitationDto create(InvitationDto dto) {
        if (dto == null) throw new IllegalArgumentException("Person data should not be null");
        if (dto.getInvitationId() != 0) throw new IllegalArgumentException("id should be null");
        Invitation saved = mapper.map(dto, Invitation.class);
        Invitation result = invitationsRepository.save(saved);
        return mapper.map(result, InvitationDto.class);
    }

    @Override
    public InvitationDto findById(Integer invitationsId) throws DataNotFoundException{
        if (invitationsId == null || invitationsId == 0) throw new IllegalArgumentException("Invitations Id should not be null or 0");
        Invitation result = invitationsRepository.findById(invitationsId).orElseThrow(() -> new DataNotFoundException("Invitations not found"));
        return mapper.map(result, InvitationDto.class);
    }

    @Override
    public List<InvitationDto> findAll() {

        List<Invitation> list = new ArrayList<>();
        invitationsRepository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(category -> mapper.map(category, InvitationDto.class)).collect(Collectors.toList());

    }

    @Override
    public InvitationDto update(InvitationDto dto){
        if (dto == null) throw new IllegalArgumentException("Invitations data should not be null");
        if (dto.getInvitationId() == 0) throw new IllegalArgumentException("InvitationsId should not be null");

        Invitation entity = mapper.map(dto, Invitation.class);
        Invitation result = invitationsRepository.save(entity);
        return mapper.map(result, InvitationDto.class);


    }

    @Override
    public void delete(Integer invitationsId){
        invitationsRepository.deleteById(invitationsId);
        invitationsRepository.existsById(invitationsId);
    }
}
