package server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class WebServer {

    public void main(String... args) throws IOException {
        InetSocketAddress address = new InetSocketAddress("192.168.181.160", 9000);
        HttpServer server = HttpServer.create(address, 2);
        server.createContext("/", httpExchange -> {
            String response = "this is response";
            InetSocketAddress address1 = httpExchange.getRemoteAddress();
            httpExchange.sendResponseHeaders(200, response.length() + address1.getHostName().length());
            httpExchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.write(address1.getAddress().getHostName().getBytes());
            os.close();
        });
        server.start();
    }
}
