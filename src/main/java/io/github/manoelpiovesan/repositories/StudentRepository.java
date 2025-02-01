package io.github.manoelpiovesan.repositories;

import io.github.manoelpiovesan.entities.Course;
import io.github.manoelpiovesan.entities.Student;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Manoel Rodrigues
 */
@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {
    @Inject
    CourseRepository courseRepository;

    /**
     * Create a new student
     *
     * @param student Student
     * @return Student
     */
    @Transactional
    public Student create(Student student, Long courseId) {
        Student persistedStudent = findByEmail(student.email);
        Course course = courseRepository.findById(courseId);

        if (persistedStudent != null || course == null) {
            return null;
        }

        student.course = course;

        persist(student);

        System.out.println("[ETY] Creating Student: " + student.name);

        return student;
    }

    /**
     * Search for a student
     *
     * @param term String
     * @return PanacheQuery<Student>
     */
    private PanacheQuery<Student> search(String term, Long courseId) {
        if (courseId == null) {
            throw new IllegalArgumentException("Course ID is required");
        }

        StringBuilder hql = new StringBuilder("FROM Student s JOIN Course c ON s.course.id = c.id ");
        Map<String, Object> params = new HashMap<>();

        hql.append("WHERE c.id = :courseId ");
        params.put("courseId", courseId);

        if (term != null && !term.isEmpty()) {
            hql.append("AND s.name LIKE :term OR s.email LIKE :term");
            params.put("term", "%" + term + "%");
        }

        return find(hql.toString(), params);
    }

    /**
     * List students
     *
     * @param term     String
     * @param page     int
     * @param size     int
     * @param courseId Long
     * @return List<Student>
     */
    public List<Student> list(String term, int page, int size, Long courseId, Long ownerId) {

        // Check if the course belongs to the teacher
        if (!courseRepository.findById(courseId).owner.id.equals(ownerId)) {
            throw new ForbiddenException("This course does not belong to you =)");
        }

        return search(term, courseId).page(page, size).list();
    }

    /**
     * Find a student by email
     *
     * @param email String
     * @return Student
     */
    private Student findByEmail(String email) {
        return find("email", email).firstResult();
    }
}
