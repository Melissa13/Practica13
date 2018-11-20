package main;

import Services.Websockets;
import freemarker.template.Configuration;
import freemarker.template.Version;
import jms.Reciver;
import org.eclipse.jetty.websocket.api.Session;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import javax.jms.JMSException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

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

            return new ModelAndView(attributes, "inicio.ftl");

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
