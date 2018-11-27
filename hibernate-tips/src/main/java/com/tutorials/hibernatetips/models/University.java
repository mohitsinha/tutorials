package com.tutorials.hibernatetips.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Audited
public class University extends BaseEntity{
    String name;
    String city;

    @OneToMany(mappedBy = "university")
    Set<Student> students;
}
