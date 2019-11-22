package com.granjan.akka.tutorials.slick.jdbc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

    int id;
    int age;
    String name;
    String city;
}
