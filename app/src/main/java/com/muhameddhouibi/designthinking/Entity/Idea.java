package com.muhameddhouibi.designthinking.Entity;

public class Idea {
    String id ;
    String sector ;
    String problem ;
    String idea ;
    String date ;
    String username ;

    public Idea() {
    }

    public Idea(String id, String sector, String problem, String idea, String date, String username) {
        this.id = id;
        this.sector = sector;
        this.problem = problem;
        this.idea = idea;
        this.date = date;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
