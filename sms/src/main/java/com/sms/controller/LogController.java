package com.sms.controller;

import com.sms.model.Logs;
import com.sms.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/logs")
public class LogController {

    LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    /**
     *
     * @return All Logs
     */
    @GetMapping(value = "")
    public ResponseEntity<List<Logs>> getAllLogs(){
        return new ResponseEntity<>(logService.findAll().get(), HttpStatus.OK);
    }

    /**
     *
     * @param status is error type
     * @return logs according to error type
     */
    @GetMapping(value = "/{status}")
    public ResponseEntity<List<Logs>> getAllByStatus(@PathVariable  int status){
        return new ResponseEntity<>(logService.findAllByStatus(status).get(),HttpStatus.OK);
    }

    /**
     *
     * @param date first date
     * @return logs between dates
     */
    @PostMapping(value = "/{date}")
    public ResponseEntity<List<Logs>> getLogsByDateAt(@PathVariable Date date){
        return new ResponseEntity<>(logService.getByTimestampBetween(date).get(),HttpStatus.OK);

    }
}
