package com.sms.repository;

import com.sms.model.Logs;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Logs, Long> {

    @Query(value = "SELECT l FROM Logs l where l.status = ?1")
    List<Logs> getAllByStatus(int status);

    List<Logs> findByCreated(Date date);

}
