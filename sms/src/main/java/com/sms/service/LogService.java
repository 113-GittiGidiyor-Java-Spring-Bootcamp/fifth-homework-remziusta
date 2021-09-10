package com.sms.service;

import com.sms.model.Logs;
import com.sms.repository.LogRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LogService {

    LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Transactional(readOnly = true)
    public Optional<List<Logs>> findAll(){
        return Optional.of(logRepository.findAll());
    }

    @Transactional
    public Optional<Logs> save(Logs logs){
        return Optional.of(logRepository.save(logs));
    }

    @Transactional(readOnly = true)
    public Optional<List<Logs>> findAllByStatus(int status){
        return Optional.of(logRepository.getAllByStatus(status));
    }

    @Transactional(readOnly = true)
    public Optional<List<Logs>> getByTimestampBetween(Date date){
        return Optional.of(logRepository.findByCreated(date));
    }
}
