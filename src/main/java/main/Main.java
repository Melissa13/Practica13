package main;

import jms.Broker;
import jms.Cliente1;
import org.apache.activemq.broker.BrokerService;

import javax.jms.JMSException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, JMSException {

        String cola = "pruebajms.cola";

        BrokerService broker = new BrokerService();

        try {
            broker.addConnector("tcp://0.0.0.0:61616");
            broker.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Broker consumidor=new Broker(cola);
        consumidor.conectar();

        new Cliente1().EnviarMensaje(cola);

        consumidor.cerrarConexion();



    }

}
