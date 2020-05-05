package com.muhameddhouibi.designthinking.Entity;

public class Instructions {

    String id_instruction ;
    String description ;
    String example ;

    public Instructions() {
    }

    public Instructions(String id_instruction, String description, String example) {
        this.id_instruction = id_instruction;
        this.description = description;
        this.example = example;
    }

    public String getId_instruction() {
        return id_instruction;
    }

    public void setId_instruction(String id_instruction) {
        this.id_instruction = id_instruction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
