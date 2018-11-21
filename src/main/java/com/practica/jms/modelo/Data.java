package com.practica.jms.modelo;

import java.text.SimpleDateFormat;
import javax.persistence.*;
import java.time.Instant;
import java.util.Date;


public class Data {

    private int id;

    private int idDispositivo;
    private Long temperatura;
    private Long humedad;
    private String fecha;

    public Data() {

        this.id = id;
        this.idDispositivo = idDispositivo;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.fecha = fecha;

    }

    public Data(int id,int idDispositivo, Long temperatura, Long humedad, String fecha) {
        this.id = id;
        this.idDispositivo = idDispositivo;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Long getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Long temperatura) {
        this.temperatura = temperatura;
    }

    public Long getHumedad() {
        return humedad;
    }

    public void setHumedad(Long humedad) {
        this.humedad = humedad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
