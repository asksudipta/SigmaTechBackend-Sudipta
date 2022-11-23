package se.sigma.sigmatechbackend.repository;

import org.springframework.data.repository.CrudRepository;
import se.sigma.sigmatechbackend.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

    Optional<User> findByEmail(String email);

    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

}
