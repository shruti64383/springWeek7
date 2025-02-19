package com.example1.springWeek2.services.impl;

import com.example1.springWeek2.dto.EmployeeDto;
import com.example1.springWeek2.entities.EmployeeEntity;
import com.example1.springWeek2.repositories.EmployeeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private EmployeeEntity mockEmployee;
    private EmployeeDto mockEmployeeDto;

    @BeforeEach
    void setUp(){
        mockEmployee = EmployeeEntity.builder()
                .id(1L)
                .email("xyz@gmail.com")
                .name("Pikachu")
                .salary(100L)
                .build();
        mockEmployeeDto = modelMapper.map(mockEmployee, EmployeeDto.class);
    }

    @Test
    void testGetEmployeeById_whenEmployeeIdIsPresent_thenReturnEmployeeDto(){
        //when
        Long id = mockEmployee.getId();
        when(employeeRepo.findById(id)).thenReturn(Optional.of(mockEmployee));

        //act
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);

        //assert
        assertThat(employeeDto.getId()).isEqualTo(mockEmployee.getId());
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployee.getEmail());
        verify(employeeRepo, times(1)).findById(any());

    }

    @Test
    void testCreateNewEmployee_whenValidEmployee_thenCreateEmployee(){
         when(employeeRepo.findByEmail(anyString())).thenReturn(List.of());
         when(employeeRepo.save(any(EmployeeEntity.class))).thenReturn(mockEmployee);

         EmployeeDto employeeDto = employeeService.createNewEmployee(mockEmployeeDto);

         assertThat(employeeDto).isNotNull();
         assertThat(employeeDto.getEmail()).isEqualTo(mockEmployeeDto.getEmail());

        ArgumentCaptor<EmployeeEntity> capturedEmployee = ArgumentCaptor.forClass(EmployeeEntity.class);
        verify(employeeRepo).save(capturedEmployee.capture());

        EmployeeEntity employee = capturedEmployee.getValue();
        assertThat(employee.getEmail()).isEqualTo(mockEmployee.getEmail());
    }


}