package com.tutorials.hibernatetips;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorials.hibernatetips.models.Student;
import com.tutorials.hibernatetips.models.University;
import com.tutorials.hibernatetips.repositories.StudentRepository;
import com.tutorials.hibernatetips.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class HibernateTipsApplication {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	UniversityRepository universityRepository;

	@Autowired
	ObjectMapper objectMapper;

//		@Bean
//		CommandLineRunner runner() {
//        return args -> {
//			University build = University.builder().name("Univ 1").build();
//			universityRepository.save(build);
//
//			Student mohit = Student.builder().name("Mohit").university(build).build();
//			studentRepository.save(mohit);
//
//
//			System.out.println("CommandLineRunner running in the UnsplashApplication class...");
////            activeLoanRepository.save(ActiveLoan.builder().build()).block();
//       };
//    }

	public static void main(String[] args) {
		SpringApplication.run(HibernateTipsApplication.class, args);
	}
}
