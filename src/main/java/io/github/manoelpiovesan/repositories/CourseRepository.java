package io.github.manoelpiovesan.repositories;

import io.github.manoelpiovesan.entities.Course;
import io.github.manoelpiovesan.entities.User;
import io.github.manoelpiovesan.utils.MyException;
import io.github.manoelpiovesan.utils.Role;
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
public class CourseRepository implements PanacheRepository<Course> {

    @Inject
    UserRepository userRepository;

    /**
     * Create a new course
     *
     * @param course Course
     * @return Course
     */
    @Transactional
    public Course create(Course course, Long ownerId) {

        User owner = userRepository.findById(ownerId);

        if (owner == null || !owner.role.equals(Role.TEACHER)) {
            throw MyException.forbidden("Você não tem permissão para criar um curso.");
        }

        course.owner = owner;

        persist(course);
        System.out.println("[ETY] Creating course: " + course.name);

        return course;
    }


    /**
     * Search for a course
     *
     * @param term String
     * @return PanacheQuery<Course>
     */
    private PanacheQuery<Course> search(String term, Long ownerId) {

        StringBuilder hql = new StringBuilder("FROM Course c JOIN User u ON c.owner.id = u.id WHERE u.id = :ownerId");
        Map<String, Object> params = new HashMap<>();
        params.put("ownerId", ownerId);

        if (term != null && !term.isEmpty()) {
            params.put("term", "%" + term + "%");
            hql.append(" AND (c.name LIKE :term OR c.description LIKE :term)");
        }

        hql.append(" ORDER BY c.name");

        return find(hql.toString(), params);
    }

    /**
     * List courses
     *
     * @param term String
     * @param page int
     * @param size int
     * @return List<Course>
     */
    public List<Course> list(String term, int page, int size, Long ownerId) {

        return search(term, ownerId).page(page, size).list();
    }

    /**
     * Find a course by id
     *
     * @param id      Long
     * @param ownerId Long
     * @return Course
     */
    public Course getById(Long id, Long ownerId) {

        if (findById(id).owner.id.equals(ownerId)) {
            return findById(id);
        } else {
            throw MyException.forbidden("Este curso não pertence a você.");
        }
    }

    /**
     * Count the number of courses
     *
     * @param term String
     * @return long
     */
    public long count(String term, Long ownerId) {
        return search(term, ownerId).count();
    }
}
