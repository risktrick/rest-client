package com.example.restclient.model;

import com.example.restclient.utils.ILogger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ModelJsonParser {

    void parseBashJson(String response, ILogger logger){
        Gson gson = new Gson();
        BashModel[] bashModelArray = gson.fromJson(response, BashModel[].class);
        for (BashModel bashModel : bashModelArray) {
            logger.log(bashModel.elementPureHtml);
        }
    }

    public TestModel parseOneTestModel(String response){
        Gson gson = new Gson();
        TestModel model = gson.fromJson(response, TestModel.class);
        return model;
    }

    public TestModel[] parseArrayTestModel(String response) {
        Gson gson = new Gson();
        TestModel[] models = gson.fromJson(response, TestModel[].class);
        return models;
    }

    public void parseArrayTestModelNewMethod(String response, ILogger logger) {
        Gson gson = new Gson();
        List<TestModel> models = gson.fromJson(response, new TypeToken<List<TestModel>>() {}.getType());
        for (TestModel model : models) {
            logger.log(model.toString());
        }
    }

    public void parseArrayOfArrayTestModel(String response, ILogger logger) {
        Gson gson = new Gson();
        List<List<TestModel>> modelss = gson.fromJson(response, new TypeToken<List<List<TestModel>>>() {}.getType());
        for (List<TestModel> models : modelss) {
            for (TestModel testModel : models) {
                logger.log(testModel.toString());
            }
        }
    }

    public List<List<SourceModel>> parseSources(String response, ILogger logger){
        Gson gson = new Gson();
        List<List<SourceModel>> modelss = gson.fromJson(response, new TypeToken<List<List<SourceModel>>>() {}.getType());

        for (List<SourceModel> models : modelss) {
            for (SourceModel sourceModel : models) {
                logger.log(sourceModel.toString());
            }
        }

        return modelss;
    }

    public BashModel[] parseBash(String response) {
        Gson gson = new Gson();
        BashModel[] models = gson.fromJson(response, BashModel[].class);
        return models;
    }
}
