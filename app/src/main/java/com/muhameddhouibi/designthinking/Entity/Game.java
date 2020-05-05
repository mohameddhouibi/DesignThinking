package com.muhameddhouibi.designthinking.Entity;

import java.sql.Timestamp;
import java.util.List;

public class Game {
   String RoomName;
   String Room_id ;
   String Player1;
   String Player2;
   String Player3;
   String Player4;
   String Player5;
   int Nb_players;

    public Game() {
    }

    public Game(String roomName, String room_id, String player1, String player2, String player3, String player4, String player5, int nb_players) {
        RoomName = roomName;
        Room_id = room_id;
        Player1 = player1;
        Player2 = player2;
        Player3 = player3;
        Player4 = player4;
        Player5 = player5;
        Nb_players = nb_players;
    }

    public int getNb_players() {
        return Nb_players;
    }

    public void setNb_players(int nb_players) {
        Nb_players = nb_players;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getRoom_id() {
        return Room_id;
    }

    public void setRoom_id(String room_id) {
        Room_id = room_id;
    }

    public String getPlayer1() {
        return Player1;
    }

    public void setPlayer1(String player1) {
        Player1 = player1;
    }

    public String getPlayer2() {
        return Player2;
    }

    public void setPlayer2(String player2) {
        Player2 = player2;
    }

    public String getPlayer3() {
        return Player3;
    }

    public void setPlayer3(String player3) {
        Player3 = player3;
    }

    public String getPlayer4() {
        return Player4;
    }

    public void setPlayer4(String player4) {
        Player4 = player4;
    }

    public String getPlayer5() {
        return Player5;
    }

    public void setPlayer5(String player5) {
        Player5 = player5;
    }
}
