package com.sms.repository;

import com.sms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query(value = "select count(c.id) from Course c WHERE c.courseCode = ?1")
    int isTheSameCourseCode(String courseCode);
}
