package com.sms.service;

import com.sms.dto.InstructorGetDTO;
import com.sms.dto.InstructorPostDTO;
import org.springframework.data.domain.Pageable;
import com.sms.mappers.MapStructMapperImp;
import com.sms.model.Instructor;
import com.sms.model.InstructorTransactionLogger;
import com.sms.repository.InstructorRepository;
import com.sms.repository.InstructorTransactionLoggerRepository;
import com.sms.utils.ClientRequestInfo;
import com.sms.utils.ValidatorUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstructorServiceTest {

    @Mock
    InstructorRepository instructorRepository;

    @Mock
    MapStructMapperImp mapStructMapperImp;

    @Mock
    ClientRequestInfo clientRequestInfo;

    @Mock
    InstructorTransactionLoggerRepository loggerRepository;

    @InjectMocks
    InstructorService instructorService;

    @Test
    void testFindAll() {
        List<Instructor> expected = new ArrayList<>();
        when(instructorRepository.findAll()).thenReturn(expected);

        List<InstructorGetDTO> actual = instructorService.findAll().get();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertNotNull(expected),
                () -> assertEquals(expected.size(),actual.size())
        );
    }

    @Test
    void testFindById() {
        Instructor instructor = new Instructor();
        Optional<Instructor> opInstructor = Optional.of(instructor);
        InstructorGetDTO expected = new InstructorGetDTO();

        lenient().when(instructorRepository.findById(anyLong())).thenReturn(opInstructor);
        lenient().when(mapStructMapperImp.instructorToInstructorGetDto(opInstructor.get())).thenReturn(expected);
        lenient().when(instructorRepository.getById(anyLong())).thenReturn(instructor);


        Optional<InstructorGetDTO> actual = instructorService.findById(anyLong());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertNotNull(expected)
        );
    }

    @Test
    void testSave() {
        Executable executable = () -> ValidatorUtil.phoneNumberValidator(instructorRepository.existsByPhoneNumber(anyString()));
        assertDoesNotThrow(executable);

        Instructor instructor = new Instructor();
        InstructorGetDTO expected = new InstructorGetDTO();
        when(mapStructMapperImp.instructorPostDtoToInstructor(any())).thenReturn(instructor);
        when(instructorRepository.save(any())).thenReturn(instructor);
        when(mapStructMapperImp.instructorToInstructorGetDto(instructor)).thenReturn(expected);

        InstructorPostDTO postDTO = new InstructorPostDTO();
        Optional<InstructorGetDTO> actual = instructorService.save(postDTO);


        assertAll(
                () -> assertNotNull(actual),
                () -> assertNotNull(expected),
                () -> assertEquals(expected.getId(),actual.get().getId())
        );
    }

    @Test
    void testUpdate() {
        Instructor instructor = new Instructor();
        InstructorGetDTO expected = new InstructorGetDTO();
        lenient().when(instructorRepository.getById(anyLong())).thenReturn(instructor);
        lenient().when(instructorRepository.save(any())).thenReturn(instructor);
        lenient().when(mapStructMapperImp.instructorToInstructorGetDto(instructor)).thenReturn(expected);

        InstructorPostDTO postDTO = new InstructorPostDTO();
        Optional<InstructorGetDTO> actual = instructorService.update(postDTO);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.getId(),actual.get().getId())
        );

    }

    @Test
    void testUpdateSalary(){

    }

    @Test
    void testGetAllTransactionById(){
        Page<List<InstructorTransactionLogger>> expected = Page.empty();
        lenient().when(loggerRepository.findAllByInstructorId(anyLong(),any())).thenReturn(expected);

        Page<List<InstructorTransactionLogger>> actual = instructorService.getAllTransactionById(anyLong(),1,5,any());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected,actual),
                () -> assertEquals(expected.getSize(),actual.getSize())
        );
    }

    @Test
    void setGetAllTransactionByDate(){
        Page<List<InstructorTransactionLogger>> expected = Page.empty();
        lenient().when(loggerRepository.findAllTransactionByTransactionTime(any(),any())).thenReturn(expected);

        Page<List<InstructorTransactionLogger>> actual = instructorService.getAllTransactionByDate("10/09/2021",1,6,Pageable.unpaged());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected,actual),
                () -> assertEquals(expected.getSize(),actual.getSize())
        );
    }
}