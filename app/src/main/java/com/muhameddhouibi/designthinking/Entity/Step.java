package com.muhameddhouibi.designthinking.Entity;

import java.util.List;

public class Step {
    String id_step;
    String description ;
    List<Instructions>instructions;

    public Step() {
    }

    public Step(String id_step, String description, List<Instructions> instructions) {
        this.id_step = id_step;
        this.description = description;
        this.instructions = instructions;
    }

    public String getId_step() {
        return id_step;
    }

    public void setId_step(String id_step) {
        this.id_step = id_step;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Instructions> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instructions> instructions) {
        this.instructions = instructions;
    }
}
