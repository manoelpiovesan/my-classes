package io.github.manoelpiovesan.repositories;

import io.github.manoelpiovesan.entities.Classroom;
import io.github.manoelpiovesan.utils.MyException;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import net.bytebuddy.utility.RandomString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Manoel Rodrigues
 */
@ApplicationScoped
public class ClassroomRepository implements PanacheRepository<Classroom> {

    @Inject
    CourseRepository courseRepository;

    @Inject
    StudentRepository studentRepository;


    /**
     * Search for a classroom
     *
     * @param term     String
     * @param courseId Long
     * @return PanacheQuery<Classroom>
     */
    public PanacheQuery<Classroom> search(String term, Long courseId) {
        StringBuilder hql = new StringBuilder("FROM Classroom cr JOIN Course c ON cr.course.id = c.id WHERE c.id = :courseId");

        Map<String, Object> params = new HashMap<>();
        params.put("courseId", courseId);

        if (term != null && !term.isEmpty()) {
            hql.append(" AND (c.name LIKE :term OR c.description LIKE :term)");
            params.put("term", "%" + term + "%");
        }

        return find(hql.toString(), params);
    }

    /**
     * List classrooms
     *
     * @param
     * @return List<Classroom>
     */
    public List<Classroom> list(String term, Long courseId, int page, int size) {
        return search(term, courseId).page(page, size).list();
    }

    /**
     * Create a new classroom
     *
     * @param classroom Classroom
     * @return Classroom
     */
    @Transactional
    public Classroom create(Classroom classroom, Long courseId, List<Long> students) {
        Classroom persistedClassroom = find("name", classroom.name).firstResult();
//        if (persistedClassroom != null) {
//            throw MyException.conflict("Sala de aula já existente");
//        }

        String code = RandomString.make(6).toUpperCase();

        while (find("code", code).firstResult() != null) {
            code = RandomString.make(6).toUpperCase();
        }

        classroom.code = code;
        classroom.course = courseRepository.findById(courseId);

        if (students != null && !students.isEmpty()) {
            students.forEach(studentId -> {
                classroom.students.add(studentRepository.findById(studentId));
            });
        }

        persist(classroom);

        System.out.println("[ETY] Creating Classroom: " + classroom.name);

        return classroom;
    }
}
