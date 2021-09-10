package com.sms.service;

import com.sms.dto.CourseGetDTO;
import com.sms.dto.StudentGetDTO;
import com.sms.dto.StudentPostDTO;
import com.sms.mappers.MapStructMapperImp;
import com.sms.model.Course;
import com.sms.model.Student;
import com.sms.repository.CourseRepository;
import com.sms.repository.StudentRepository;
import com.sms.repository.StudentRepository;
import com.sms.utils.ValidatorUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @Mock
    CourseRepository courseRepository;

    @Mock
    MapStructMapperImp mapStructMapperImp;

    @InjectMocks
    StudentService studentService;

    @Test
    void testFindAll() {
        List<Student> expected = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(expected);

        List<StudentGetDTO> actual = studentService.findAll().get();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertNotNull(expected),
                () -> assertEquals(expected.size(),actual.size())
        );
    }

    @Test
    void testFindById() {
        Student student = new Student();
        Optional<Student> opStudent = Optional.of(student);
        StudentGetDTO expected = new StudentGetDTO();

        when(studentRepository.findById(anyLong())).thenReturn(opStudent);
        when(mapStructMapperImp.studentToStudentGetDto(opStudent.get())).thenReturn(expected);
        lenient().when(studentRepository.getById(anyLong())).thenReturn(student);

        Optional<StudentGetDTO> actual = studentService.findById(anyLong());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertNotNull(expected)
        );
    }

    @Test
    void testSave() {
        Student student = new Student();
        StudentGetDTO expected = new StudentGetDTO();
        when(mapStructMapperImp.studentPostDtoToStudent(any())).thenReturn(student);
        when(studentRepository.save(any())).thenReturn(student);
        when(mapStructMapperImp.studentToStudentGetDto(student)).thenReturn(expected);

        StudentPostDTO postDTO = new StudentPostDTO();
        Optional<StudentGetDTO> actual = studentService.save(postDTO);


        assertAll(
                () -> assertNotNull(actual),
                () -> assertNotNull(expected),
                () -> assertEquals(expected.getId(),actual.get().getId())
        );
    }

    @Test
    void testUpdate() {
        Student student = new Student();
        StudentGetDTO expected = new StudentGetDTO();
        lenient().when(studentRepository.getById(anyLong())).thenReturn(student);
        lenient().when(studentRepository.save(any())).thenReturn(student);
        lenient().when(mapStructMapperImp.studentToStudentGetDto(student)).thenReturn(expected);

        StudentPostDTO postDTO = new StudentPostDTO();
        Optional<StudentGetDTO> actual = studentService.update(postDTO);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.getId(),actual.get().getId())
        );

    }

    @Test
    void testSetStudentCourseRelationship(){
        Student student = new Student();
        Course course = new Course();
        lenient().when(studentRepository.getById(anyLong())).thenReturn(student);
        lenient().when(courseRepository.getById(anyLong())).thenReturn(course);

        Executable executable = () -> ValidatorUtil.coursesStudentSizeControl(course.getStudents().size());
        assertDoesNotThrow(executable);

        student.setId(1L);
        course.setId(1L);

        lenient().when(studentRepository.save(student)).thenReturn(student);
        lenient().when(courseRepository.save(course)).thenReturn(course);

        Optional<List<StudentGetDTO>> actual = studentService.setStudentCourseRelationship(student.getId(), course.getId());

        assertAll(
                () -> assertNotNull(actual)
        );

    }

}