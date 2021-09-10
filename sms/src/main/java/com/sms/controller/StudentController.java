package com.sms.controller;

import com.sms.dto.StudentGetDTO;
import com.sms.dto.StudentPostDTO;
import com.sms.model.Student;
import com.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     *
     * @param studentPostDTO
     * @return the student added to the database
     */
    @PostMapping(value = "")
    public ResponseEntity<StudentGetDTO> save(@RequestBody @Valid StudentPostDTO studentPostDTO){
        Optional<StudentGetDTO> myOptionalResult = studentService.save(studentPostDTO);
        if(myOptionalResult.isPresent()){
            return new ResponseEntity<>(myOptionalResult.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @return the students found in the database
     */
    @GetMapping(value = "")
    public ResponseEntity<List<StudentGetDTO>> getAllStudents(){
        return new ResponseEntity<>(studentService.findAll().get(),HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return the student with the desired id
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentGetDTO> getStudent(@PathVariable Long id){
        return new ResponseEntity<>(studentService.findById(id).get(),HttpStatus.OK);
    }

    /**
     * Updates and returns the student with the requested ID
     * @param studentPostDTO
     * @return the student with the requested ID
     */
    @PutMapping(value = "")
    public ResponseEntity<StudentGetDTO> updateStudent(@RequestBody @Valid StudentPostDTO studentPostDTO){
        return new ResponseEntity<>(studentService.update(studentPostDTO).get(),HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return Deletes the student with the desired ID and returns a string value
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        String name = studentService.findById(id).get().getName();
        studentService.deleteById(id);
        return new ResponseEntity<>(name + " is deleted", HttpStatus.OK);
    }

    /**
     * That method adds student in course.
     * @param studentId
     * @param courseId
     * @return the students found in the database
     */
    @PostMapping("/{studentId}/{courseId}")
    public ResponseEntity<List<StudentGetDTO>> addStudentCourseRelationship(@PathVariable Long studentId, @PathVariable Long courseId){
        return new ResponseEntity<>(studentService.setStudentCourseRelationship(studentId,courseId).get(),HttpStatus.OK);
    }

}
