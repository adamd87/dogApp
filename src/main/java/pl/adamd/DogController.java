package pl.adamd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
class DogController {
    private static final Logger logger = LoggerFactory.getLogger(DogController.class);
    private final DogRepository repository;

    DogController(final DogRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/dogs", method = RequestMethod.GET, params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Dog>> getAllDogs(){
        logger.info("Whole list of dogs!");
        return ResponseEntity.ok(repository.findAll());
    }

    @RequestMapping(value = "/dogs", method = RequestMethod.GET)
    ResponseEntity<List<Dog>> getAllDogs(Pageable page){
        logger.info("Custom pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @RequestMapping(value = "/dogs/{id}", method = RequestMethod.GET)
    ResponseEntity<Dog> getDogById(@PathVariable int id){
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/dogs", method = RequestMethod.POST)
    ResponseEntity<Dog> createDog(@RequestBody @Valid Dog dog) {
        Dog newDog = repository.save(dog);
        return ResponseEntity.created(URI.create("/" + newDog.getId())).body(newDog);
    }

    @RequestMapping(value = "/dogs/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateDog(@PathVariable int id, @RequestBody @Valid Dog updatedDog){
        if (!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        updatedDog.setId(id);
        repository.save(updatedDog);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/dogs/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteDog(@PathVariable int id){
        if (!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        logger.warn("Removing a dog from the repository");
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
