package com.muhameddhouibi.designthinking.Entity;

public class Question {
    String QstId ;
    String Question ;
    String True_Flase ;

    public Question() {

    }

    public Question(String qstId, String question, String true_Flase) {
        QstId = qstId;
        Question = question;
        True_Flase = true_Flase;
    }

    public String getQstId() {
        return QstId;
    }

    public void setQstId(String qstId) {
        QstId = qstId;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getTrue_Flase() {
        return True_Flase;
    }

    public void setTrue_Flase(String true_Flase) {
        True_Flase = true_Flase;
    }
}
