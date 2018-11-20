package main;

import Services.Websockets;
import com.fasterxml.jackson.core.JsonProcessingException;
import jms.Reciver;

import freemarker.template.Configuration;
import freemarker.template.Version;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;


import javax.jms.JMSException;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class Main {

    public static List<Session> Users = new ArrayList<>();

    public static void main(String[] args) throws JMSException{

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
        try {
            consumidor.conectar();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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
