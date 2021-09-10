package com.sms.repository;

import com.sms.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {

    @Query(value = "SELECT count(i) FROM Instructor i WHERE i.phoneNumber = ?1")
    int existsByPhoneNumber(String phoneNumber);
}
