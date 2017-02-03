package com.example.restclient.model;

public class BashModel {
    public String site;
    public String name;
    public String desc;
    public String link;
    public String elementPureHtml;

    @Override
    public String toString() {
        return "BashModel{" +
                "site='" + site + '\'' + "\n" +
                ", name='" + name + '\'' + "\n" +
                ", desc='" + desc + '\'' + "\n" +
                ", link='" + link + '\'' + "\n" +
                ", elementPureHtml='" + elementPureHtml + '\'' + "\n" +
                '}';
    }
}
