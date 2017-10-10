package com.example.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "megalopolises", path = "cities")
public interface CityRepository extends JpaRepository<City, Long> {
}
