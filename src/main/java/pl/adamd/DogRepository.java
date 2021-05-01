package pl.adamd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

interface DogRepository {
    List<Dog> findAll();

    Page<Dog> findAll(Pageable page);

    Optional<Dog> findById(Integer id);

    boolean existsById(Integer id);

    Dog save(Dog dog);

    void deleteById(int id);
}
