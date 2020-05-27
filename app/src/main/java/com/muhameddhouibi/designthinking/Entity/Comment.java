package com.muhameddhouibi.designthinking.Entity;

public class Comment {

    String Comment ;
    String publisher ;

    public Comment() {
    }

    public Comment(String comment, String publisher) {
        Comment = comment;
        this.publisher = publisher;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
