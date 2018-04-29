package com.spring.cloud.example.libraryservice;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@SpringBootApplication
public class LibraryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryServiceApplication.class, args);
    }

}

@Component
class SampleData implements CommandLineRunner{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        Arrays.asList("Shame of Thrones", "The Cord of the Strings", "The Chemist")
                .forEach(b-> bookRepository.save(Book.builder().title(b).build()));
        bookRepository.findAll().forEach(System.out::println);
    }
}

@Entity @Data @NoArgsConstructor @AllArgsConstructor @Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
class Book {
    @Id @GeneratedValue
    Long id;
    String title;
}

@RepositoryRestResource
@Component
interface BookRepository extends JpaRepository<Book, Long> {
}

@RestController
@RefreshScope
class LibraryController{

    @Value("${library.name}")
    private String libraryName;

    @GetMapping("/details")
    public Mono<String> details(){
        return Mono.just(libraryName);
    }
}