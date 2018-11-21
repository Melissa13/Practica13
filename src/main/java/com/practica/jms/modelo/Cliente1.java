package com.practica.jms.modelo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnectionFactory;
import com.practica.jms.DB.DataService;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Cliente1 {

    public Cliente1() {

    }

    /**
     * @param cola
     * @throws Exception
     */
    public void enviarMensaje(String cola) throws JMSException, JsonProcessingException {

        //Creando el connection factory indicando el host y puerto, en la trama el failover indica que reconecta de manera
        // automatica
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        //Crea un nuevo hilo cuando hacemos a conexión, que no se detiene cuando
        // aplicamos el metodo stop(), para eso tenemos que cerrar la JVM o
        // realizar un close().
        Connection connection = factory.createConnection("admin", "admin");
        connection.start();

        // Creando una sesión no transaccional y automatica.
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Creamos o nos connectamos a la una cola, por defecto ActiveMQ permite
        // la creación si no existe. Si la cola es del tipo Queue es acumula los mensajes, si es
        // del tipo topic es en el momento.

        Queue queue = session.createQueue(cola);
//        Topic topic = session.createTopic(cola);


        // Creando el objeto de referencia para enviar los mensajes.
        MessageProducer producer = session.createProducer(queue);


//        String mensaje = mensajeEnviar;
        Data data;

        // Creando un simple mensaje de texto y enviando.

        while (true) {
            try {
                TimeUnit.SECONDS.sleep(2);
                data = returndata();
                //Haciendo la data JSON
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(data);
                //Entrando a la base de datos
                //DataService.getInstancia().crear(data);
                //Mandando el mensaje
                TextMessage message = session.createTextMessage(json);
//            System.out.println("hello");
                producer.send(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        //Desconectando la referencia.
//        producer.close();
//        session.close();
//        connection.stop();
//
//        System.exit(0);
    }

    Data returndata()
    {
        Data data = new Data();
        //         DD/MM/YYYY HH:mm:ss
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");
        Date date = new Date();
        data.setIdDispositivo((int) (Math.random() * 100) + 1);
        data.setFecha(simpleDateFormat.format(date));
        data.setHumedad((long) (Math.random() * 100) + 1);
        data.setTemperatura((long) (Math.random() * 100) + 1);

        return data;
    }

}
