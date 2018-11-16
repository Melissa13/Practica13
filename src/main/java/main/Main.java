package main;

import DB.BootStrapService;
import DB.DataService;
import jms.Broker;
import jms.CLiente2;
import jms.Cliente1;
import org.apache.activemq.broker.BrokerService;

import javax.jms.JMSException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, JMSException {

        BootStrapService.getInstancia().init();

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
        new CLiente2().EnviarMensaje(cola);

        consumidor.cerrarConexion();

        



    }

}
