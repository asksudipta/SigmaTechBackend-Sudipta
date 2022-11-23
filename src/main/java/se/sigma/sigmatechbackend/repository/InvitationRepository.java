package se.sigma.sigmatechbackend.repository;

import org.springframework.data.repository.CrudRepository;
import se.sigma.sigmatechbackend.models.entity.Invitation;

public interface InvitationRepository extends CrudRepository<Invitation, Integer> {


}
