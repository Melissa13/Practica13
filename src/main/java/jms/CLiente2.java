package jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CLiente2 {

    ActiveMQConnectionFactory factory;
    Connection connection;
    Session session;
    Queue queue;
    Topic topic;
    MessageConsumer consumer;
    String cola;

    public CLiente2(){
    }

    /**
     *
     * @param cola\
     * @throws Exception
     */

    public void EnviarMensaje(String cola) throws JMSException, JsonProcessingException
    {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Connection connection = factory.createConnection("admin", "admin");

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(cola);

        MessageProducer producer = session.createProducer(topic);


        Data data = returnData();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(data);

        TextMessage message = session.createTextMessage(json);
        producer.send(message);


        producer.close();
        session.close();
        connection.stop();
    }

    public Data returnData()
    {
        Random r = new Random();
        Data data = new Data();
        data.id = 2;
        data.date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        data.humedad = 10 + r.nextFloat() * (50 - 10);
        data.temperatura = 0 + r.nextFloat() * (65 - 0);

        return data;
    }

}
