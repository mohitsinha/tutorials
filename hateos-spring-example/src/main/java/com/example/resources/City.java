package com.example.resources;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@RestResource
@FieldDefaults(level = AccessLevel.PRIVATE)
public class City {
    @GeneratedValue
    @Id Long id;
    String name;
    @ManyToOne Country country;
}
