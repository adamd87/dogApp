package pl.adamd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlDogRepository extends DogRepository, JpaRepository<Dog, Integer> {
}
