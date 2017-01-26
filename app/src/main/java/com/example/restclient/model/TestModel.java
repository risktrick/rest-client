package com.example.restclient.model;

public class TestModel {
    String name;
    String age;

    public TestModel(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name=" + name + '\t' + ", age=" + age;
    }
}
