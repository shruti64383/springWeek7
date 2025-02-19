package com.example1.springWeek2.repositories;

import com.example1.springWeek2.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findByEmail(String email);
}
