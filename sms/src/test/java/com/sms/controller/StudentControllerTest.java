package com.sms.controller;

import com.sms.dto.StudentGetDTO;
import com.sms.dto.StudentPostDTO;
import com.sms.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    StudentService studentService;

    @InjectMocks
    StudentController studentController;

    @Test
    void testSave() {
        StudentGetDTO studentGetDTO = new StudentGetDTO();
        Optional<StudentGetDTO> expected = Optional.of(studentGetDTO);
        when(studentService.save(any())).thenReturn(expected);

        StudentPostDTO studentPostDTO = new StudentPostDTO();
        StudentGetDTO actual = studentController.save(studentPostDTO).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(),actual),
                () -> assertEquals(expected.get().getId(),actual.getId())
        );

    }

    @Test
    void testGetAllStudents() {
        List<StudentGetDTO> list = new ArrayList<>();
        Optional<List<StudentGetDTO>> expected = Optional.of(list);
        when(studentService.findAll()).thenReturn(expected);

        List<StudentGetDTO> actual = studentController.getAllStudents().getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(),actual),
                () -> assertEquals(expected.get().size(),actual.size())
        );
    }

    @Test
    void testGetStudent() {
        StudentGetDTO studentGetDTO = new StudentGetDTO();
        Optional<StudentGetDTO> expected = Optional.of(studentGetDTO);
        when(studentService.findById(anyLong())).thenReturn(expected);

        StudentGetDTO actual = studentController.getStudent(anyLong()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(), actual)
        );
    }

    @Test
    void testUpdateStudent() {
        StudentGetDTO studentGetDTO = new StudentGetDTO();
        Optional<StudentGetDTO> expected = Optional.of(studentGetDTO);
        when(studentService.update(any())).thenReturn(expected);

        StudentPostDTO postDTO = new StudentPostDTO();
        StudentGetDTO actual = studentController.updateStudent(any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get().getId(),actual.getId())
        );
    }

    @Test
    void testDeleteStudent() {
        StudentGetDTO studentGetDTO = new StudentGetDTO();
        Optional<StudentGetDTO> ex = Optional.of(studentGetDTO);
        when(studentService.findById(anyLong())).thenReturn(ex);
        String expected = ex.get().getName() + " is deleted";

        String actual = studentController.deleteStudent(anyLong()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected,actual)
        );

    }

    @Test
    void testAddStudentCourseRelationship(){
        List<StudentGetDTO> studentList = new ArrayList<>();
        Optional<List<StudentGetDTO>> excepted = Optional.of(studentList);
        when(studentService.setStudentCourseRelationship(anyLong(),anyLong())).thenReturn(excepted);

        List<StudentGetDTO> actual = studentController.addStudentCourseRelationship(anyLong(),anyLong()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(excepted.get(),actual),
                () -> assertEquals(excepted.get().size(),actual.size())
        );
    }


}