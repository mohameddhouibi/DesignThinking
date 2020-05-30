package com.muhameddhouibi.designthinking.Entity;

public class Room {

    String room_id ;
    String room_name ;
    String nb_of_players ;
    String Creator ;
    String code ;

    public Room() {
    }

    public Room(String room_id, String room_name, String nb_of_players, String creator, String code) {
        this.room_id = room_id;
        this.room_name = room_name;
        this.nb_of_players = nb_of_players;
        Creator = creator;
        this.code = code;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getNb_of_players() {
        return nb_of_players;
    }

    public void setNb_of_players(String nb_of_players) {
        this.nb_of_players = nb_of_players;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
