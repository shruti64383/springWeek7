package com.example1.springWeek2.repositories;

import com.example1.springWeek2.TestContainerConfiguration;
import com.example1.springWeek2.entities.EmployeeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DataJpaTest
//@Import(TestContainerConfiguration.class)
class EmployeeRepoTest {

    @Autowired
    private EmployeeRepo employeeRepo;

    private EmployeeEntity employeeEntity;

    @BeforeEach
    void setUp() {
        employeeEntity = EmployeeEntity.builder()

                .name("Anuj")
                .email("anuj@gmail.com")
                .salary(100L)
                .build();
    }

    @Test
    void testFindByEmail_whenEmailIsPresent_thenReturnEmployee() {
//        Arrange, Given
        employeeRepo.save(employeeEntity);

//        Act, When
        List<EmployeeEntity> employeeList = employeeRepo.findByEmail(employeeEntity.getEmail());

//        Assert, Then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isNotEmpty();
        assertThat(employeeList.get(0).getEmail()).isEqualTo(employeeEntity.getEmail());
    }

//    @BeforeEach
//    void setUp(){
//        employeeEntity = EmployeeEntity.builder()
//                .id(1L)
//                .name("Shruti")
//                .email("k@gmail.com")
//                .salary(100L)
//                .build();
//    }
//
//    @Test
//    void testFindByEmail_whenEmailIsPresent_thenReturnEmployee() {
//        //Given
//        employeeRepo.save(employeeEntity);
//
//        //When
//        //List<EmployeeEntity> employeeList = employeeRepo.findByEmail(employeeEntity.getEmail());
//
//        //Then
//        //assertThat(employeeList).isNotEmpty();
//        //assertThat(employeeList).isNotNull();
//        //assertThat(employeeList.get(0).getEmail()).isEqualTo(employeeEntity.getEmail());
//
//    }

    @Test
    void testFindByEmail_whenEmailIsNotFound_thenReturnEmptyEmployeeList() {
        //Given
        String email = "xyz@gmail.com";

        //When
        List<EmployeeEntity> employeeList = employeeRepo.findByEmail(email);

        //Then
        assertThat(employeeList).isEmpty();
        //assertThat(employeeList).isNull();
    }
}