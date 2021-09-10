package com.sms.controller;

import com.sms.dto.InstructorGetDTO;
import com.sms.dto.InstructorPostDTO;
import com.sms.dto.InstructorPutSalaryDTO;
import com.sms.model.InstructorTransactionLogger;
import com.sms.service.InstructorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstructorControllerTest {

    @Mock
    InstructorService instructorService;

    @InjectMocks
    InstructorController instructorController;

    @Test
    void testSave() {
        InstructorGetDTO instructorGetDTO = new InstructorGetDTO();
        Optional<InstructorGetDTO> expected = Optional.of(instructorGetDTO);
        when(instructorService.save(any())).thenReturn(expected);

        InstructorPostDTO instructorPostDTO = new InstructorPostDTO();
        InstructorGetDTO actual = instructorController.save(instructorPostDTO).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(),actual),
                () -> assertEquals(expected.get().getId(),actual.getId())
        );

    }

    @Test
    void testGetAllInstructors() {
        List<InstructorGetDTO> list = new ArrayList<>();
        Optional<List<InstructorGetDTO>> expected = Optional.of(list);
        when(instructorService.findAll()).thenReturn(expected);

        List<InstructorGetDTO> actual = instructorController.getAllInstructors().getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(),actual),
                () -> assertEquals(expected.get().size(),actual.size())
        );
    }

    @Test
    void testGetInstructor() {
        InstructorGetDTO instructorGetDTO = new InstructorGetDTO();
        Optional<InstructorGetDTO> expected = Optional.of(instructorGetDTO);
        when(instructorService.findById(anyLong())).thenReturn(expected);

        InstructorGetDTO actual = instructorController.getInstructor(anyLong()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(), actual)
        );
    }

    @Test
    void testUpdateInstructor() {
        InstructorGetDTO instructorGetDTO = new InstructorGetDTO();
        Optional<InstructorGetDTO> expected = Optional.of(instructorGetDTO);
        when(instructorService.update(any())).thenReturn(expected);

        InstructorPostDTO postDTO = new InstructorPostDTO();
        InstructorGetDTO actual = instructorController.updateInstructor(any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get().getId(),actual.getId())
        );
    }

    @Test
    void testDeleteInstructor() {
        InstructorGetDTO instructorGetDTO = new InstructorGetDTO();
        Optional<InstructorGetDTO> ex = Optional.of(instructorGetDTO);
        when(instructorService.findById(anyLong())).thenReturn(ex);
        String expected = ex.get().getName() + " is deleted";

        String actual = instructorController.deleteInstructor(anyLong()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected,actual)
        );

    }

    @Test
    void testAddInstructorCourseRelationship(){
        List<InstructorGetDTO> instructorList = new ArrayList<>();
        Optional<List<InstructorGetDTO>> excepted = Optional.of(instructorList);
        when(instructorService.setInstructorCourseRelationship(anyLong(),anyLong())).thenReturn(excepted);

        List<InstructorGetDTO> actual = instructorController.addInstructorCourseRelationship(anyLong(),anyLong()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(excepted.get(),actual),
                () -> assertEquals(excepted.get().size(),actual.size())
        );
    }

    @Test
    void testUpdateSalary(){
        InstructorGetDTO instructorGetDTO = new InstructorGetDTO();
        Optional<InstructorGetDTO> expected = Optional.of(instructorGetDTO);
        when(instructorService.updateSalary(any(),anyBoolean())).thenReturn(expected);

        InstructorPutSalaryDTO instructorPutSalaryDTO = new InstructorPutSalaryDTO();
        InstructorGetDTO actual = instructorController.increaseSalary(instructorPutSalaryDTO).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get().getId(),actual.getId())
        );
    }

    @Test
    void testGetAllTransactionsWithDate(){
        Page<List<InstructorTransactionLogger>> excepted = Page.empty();
        when(instructorService.getAllTransactionByDate(anyString(),anyInt(),anyInt(),any())).thenReturn(excepted);

        Page<List<InstructorTransactionLogger>> actual = instructorController.getAllTransactionsWithDate(anyString(),anyInt(),anyInt(),any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(excepted,actual),
                () -> assertEquals(excepted.get().count(),actual.get().count())
        );
    }

    @Test
    void testGetAllTransactionsWithId(){
        Page<List<InstructorTransactionLogger>> excepted = Page.empty();
        when(instructorService.getAllTransactionById(anyLong(),anyInt(),anyInt(),any())).thenReturn(excepted);

        Page<List<InstructorTransactionLogger>> actual = instructorController.getAllTransactionsWithId(anyLong(),anyInt(),anyInt(),any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(excepted,actual),
                () -> assertEquals(excepted.get().count(),actual.get().count())
        );
    }


}