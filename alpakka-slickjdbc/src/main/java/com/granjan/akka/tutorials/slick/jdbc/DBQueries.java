package com.granjan.akka.tutorials.slick.jdbc;

public class DBQueries {

    public final static String selectEmployees = "select id,age,name,city from  employees";
    public final static String updateEmployee = "update employees set city='<city>' where id=<id>";
    public final static String createEmployee = "insert into employees values(<id>,'<name>',<age>,'<city>')";
    public final static String deleteEmployee = "delete from employees where id=<id>";
}