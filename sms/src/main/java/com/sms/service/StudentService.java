package com.sms.service;

import com.sms.dto.StudentGetDTO;
import com.sms.dto.StudentPostDTO;
import com.sms.mappers.MapStructMapper;
import com.sms.model.Course;
import com.sms.model.Student;
import com.sms.repository.CourseRepository;
import com.sms.repository.StudentRepository;
import com.sms.utils.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService{

    StudentRepository studentRepository;

    MapStructMapper mapStructMapper;

    CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, MapStructMapper mapStructMapper, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.mapStructMapper = mapStructMapper;
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly = true)
    public Optional<List<StudentGetDTO>> findAll(){
        List<StudentGetDTO> studentGetList = new ArrayList<>();
        for (Student s : studentRepository.findAll())
            studentGetList.add(mapStructMapper.studentToStudentGetDto(s));

        return Optional.of(studentGetList);
    }

    @Transactional(readOnly = true)
    public Optional<StudentGetDTO> findById(Long id){
        StudentGetDTO studentGetDTO = mapStructMapper.studentToStudentGetDto(studentRepository.findById(id).get());
        return Optional.of(studentGetDTO);
    }

    @Transactional
    public Optional<StudentGetDTO> save(StudentPostDTO studentPostDTO) {
        Student s = mapStructMapper.studentPostDtoToStudent(studentPostDTO);
        StudentGetDTO studentGetDTO = mapStructMapper.studentToStudentGetDto(studentRepository.save(s));
        return Optional.of(studentGetDTO);
    }

    @Transactional
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public Optional<StudentGetDTO> update(StudentPostDTO studentPostDTO) {
        Student s = studentRepository.getById(studentPostDTO.getId());

        if(s != null){
            s.setName(studentPostDTO.getName());
            s.setAddress(studentPostDTO.getAddress());
            s.setGender(studentPostDTO.getGender());
            s.setBirthDate(studentPostDTO.getBirthDate());
        }

        StudentGetDTO studentGetDTO = mapStructMapper.studentToStudentGetDto(studentRepository.save(s));

        return Optional.of(studentGetDTO);
    }

    @Transactional
    public Optional<List<StudentGetDTO>> setStudentCourseRelationship(Long studentId, Long courseId){
        Student student = studentRepository.getById(studentId);
        Course course = courseRepository.getById(courseId);

        if(student != null && course != null){
            ValidatorUtil.coursesStudentSizeControl(course.getStudents().size());

            student.getCourses().add(course);
            course.getStudents().add(student);

            studentRepository.save(student);
            courseRepository.save(course);
        }

        return findAll();
    }

}
