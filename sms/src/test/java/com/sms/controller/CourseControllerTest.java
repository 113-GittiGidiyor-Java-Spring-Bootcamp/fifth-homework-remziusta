package com.sms.controller;

import com.sms.dto.CourseGetDTO;
import com.sms.dto.CoursePostDTO;
import com.sms.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock
    CourseService courseService;

    @InjectMocks
    CourseController courseController;

    @Test
    void testSave() {
        CourseGetDTO courseGetDTO = new CourseGetDTO();
        Optional<CourseGetDTO> expected = Optional.of(courseGetDTO);
        when(courseService.save(any())).thenReturn(expected);

        CoursePostDTO coursePostDTO = new CoursePostDTO();
        CourseGetDTO actual = courseController.save(coursePostDTO).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(),actual),
                () -> assertEquals(expected.get().getId(),actual.getId())
        );

    }

    @Test
    void testGetAllCourses() {
        List<CourseGetDTO> list = new ArrayList<>();
        Optional<List<CourseGetDTO>> expected = Optional.of(list);
        when(courseService.findAll()).thenReturn(expected);

        List<CourseGetDTO> actual = courseController.getAllCourses().getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(),actual),
                () -> assertEquals(expected.get().size(),actual.size())
        );
    }

    @Test
    void testGetCourse() {
        CourseGetDTO courseGetDTO = new CourseGetDTO();
        Optional<CourseGetDTO> expected = Optional.of(courseGetDTO);
        when(courseService.findById(anyLong())).thenReturn(expected);

        CourseGetDTO actual = courseController.getCourse(anyLong()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(), actual)
        );
    }

    @Test
    void testUpdateCourse() {
        CourseGetDTO courseGetDTO = new CourseGetDTO();
        Optional<CourseGetDTO> expected = Optional.of(courseGetDTO);
        when(courseService.update(any())).thenReturn(expected);

        CoursePostDTO postDTO = new CoursePostDTO();
        CourseGetDTO actual = courseController.updateCourse(any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get().getId(),actual.getId())
        );
    }

    @Test
    void testDeleteCourse() {
        CourseGetDTO courseGetDTO = new CourseGetDTO();
        Optional<CourseGetDTO> ex = Optional.of(courseGetDTO);
        when(courseService.findById(anyLong())).thenReturn(ex);
        String expected = ex.get().getCourseName() + " is deleted";

        String actual = courseController.deleteCourse(anyLong()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected,actual)
        );

    }

}