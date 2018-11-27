package com.tutorials.hibernatetips.controllers;

import com.tutorials.hibernatetips.models.Student;
import com.tutorials.hibernatetips.models.University;
import com.tutorials.hibernatetips.repositories.StudentRepository;
import com.tutorials.hibernatetips.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RestController
public class WebApi {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping(value = "/students")
    public Flux<Student> getStudents() {
        return Flux.defer(() -> Flux.fromIterable(studentRepository.findAll()))
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(value = "/universities")
    public Flux<University> getUniversities() {
        return Flux.defer(() -> Flux.fromIterable(universityRepository.findAll()))
                .doOnNext(university -> university.getStudents().size())
                .subscribeOn(Schedulers.elastic());
    }
}
