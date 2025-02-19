package com.example1.springWeek2.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;
import java.time.LocalDate;

//Other validate annotations: NotBlank, Email, Positive, Digits(integer=6, fraction=2), DecimalMax(value="100.44"),
//DecimalMin(value="22.5"), PastOrPresent, AssertTrue
//Pattern(regexp="^(ADMIN|USER)$"


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    private String email;
    private String name;
    private Long salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDto that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getName(), that.getName()) && Objects.equals(getSalary(), that.getSalary());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getName(), getSalary());
    }
}
