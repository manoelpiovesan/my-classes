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
@Table(name = "courses")
@SQLDelete(sql = "UPDATE courses SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at = '1970-01-01 00:00:00+00'")
public class Course extends AbstractFullEntity {

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "description", nullable = false)
    public String description;

    /**
     * Relationships
     */

    // User <1:N> Course
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    public User owner;

    // Course <1:N> Classroom
    @OneToMany(mappedBy = "course")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Set<Classroom> classrooms = new HashSet<>();


    // Course <1:N> Student
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(mappedBy = "course")
    public Set<Student> students = new HashSet<>();


}
