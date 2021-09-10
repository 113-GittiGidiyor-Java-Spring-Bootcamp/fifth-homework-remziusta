package com.sms.controller;

import com.sms.dto.CourseGetDTO;
import com.sms.dto.CoursePostDTO;
import com.sms.model.Course;
import com.sms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/course")
public class CourseController {

    CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     *
     * @param coursePostDTO
     * @return the course added to the database
     */
    @PostMapping(value = "")
    public ResponseEntity<CourseGetDTO> save(@RequestBody @Valid CoursePostDTO coursePostDTO){
        Optional<CourseGetDTO> myOptionalResult = courseService.save(coursePostDTO);
        if(myOptionalResult.isPresent()){
            return new ResponseEntity<>(myOptionalResult.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @return the courses found in the database
     */
    @GetMapping(value = "")
    public ResponseEntity<List<CourseGetDTO>> getAllCourses(){
        return new ResponseEntity<>(courseService.findAll().get(),HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return the course with the desired id
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<CourseGetDTO> getCourse(@PathVariable Long id){
        return new ResponseEntity<>(courseService.findById(id).get(), HttpStatus.OK);
    }

    /**
     * Updates and returns the course with the requested ID
     * @param coursePostDTO
     * @return the course with the requested ID
     */
    @PutMapping(value = "")
    public ResponseEntity<CourseGetDTO> updateCourse(@RequestBody @Valid CoursePostDTO coursePostDTO){
        return new ResponseEntity<>(courseService.update(coursePostDTO).get(),HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return Deletes the course with the desired ID and returns a string value
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id){
        String name = courseService.findById(id).get().getCourseName();
        courseService.deleteById(id);
        return new ResponseEntity<>(name + " is deleted", HttpStatus.OK);
    }
}
