package io.github.manoelpiovesan.resources;

import io.github.manoelpiovesan.entities.Student;
import io.github.manoelpiovesan.repositories.StudentRepository;
import io.github.manoelpiovesan.utils.Role;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;


/**
 * @author Manoel Rodrigues
 */
@Path("/courses/{courseId}/students")
@RequestScoped
public class StudentResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    StudentRepository repository;

// The course entity already brings the students, so this method is not necessary.
//    /**
//     * Get the students from a course
//     *
//     * @param courseId Long
//     * @param term     String
//     * @param page     int
//     * @param size     int
//     * @return List<Student>
//     */
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed(Role.TEACHER)
//    public List<Student> getStudents(
//            @PathParam("courseId") Long courseId,
//            @QueryParam("t") String term,
//            @QueryParam("page") @DefaultValue("0") int page,
//            @QueryParam("size") @DefaultValue("15") int size
//    ) {
//        return repository.list(term, page, size, courseId, userId());
//    }

    /**
     * Create a new student
     *
     * @param courseId Long
     * @param student  Student
     * @return Student
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(Role.TEACHER)
    public Student createStudent(@PathParam("courseId") Long courseId, Student student) {
        return repository.create(student, courseId);
    }

    /**
     * Get student by id
     *
     * @return Student
     */
    // TODO: Should be verified if the student is from the course of the teacher who is requesting?
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(Role.TEACHER)
    public Student getStudent(@PathParam("id") Long id) {
        return repository.getById(id);
    }

}
