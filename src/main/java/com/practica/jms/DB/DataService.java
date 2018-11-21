package com.practica.jms.DB;

import com.practica.jms.modelo.Data;
import com.practica.jms.DB.DataService;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DataService extends DataBaseService<Data> {

    private static DataService instancia;

    private DataService() {
        super(Data.class);
    }

    public static DataService getInstancia(){
        if(instancia==null){
            instancia = new DataService();
        }
        return instancia;
    }
}
