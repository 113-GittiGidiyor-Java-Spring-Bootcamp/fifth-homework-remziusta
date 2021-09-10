package com.sms.utils;

import com.sms.exceptions.CourseIsAlreadyExistException;
import com.sms.exceptions.InstructorIsAlreadyExistException;
import com.sms.exceptions.StudentAgeNotValidException;
import com.sms.exceptions.StudentNumberForOneCourseExcededException;

import java.time.LocalDate;
import java.time.Period;

public class ValidatorUtil {

    /**
     * This function checks if the age is greater than 18 and less than 40 and throws an error.
     * @param birthDate is condition parameter.
     *
     */
    public static void studentAgeValidator(LocalDate birthDate){
        int age = Period.between(birthDate,LocalDate.now()).getYears();
        if(age < 18 || age > 40){
           throw new StudentAgeNotValidException(ErrorMessageConstants.STUDENT_AGE_ERROR);
        }
    }

    /**
     * This function evaluates the count returned from a sql query and throws an error.
     * @param count is condition parameter.
     */
    public static void courseCodeValidator(int count){
        if(count > 0){
            throw  new CourseIsAlreadyExistException(ErrorMessageConstants.COURSES_AVAILABLE);
        }
    }

    /**
     * This function evaluates the count returned from a sql query and throws an error.
     * @param count is condition parameter.
     */
    public static void phoneNumberValidator(int count){
        if(count > 0)
            throw new InstructorIsAlreadyExistException(ErrorMessageConstants.INSTRUCTOR_AVAILABLE);
    }

    /**
     * Throws an error based on the number of students in the course
     * @param count
     */
    public static void coursesStudentSizeControl(int count){
        if (count > 20)
            throw new StudentNumberForOneCourseExcededException(ErrorMessageConstants.COURSE_MAX_STUDENT);
    }
}
