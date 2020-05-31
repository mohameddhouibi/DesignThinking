package com.muhameddhouibi.designthinking.Entity;

import java.util.Date;

public class Invitation {
    String Invitation_id;
    String Game_id ;
    String Sender_uid ;
    String Desitination_uid ;
    String Privacy ;

    public Invitation() {
    }


    public Invitation(String invitation_id, String game_id, String sender_uid, String desitination_uid,String privacy ) {
        Invitation_id = invitation_id;
        Game_id = game_id;
        Sender_uid = sender_uid;
        Desitination_uid = desitination_uid;
        Privacy = privacy ;
    }

    public String getPrivacy() {
        return Privacy;
    }

    public void setPrivacy(String privacy) {
        Privacy = privacy;
    }

    public String getInvitation_id() {
        return Invitation_id;
    }

    public void setInvitation_id(String invitation_id) {
        Invitation_id = invitation_id;
    }

    public String getGame_id() {
        return Game_id;
    }

    public void setGame_id(String game_id) {
        Game_id = game_id;
    }

    public String getSender_uid() {
        return Sender_uid;
    }

    public void setSender_uid(String sender_uid) {
        Sender_uid = sender_uid;
    }

    public String getDesitination_uid() {
        return Desitination_uid;
    }

    public void setDesitination_uid(String desitination_uid) {
        Desitination_uid = desitination_uid;
    }
}
