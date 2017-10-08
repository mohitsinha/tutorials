package com.example.resources;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "companies", path = "org")
public interface OrganizationRepository extends MongoRepository<Organization, UUID> {
}
