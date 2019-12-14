package mysport;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
//import java.time.LocalTime;
//import java.time.LocalTime;

import java.text.SimpleDateFormat;
public class Reservation {
    String date, heureDebut, heureFin;
    int nombreDePlace,idAnnonce, idUser,idReservation;

    public Reservation(String date, String heureDebut, String heureFin, int nombreDePlace, int idAnnonce, int idUser, int idReservation) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.nombreDePlace = nombreDePlace;
        this.idAnnonce = idAnnonce;
        this.idUser = idUser;
        this.idReservation = idReservation;
    }

    public Reservation() {
    }

    public int getIdReservation() {
        return idReservation;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNombreDePlace() {
        return nombreDePlace;
    }

    public void setNombreDePlace(int nombreDePlace) {
        this.nombreDePlace = nombreDePlace;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation=idReservation;}
}
