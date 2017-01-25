package com.example.restclient.model;

public class TestModel {
    String name;
    String age;

    @Override
    public String toString() {
        return "name=" + name + '\t' + ", age=" + age;
    }
}
