package com.sms.service;

import com.sms.dto.CourseGetDTO;
import com.sms.dto.CoursePostDTO;
import com.sms.mappers.MapStructMapper;
import com.sms.model.Course;
import com.sms.repository.CourseRepository;
import com.sms.utils.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService{

    CourseRepository courseRepository;

    MapStructMapper mapStructMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, MapStructMapper mapStructMapper) {
        this.courseRepository = courseRepository;
        this.mapStructMapper = mapStructMapper;
    }


    public Optional<List<CourseGetDTO>> findAll() {
        List<CourseGetDTO> courseGetList = new ArrayList<>();
        for (Course c : courseRepository.findAll())
            courseGetList.add(mapStructMapper.courseToCourseGetDto(c));
        return Optional.of(courseGetList);
    }

    public Optional<CourseGetDTO> findById(Long id) {
        return Optional.of(mapStructMapper.courseToCourseGetDto(courseRepository.findById(id).get()));
    }

    public Optional<CourseGetDTO> save(CoursePostDTO coursePostDTO) {
        ValidatorUtil.courseCodeValidator(courseRepository.isTheSameCourseCode(coursePostDTO.getCourseCode()));
        Course c = mapStructMapper.coursePostDtoToCourse(coursePostDTO);
        CourseGetDTO courseGetDTO = mapStructMapper.courseToCourseGetDto(courseRepository.save(c));

        return Optional.of(courseGetDTO);
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public Optional<CourseGetDTO> update(CoursePostDTO coursePostDTO) {
        Course c = courseRepository.getById(coursePostDTO.getId());

        if (c != null){
            c.setCourseName(coursePostDTO.getCourseName());
            c.setCourseCode(coursePostDTO.getCourseCode());
            c.setCourseCredit(coursePostDTO.getCourseCredit());
        }

        CourseGetDTO courseGetDTO = mapStructMapper.courseToCourseGetDto(courseRepository.save(c));
        return Optional.of(courseGetDTO);
    }
}
