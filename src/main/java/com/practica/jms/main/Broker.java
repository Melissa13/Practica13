package com.practica.jms.main;

import com.practica.jms.modelo.Cliente1;
import com.practica.jms.DB.BootStrapService;
import org.apache.activemq.broker.BrokerService;

import javax.jms.JMSException;
import java.io.IOException;

public class Broker {

    public static void main(String[] args) throws IOException,JMSException {
        String cola = " notificaciones.cola";

        BootStrapService.getInstancia().init();

        BrokerService brokerService = new BrokerService();
        try {
            brokerService.addConnector("tcp://localhost:61616");
            brokerService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Cliente1().enviarMensaje(cola);
    }
}
