package com.sms.service;

import com.sms.model.Logs;
import com.sms.repository.LogRepository;
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
class LogServiceTest {

    @Mock
    LogRepository logRepository;

    @InjectMocks
    LogService logService;

    @Test
    void testFindAll() {
        List<Logs> expected = new ArrayList<>();
        lenient().when(logRepository.findAll()).thenReturn(expected);

        Optional<List<Logs>> actual = logService.findAll();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected,actual.get()),
                () -> assertEquals(expected.size(),actual.get().size())
        );
    }

    @Test
    void testSave() {
        Logs expected = new Logs();
        lenient().when(logRepository.save(any())).thenReturn(expected);

        Optional<Logs> actual = logService.save(expected);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected,actual.get())
        );
    }

    @Test
    void testFindAllByStatus() {
        List<Logs> expected = new ArrayList<>();
        lenient().when(logRepository.getAllByStatus(anyInt())).thenReturn(expected);

        Optional<List<Logs>> actual = logService.findAllByStatus(anyInt());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected,actual.get()),
                () -> assertEquals(expected.size(),actual.get().size())
        );
    }

    @Test
    void testGetByTimestampBetween() {
        List<Logs> expected = new ArrayList<>();
        lenient().when(logRepository.findByCreated(any())).thenReturn(expected);

        Optional<List<Logs>> actual = logService.getByTimestampBetween(any());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected,actual.get()),
                () -> assertEquals(expected.size(),actual.get().size())
        );
    }
}