package Services;

import main.Main;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;

public class Websockets {

    @OnWebSocketConnect
    public void conectando(Session usuario) {
        System.out.println("Conectando Usuario: " + usuario.getLocalAddress().getAddress().toString());
        Main.Users.add(usuario);
    }

    @OnWebSocketClose
    public void cerrandoConexion(Session usuario, int statusCode, String reason) {
        System.out.println("Desconectando el usuario: " + usuario.getLocalAddress().getAddress().toString());
        Main.Users.remove(usuario);
    }

}
