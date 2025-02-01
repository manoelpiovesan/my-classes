package io.github.manoelpiovesan.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Manoel Rodrigues
 */
@Entity
@Table(name = "students")
@SQLDelete(sql = "UPDATE students SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at = '1970-01-01 00:00:00+00'")
public class Student extends AbstractFullEntity {

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "email", nullable = false)
    public String email;

    /*
      Add more fields later
      firstName, lastName, email, cpf, rg, birthDate, address, phone, cellphone..
     */

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    public Set<Course> courses = new HashSet<>();

}
