package main;

import DB.BootStrapService;
import Services.Websockets;
import freemarker.template.Configuration;
import freemarker.template.Version;
import jms.Reciver;
import jms.Cliente1;
import org.apache.activemq.broker.BrokerService;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import javax.jms.JMSException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.staticFiles;
import static spark.Spark.webSocket;
import static spark.Spark.get;

public class Main {

    public static List<Session> Users = new ArrayList<>();

    public static void main(String[] args) throws IOException, JMSException, InterruptedException {

        staticFiles.location("/static");

        Configuration configuration = new Configuration(new Version(2, 3, 3));
        configuration.setClassForTemplateLoading(Main.class, "/templates");

        String cola = "pruebajms.cola";

        webSocket("/nuevoMensaje", Websockets.class);

        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        get("/", (req, res) -> {
            Map<String, Object> attributes = new HashMap<>();

            return new ModelAndView(attributes, "index.ftl");

        },freeMarkerEngine);

        Reciver consumidor=new Reciver(cola);
        consumidor.conectar();
    }

    public static void enviarMensaje(String mensaje) {
        for (Session sesionConectada : Users) {
            try {
                sesionConectada.getRemote().sendString(mensaje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
