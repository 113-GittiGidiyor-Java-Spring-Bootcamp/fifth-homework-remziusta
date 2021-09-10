package com.sms.exceptions;

import com.sms.model.Logs;
import com.sms.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Date;
import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

    LogService logService;

    @Autowired
    public GlobalExceptionHandler(LogService logService) {
        this.logService = logService;
    }

    /**
     *
     * @param exc
     * @return This error is thrown if there is a instructor with the same phone number in the database.
     */
    @ExceptionHandler({InstructorIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Logs> handleException(InstructorIsAlreadyExistException exc){
        Logs response = prepareErrorResponse(HttpStatus.BAD_REQUEST, exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param exc
     * @return This error is thrown if there is a courses with the same course code in the database.
     */
    @ExceptionHandler({CourseIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Logs> handleException(CourseIsAlreadyExistException exc){
        Logs response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param exc
     * @return This error is thrown if the student age is less than 18 and greater than 40.
     */
    @ExceptionHandler({StudentAgeNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Logs> handleException(StudentAgeNotValidException exc){
        Logs response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param exc
     * @return This error is thrown,If the number of students taking a course is more than 20.
     */
    @ExceptionHandler({StudentNumberForOneCourseExcededException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Logs> handleException(StudentNumberForOneCourseExcededException exc){
        Logs response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param httpStatus
     * @param message your custom message
     * @return the log added to the database
     */
    private Logs prepareErrorResponse(HttpStatus httpStatus, String message) {
        Logs response = new Logs();
        response.setStatus(httpStatus.value());
        response.setMessage(message);
        response.setCreated(Date.valueOf(LocalDate.now()));
        return logService.save(response).get();

    }
}
