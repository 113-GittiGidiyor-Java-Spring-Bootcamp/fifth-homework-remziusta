package com.sms.service;

import com.sms.dto.CourseGetDTO;
import com.sms.dto.CoursePostDTO;
import com.sms.mappers.MapStructMapperImp;
import com.sms.model.Course;
import com.sms.repository.CourseRepository;
import com.sms.utils.ValidatorUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    CourseRepository courseRepository;

    @Mock
    MapStructMapperImp mapStructMapperImp;

    @InjectMocks
    CourseService courseService;

    @Test
    void testFindAll() {
        List<Course> expected = new ArrayList<>();
        when(courseRepository.findAll()).thenReturn(expected);

        List<CourseGetDTO> actual = courseService.findAll().get();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertNotNull(expected),
                () -> assertEquals(expected.size(),actual.size())
        );
    }

    @Test
    void testFindById() {
        Course course = new Course();
        Optional<Course> opCourse = Optional.of(course);
        CourseGetDTO expected = new CourseGetDTO();
        when(courseRepository.findById(anyLong())).thenReturn(opCourse);
        when(mapStructMapperImp.courseToCourseGetDto(opCourse.get())).thenReturn(expected);

        Optional<CourseGetDTO> actual = courseService.findById(anyLong());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertNotNull(expected)
        );
    }

    @Test
    void testSave() {
        Executable executable = () -> ValidatorUtil.courseCodeValidator(courseRepository.isTheSameCourseCode(anyString()));
        assertDoesNotThrow(executable);

        Course course = new Course();
        CourseGetDTO expected = new CourseGetDTO();
        when(mapStructMapperImp.coursePostDtoToCourse(any())).thenReturn(course);
        when(courseRepository.save(any())).thenReturn(course);
        when(mapStructMapperImp.courseToCourseGetDto(course)).thenReturn(expected);

        CoursePostDTO postDTO = new CoursePostDTO();
        Optional<CourseGetDTO> actual = courseService.save(postDTO);


        assertAll(
                () -> assertNotNull(actual),
                () -> assertNotNull(expected),
                () -> assertEquals(expected.getId(),actual.get().getId())
        );
    }

    @Test
    void testUpdate() {
        Course course = new Course();
        CourseGetDTO expected = new CourseGetDTO();
        lenient().when(courseRepository.getById(anyLong())).thenReturn(course);
        lenient().when(courseRepository.save(any())).thenReturn(course);
        lenient().when(mapStructMapperImp.courseToCourseGetDto(course)).thenReturn(expected);

        CoursePostDTO postDTO = new CoursePostDTO();
        Optional<CourseGetDTO> actual = courseService.update(postDTO);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.getId(),actual.get().getId())
        );

    }
}