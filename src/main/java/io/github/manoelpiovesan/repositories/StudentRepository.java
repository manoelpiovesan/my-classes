package io.github.manoelpiovesan.repositories;

import io.github.manoelpiovesan.entities.Student;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

/**
 * @author Manoel Rodrigues
 */
public class StudentRepository implements PanacheRepository<Student> {
}
