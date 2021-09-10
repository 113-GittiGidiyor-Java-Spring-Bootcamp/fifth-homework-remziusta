package com.sms.service;

import com.sms.dto.InstructorGetDTO;
import com.sms.dto.InstructorPostDTO;
import com.sms.dto.InstructorPutSalaryDTO;
import com.sms.mappers.MapStructMapper;
import com.sms.model.Course;
import com.sms.model.Instructor;
import com.sms.model.InstructorTransactionLogger;
import com.sms.repository.CourseRepository;
import com.sms.repository.InstructorRepository;
import com.sms.repository.InstructorTransactionLoggerRepository;
import com.sms.utils.ClientRequestInfo;
import com.sms.utils.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorService{

    InstructorRepository instructorRepository;

    MapStructMapper mapStructMapper;

    CourseRepository courseRepository;

    InstructorTransactionLoggerRepository instructorTransactionLoggerRepository;

    ClientRequestInfo clientRequestInfo;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository, MapStructMapper mapStructMapper, CourseRepository courseRepository, InstructorTransactionLoggerRepository instructorTransactionLoggerRepository, ClientRequestInfo clientRequestInfo) {
        this.instructorRepository = instructorRepository;
        this.mapStructMapper = mapStructMapper;
        this.courseRepository = courseRepository;
        this.instructorTransactionLoggerRepository = instructorTransactionLoggerRepository;
        this.clientRequestInfo = clientRequestInfo;
    }


    public Optional<List<InstructorGetDTO>> findAll() {
        List<InstructorGetDTO> instructorGetList = new ArrayList<>();
        for (Instructor i : instructorRepository.findAll())
            instructorGetList.add(mapStructMapper.instructorToInstructorGetDto(i));
        return Optional.of(instructorGetList);
    }

    public Optional<InstructorGetDTO> findById(Long id) {
        return Optional.of(mapStructMapper.instructorToInstructorGetDto(instructorRepository.getById(id)));
    }

    @Transactional
    public Optional<InstructorGetDTO> save(InstructorPostDTO instructorPostDTO) {
        ValidatorUtil.phoneNumberValidator(instructorRepository.existsByPhoneNumber(instructorPostDTO.getPhoneNumber()));
        Instructor instructor = mapStructMapper.instructorPostDtoToInstructor(instructorPostDTO);
        InstructorGetDTO instructorGetDTO = mapStructMapper.instructorToInstructorGetDto(instructorRepository.save(instructor));
        return Optional.of(instructorGetDTO);
    }

    @Transactional
    public void deleteById(Long id) {
        instructorRepository.deleteById(id);
    }

    @Transactional
    public Optional<InstructorGetDTO> update(InstructorPostDTO instructorPostDTO) {
        Instructor i = findByInstructorId(instructorPostDTO.getId());

        if (i != null){
            i.setName(instructorPostDTO.getName());
            i.setAddress(instructorPostDTO.getAddress());
            i.setPhoneNumber(instructorPostDTO.getPhoneNumber());
            i.setSalary(instructorPostDTO.getSalary());
        }

        InstructorGetDTO instructorGetDTO= mapStructMapper.instructorToInstructorGetDto(instructorRepository.save(i));
        return Optional.of(instructorGetDTO);
    }

    public Instructor findByInstructorId(Long id){
        return instructorRepository.getById(id);
    }

    public Optional<List<InstructorGetDTO>> setInstructorCourseRelationship(Long instructorId, Long courseId){
        Instructor i = instructorRepository.getById(instructorId);
        Course c = courseRepository.getById(courseId);

        i.getCourses().add(c);
        c.setInstructor(i);

        instructorRepository.save(i);
        courseRepository.save(c);

        return findAll();
    }

    @Transactional
    public Optional<InstructorGetDTO> updateSalary(InstructorPutSalaryDTO instructorPutSalaryDTO,boolean status) {
        Instructor instructor = findByInstructorId(instructorPutSalaryDTO.getInstructorId());
        InstructorTransactionLogger transactionLogger = new InstructorTransactionLogger();
        transactionLogger.setInstructorId(instructor.getId());
        transactionLogger.setPercentageRate(instructorPutSalaryDTO.getPercent());
        transactionLogger.setTransactionTime(LocalDate.now());
        transactionLogger.setClientUrl(clientRequestInfo.getClientUrl());
        transactionLogger.setClientIpAddress(clientRequestInfo.getClientIpAdress());
        transactionLogger.setSessionActivityId(clientRequestInfo.getSessionActivityId());
        transactionLogger.setSalaryBefore(instructor.getSalary());

        double newSalary = 0.0;

        if(status) newSalary = instructor.getSalary().doubleValue() + (instructor.getSalary().doubleValue() * (instructorPutSalaryDTO.getPercent().doubleValue() / 100));
        else newSalary = instructor.getSalary().doubleValue() - (instructor.getSalary().doubleValue() * (instructorPutSalaryDTO.getPercent().doubleValue() / 100));

        instructor.setSalary(newSalary);
        transactionLogger.setSalaryAfter(newSalary);

        instructorTransactionLoggerRepository.save(transactionLogger);

        InstructorGetDTO instructorGetDTO = mapStructMapper.instructorToInstructorGetDto(instructorRepository.save(instructor));
        return Optional.of(instructorGetDTO);
    }

    @Transactional(readOnly = true)
    public Page<List<InstructorTransactionLogger>> getAllTransactionByDate(String transactionDate, Integer page, Integer size, Pageable pageable){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate transactionDateResult = LocalDate.parse(transactionDate, formatter);
        if(page != null && size != null){
            pageable = PageRequest.of(page, size);
        }
        return instructorTransactionLoggerRepository.findAllTransactionByTransactionTime(transactionDateResult,pageable);
    }

    @Transactional(readOnly = true)
    public Page<List<InstructorTransactionLogger>> getAllTransactionById(Long id, Integer page, Integer size, Pageable pageable){
        if(page !=null && size != null){
            pageable = PageRequest.of(page,size);
        }
        return instructorTransactionLoggerRepository.findAllByInstructorId(id,pageable);
    }
}
