package com.granjan.akka.tutorials.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeResponseDO {
    String id;
    String name;
    String salary;
    String age;
    String employee_name;
    String employee_salary;
    String employee_age;
    String profile_image;

}
