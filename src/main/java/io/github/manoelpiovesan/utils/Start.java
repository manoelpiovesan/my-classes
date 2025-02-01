package io.github.manoelpiovesan.utils;

import io.github.manoelpiovesan.entities.Course;
import io.github.manoelpiovesan.entities.User;
import io.github.manoelpiovesan.repositories.CourseRepository;
import io.github.manoelpiovesan.repositories.UserRepository;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

/**
 * @author Manoel Rodrigues
 */
@Startup
public class Start {

    @Inject
    CourseRepository courseRepository;

    @Inject
    UserRepository userRepository;

    @PostConstruct
    void init() {
        System.out.println("Application started =)");

        if(courseRepository.count() == 0) {
            // Users
            User user = new User();
            user.firstName = "Manoel";
            user.lastName = "Piovesan";
            user.email = "m";
            user.username = "manoelpiovesan";
            user.password = "m";
            userRepository.create(user, Role.TEACHER);

            // Student

            // Course
            Course course = new Course();
            course.name = "Java";
            course.description = "Java course";
            courseRepository.create(course, 1L);

            // Classroom



        }

    }
}
