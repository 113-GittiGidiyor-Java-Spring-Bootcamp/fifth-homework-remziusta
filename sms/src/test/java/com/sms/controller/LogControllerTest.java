package com.sms.controller;

import com.sms.dto.InstructorGetDTO;
import com.sms.model.Logs;
import com.sms.service.LogService;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogControllerTest {

    @Mock
    LogService logService;

    @InjectMocks
    LogController logController;

    @Test
    void testGetAllLogs() {
        List<Logs> list = new ArrayList<>();
        Optional<List<Logs>> expected = Optional.of(list);
        when(logService.findAll()).thenReturn(expected);

        List<Logs> actual = logController.getAllLogs().getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(),actual),
                () -> assertEquals(expected.get().size(),actual.size())
        );
    }

    @Test
    void testGetAllByStatus() {
        List<Logs> list = new ArrayList<>();
        Optional<List<Logs>> expected = Optional.of(list);
        when(logService.findAllByStatus(anyInt())).thenReturn(expected);

        List<Logs> actual = logController.getAllByStatus(anyInt()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(),actual),
                () -> assertEquals(expected.get().size(),actual.size())
        );
    }

    @Test
    void testGetLogsByDateAt() {
        List<Logs> list = new ArrayList<>();
        Optional<List<Logs>> expected = Optional.of(list);
        when(logService.getByTimestampBetween(any())).thenReturn(expected);

        List<Logs> actual = logController.getLogsByDateAt(any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(),actual),
                () -> assertEquals(expected.get().size(),actual.size())
        );
    }
}