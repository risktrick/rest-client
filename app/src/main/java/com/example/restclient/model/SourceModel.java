package com.example.restclient.model;

public class SourceModel {
    private String site;
    private String desc;
    private String linkpar;
    private String name;
    private String encoding;
    private String parsel;
    private String url;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLinkpar() {
        return linkpar;
    }

    public void setLinkpar(String linkpar) {
        this.linkpar = linkpar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getParsel() {
        return parsel;
    }

    public void setParsel(String parsel) {
        this.parsel = parsel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SourceModel [site = " + site + ", desc = " + desc + ", linkpar = " + linkpar + ", name = " + name + ", encoding = " + encoding + ", parsel = " + parsel + ", url = " + url + "]";
    }
}
