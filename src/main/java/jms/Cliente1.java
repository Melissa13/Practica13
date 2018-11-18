package jms;

import DB.DataService;
import org.apache.activemq.ActiveMQConnectionFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cliente1 {

    ActiveMQConnectionFactory factory;
    Connection connection;
    Session session;
    Queue queue;
    Topic topic;
    MessageConsumer consumer;
    String cola;

    public Cliente1() {

    }

    /**
     *
     * @param cola\
     * @throws Exception
     */

    public void EnviarMensaje(String cola) throws JMSException, JsonProcessingException, InterruptedException {


        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Connection connection = factory.createConnection("admin", "admin");

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(cola);

        MessageProducer producer = session.createProducer(topic);

        Data data = returnData();

        while(true)
        {
            TimeUnit.SECONDS.sleep(2);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(data);
            DataService.getInstancia().crear(data);
            TextMessage message = session.createTextMessage(json);
            producer.send(message);
        }

       // producer.close();
       // session.close();
       // connection.stop();
    }

    public Data returnData()
    {
        Random r = new Random();
        Data data = new Data();
        data.idcliente = 1;
        data.date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        data.humedad = 10 + r.nextFloat() * (50 - 10);
        data.temperatura = 0 + r.nextFloat() * (65 - 0);

        return data;
    }
}