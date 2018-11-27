package com.tutorials.hibernatetips.repositories;

import com.tutorials.hibernatetips.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface UniversityRepository extends RevisionRepository<University, Long, Long>, JpaRepository<University,Long> {
}