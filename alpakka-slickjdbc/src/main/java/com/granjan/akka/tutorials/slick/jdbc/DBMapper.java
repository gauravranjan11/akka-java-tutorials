package com.granjan.akka.tutorials.slick.jdbc;

import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

public class DBMapper {

    public static String getUpdateQuery(String city, int id) {
        Map<String, String> mapper = new HashMap<>();

        mapper.put("city", city);
        mapper.put("id", Integer.toString(id));

        return StringSubstitutor.replace(DBQueries.updateEmployee, mapper, "<", ">");
    }

    public static String getDeleteQuery(int id) {
        Map<String, String> mapper = new HashMap<>();

        mapper.put("id", Integer.toString(id));

        return StringSubstitutor.replace(DBQueries.deleteEmployee, mapper, "<", ">");
    }

    public static String getinsertQuery(String city, String name, int age, int id) {

        Map<String, String> mapper = new HashMap<>();

        mapper.put("city", city);
        mapper.put("id", Integer.toString(id));
        mapper.put("age", Integer.toString(age));
        mapper.put("name", name);

        return StringSubstitutor.replace(DBQueries.createEmployee, mapper, "<", ">");
    }
}
