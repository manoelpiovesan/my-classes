package io.github.manoelpiovesan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.manoelpiovesan.entities.abstracts.AbstractFullEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Manoel Rodrigues
 */
@Entity
@Table(name = "classrooms")
@SQLDelete(sql = "UPDATE classrooms SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at = '1970-01-01 00:00:00+00'")
public class Classroom extends AbstractFullEntity {

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "description", nullable = false)
    public String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "code", nullable = false)
    public String code;

    /**
     * Relationships
     */

    // Classroom <1:N> Course
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    public Course course;

    // Classroom <N:M> Student
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToMany(mappedBy = "classrooms")
    public Set<Student> students = new HashSet<>();

}
