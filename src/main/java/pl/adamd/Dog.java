package pl.adamd;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "dogs")
class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "The breed of the dog must be determined")
    private String breed;
    private String description;

    Dog() {
    }

    public int getId() {
        return id;
    }

    void setId(final int id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    void setBreed(final String breed) {
        this.breed = breed;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(final String description) {
        this.description = description;
    }
}
