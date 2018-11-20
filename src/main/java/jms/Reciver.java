package jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.Main;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Reciver {

    ActiveMQConnectionFactory factory;
    Connection connection;
    Session session;
    Queue queue;
    Topic topic;
    MessageConsumer consumer;
    String cola;

    public Reciver(String cola)
    {
        this.cola = cola;
    }

    /**
     *
     * @throws JMSException
     */
    public void conectar() throws JMSException, JsonProcessingException {

        factory = new ActiveMQConnectionFactory("admin", "1234","failover:tcp://localhost:61616");

        connection = factory.createConnection();

        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //topic = session.createTopic(cola);
        queue = session.createQueue(cola);

        consumer = session.createConsumer(queue);

        consumer.setMessageListener(message ->  {

                try {
                    TextMessage messageTexto = (TextMessage) message;
                    Main.enviarMensaje(messageTexto.getText());
                    System.out.println("El mensaje de texto recibido: " + messageTexto.getText());
                }catch(Exception ex){
                    ex.printStackTrace();
                }
        });

    }

    public void cerrarConexion() throws JMSException {
        connection.stop();
        connection.close();
    }

}
