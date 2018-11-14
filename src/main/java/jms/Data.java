package jms;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {

    String date; //new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    int id;
    float temperatura;
    float humedad;

    public Data() {
        this.date = date;
        this.id = id;
        this.temperatura = temperatura;
        this.humedad = humedad;
    }

    public Data(String date, int id, float temperatura, float humedad) {
        this.date = date;
        this.id = id;
        this.temperatura = temperatura;
        this.humedad = humedad;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getHumedad() {
        return humedad;
    }

    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }
}
