package se.sigma.sigmatechbackend.repository;

import org.springframework.data.repository.CrudRepository;
import se.sigma.sigmatechbackend.models.entity.Title;

import java.util.List;
import java.util.Optional;

public interface TitleRepository extends CrudRepository<Title, Integer> {
    List<Title> findAll();

    Optional<Title> findByName(String name);
}
