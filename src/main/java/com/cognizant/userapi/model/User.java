package com.cognizant.userapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


/**
 * Domain object representing a user. Project Lombok keeps actual code at a minimum. {@code @Data} -
 * Generates getters, setters, toString, hash, and equals functions {@code @Entity} - JPA annotation to flag this class
 * for DB persistence {@code @NoArgsConstructor} - Create a constructor with no args to support JPA
 * {@code @AllArgsConstructor} - Create a constructor with all args to support testing
 * {@code @JsonIgnoreProperties(ignoreUnknow=true)} When converting JSON to Java, ignore any unrecognized attributes.
 * This is critical for REST because it encourages adding new fields in later versions that won't break. It also allows
 * things like _links to be ignore as well, meaning HAL documents can be fetched and later posted to the server without
 * adjustment.
 *
 * @author Harinath Kuntamukkala
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    @JsonIgnore
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="salary")
    private BigDecimal salary;


    /**
     * Useful constructor when id is not yet known.
     */
   public User(String name, BigDecimal salary) {
        this.name = name;
        this.salary = salary;
    }

}
