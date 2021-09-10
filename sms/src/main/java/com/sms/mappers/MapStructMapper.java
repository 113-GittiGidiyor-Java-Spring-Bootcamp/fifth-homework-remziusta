package com.sms.mappers;

import com.sms.dto.*;
import com.sms.model.Course;
import com.sms.model.Instructor;
import com.sms.model.Student;


public interface MapStructMapper {
    Student studentPostDtoToStudent(StudentPostDTO studentPostDTO);
    StudentGetDTO studentToStudentGetDto(Student student);

    StudentSlimDTO studentToStudentSlimDto(Student student);
    CourseSlimDTO courseToCourseSlimDto(Course course);

    Course coursePostDtoToCourse(CoursePostDTO coursePostDTO);
    CourseGetDTO courseToCourseGetDto(Course course);

    Instructor instructorPostDtoToInstructor(InstructorPostDTO instructorPostDTO);
    InstructorGetDTO instructorToInstructorGetDto(Instructor instructor);

}
