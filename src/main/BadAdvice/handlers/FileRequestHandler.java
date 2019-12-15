package main.BadAdvice.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileRequestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String urlPath = httpExchange.getRequestURI().toString();
        if (urlPath == null || urlPath.equals("/")) {
            urlPath = "/index.html";
        }
        String filePath = "web" + urlPath;
        File file = new File(filePath);
        if (!file.exists()) {
            // File not found - 404
            System.out.println("page not found");
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            OutputStream respBody = httpExchange.getResponseBody();
            Files.copy(Paths.get("web/HTML/404.html"), respBody);
            respBody.close();
            return;
        }
        System.out.printf("serving %s\n", filePath);
        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        OutputStream respBody = httpExchange.getResponseBody();
        Files.copy(file.toPath(), respBody);
        respBody.close();
    }

}
