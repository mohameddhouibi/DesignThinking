package com.muhameddhouibi.designthinking.Entity;

public class Question {
    String Qstnbr ;
    String Question ;
    String Username;

    public Question() {
    }

    public Question(String qstnbr, String question, String username) {
        Qstnbr = qstnbr;
        Question = question;
        Username=username;
    }

    public String getQstnbr() {
        return Qstnbr;
    }

    public void setQstnbr(String qstnbr) {
        Qstnbr = qstnbr;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
