package com.tutorials.hibernatetips.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorials.hibernatetips.models.Student;
import com.tutorials.hibernatetips.models.University;
import com.tutorials.hibernatetips.repositories.StudentRepository;
import com.tutorials.hibernatetips.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class WebApi {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    @GetMapping(value = "/students")
    public Flux<Student> getStudents() {
        return Flux.defer(() -> {
            List<Student> all = studentRepository.findAll();
            return Flux.fromIterable(all);
        })
                .subscribeOn(Schedulers.elastic());
    }

        @Transactional
    @GetMapping(value = "/universities")
    public Flux<University> getUniversities() {

        List<University> all = universityRepository.findAll();
        return Flux.defer(() -> Flux.fromIterable(all))
                .doOnNext(university -> university.getStudents().size())
                .subscribeOn(Schedulers.elastic());
    }

    @PostMapping(value = "/universities")
    public Mono<ServerResponse> saveUniversity(Mono<University> universityMono) {
        return universityMono
                .publishOn(Schedulers.parallel())
                .doOnNext(universityRepository::save)
                .flatMap(a -> ServerResponse.ok().build());
    }

    @PostMapping(value = "/universities/{id}/student")
    public Mono<ServerResponse> saveStudent(@PathVariable Long id, @RequestBody Mono<Student> studentMono) {
        return studentMono
                .publishOn(Schedulers.parallel())
                .doOnNext(student -> {
                    University one = universityRepository.getOne(id);
                    student.setUniversity(one);
                    studentRepository.save(student);
                })
                .flatMap(a -> ServerResponse.ok().build());
    }
}