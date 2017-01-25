package com.example.restclient.model;

import com.example.restclient.ILogger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JsonParser {

    void parseBashJson(String response, ILogger logger){
        Gson gson = new Gson();
        BashModel[] bashModelArray = gson.fromJson(response, BashModel[].class);
        for (BashModel bashModel : bashModelArray) {
            logger.log(bashModel.elementPureHtml);
        }
    }

    public void parseOneTestModel(String response, ILogger logger){
        Gson gson = new Gson();
        TestModel model = gson.fromJson(response, TestModel.class);
        logger.log(model.toString());
    }

    public void parseArrayTestModel(String response, ILogger logger) {
        Gson gson = new Gson();
        TestModel[] models = gson.fromJson(response, TestModel[].class);
        for (TestModel model : models) {
            logger.log(model.toString());
        }
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

    public void parseSources(String response, ILogger logger){
        Gson gson = new Gson();
        List<List<SourceModel>> modelss = gson.fromJson(response, new TypeToken<List<List<SourceModel>>>() {}.getType());
        for (List<SourceModel> models : modelss) {
            for (SourceModel sourceModel : models) {
                logger.log(sourceModel.toString());
            }
        }
    }
}
