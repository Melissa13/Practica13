package com.broker.mensajeria.Classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@ToString(exclude = "id")
@NoArgsConstructor
public class Temperaturetransaction {


    @Id private int id;
    String fechaGeneracion;
    double temperatura;
    double humedad;

    public Temperaturetransaction(int id, String fechaGeneracion, double temperatura, double humedad) {
        this.id = id;
        this.fechaGeneracion = fechaGeneracion;
        this.temperatura = temperatura;
        this.humedad = humedad;
    }
}
