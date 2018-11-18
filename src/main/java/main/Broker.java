package main;

import DB.BootStrapService;
import jms.Reciver;
import jms.Cliente1;
import org.apache.activemq.broker.BrokerService;

import javax.jms.JMSException;
import java.io.IOException;

public class Broker {

    public static void main(String[] args) throws IOException, JMSException, InterruptedException {

        BootStrapService.getInstancia().init();

        String cola = "pruebajms.cola";

        BrokerService broker = new BrokerService();

        try {
            broker.addConnector("tcp://0.0.0.0:61616");
            broker.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Cliente1().EnviarMensaje(cola);

    }

}