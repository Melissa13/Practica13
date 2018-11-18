package jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
        Random r = new Random();
        Data data = new Data();
        data.id = 1;
        data.date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        data.humedad = 10 + r.nextFloat() * (50 - 10);
        data.temperatura = 0 + r.nextFloat() * (65 - 0);





        factory = new ActiveMQConnectionFactory("admin", "1234","failover:tcp://localhost:61616");

        connection = factory.createConnection();

        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        topic = session.createTopic(cola);

        consumer = session.createConsumer(topic);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {


                    TextMessage messageTexto = (TextMessage) message;
                    System.out.println("El mensaje de texto recibido: " + messageTexto.getText());
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });

    }

    public void cerrarConexion() throws JMSException {
        connection.stop();
        connection.close();
    }

}
