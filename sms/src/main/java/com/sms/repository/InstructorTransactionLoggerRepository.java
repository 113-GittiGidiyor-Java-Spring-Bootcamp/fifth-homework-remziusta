package com.sms.repository;

import com.sms.model.InstructorTransactionLogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InstructorTransactionLoggerRepository extends PagingAndSortingRepository<InstructorTransactionLogger, Long> {

    @Query("SELECT i FROM InstructorTransactionLogger i WHERE i.transactionTime= ?1")
    Page<List<InstructorTransactionLogger>> findAllTransactionByTransactionTime(LocalDate transactionDate, Pageable pageable);

    Page<List<InstructorTransactionLogger>> findAllByInstructorId(Long id,Pageable pageable);

}
