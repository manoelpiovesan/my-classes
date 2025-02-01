package io.github.manoelpiovesan.utils;

import io.github.manoelpiovesan.entities.Course;
import io.github.manoelpiovesan.entities.Student;
import io.github.manoelpiovesan.entities.User;
import io.github.manoelpiovesan.repositories.CourseRepository;
import io.github.manoelpiovesan.repositories.StudentRepository;
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

    @Inject
    StudentRepository studentRepository;

    @PostConstruct
    void init() {
        System.out.println("Application started =)");

        if (courseRepository.count() == 0) {
            // Users
            User user = new User();
            user.firstName = "Manoel";
            user.lastName = "Rodrigues";
            user.email = "string";
            user.username = "manoelpiovesan";
            user.password = "string";
            userRepository.create(user, Role.TEACHER);

            // Course
            Course course = new Course();
            course.name = "Java";
            course.description = "Java course";
            courseRepository.create(course, 1L);

            // Student
            Student student = new Student();
            student.name = "Karen";
            student.email = "karen@karen.com";
            studentRepository.create(student, 1L);

            // Student 2
            Student student2 = new Student();
            student2.name = "Karoleen";
            student2.email = "karoleen@karoleen.com";
            studentRepository.create(student2, 1L);

            // Classroom


        }

    }
}
