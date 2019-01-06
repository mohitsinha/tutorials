package com.tutorials.hibernatetips.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @FieldDefaults(level = AccessLevel.PRIVATE)
@Entity @Audited
public class University extends BaseEntity{
    String name;
    String city;
    @OneToMany(mappedBy = "university")
    Set<Student> students = new HashSet<>();
}
