package main.BadAdvice.handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import main.BadAdvice.BadAdvice;
import main.BadAdvice.json_util.JsonConverter;
import main.BadAdvice.models.AdviceResult;
import main.BadAdvice.services.GetAdviceService;

import java.io.*;
import java.net.HttpURLConnection;
import java.security.InvalidParameterException;

public class GetAdviceHandler implements HttpHandler {

    BadAdvice badAdvice;
    MaxentTagger tagger;

    public GetAdviceHandler(BadAdvice badAdvice, MaxentTagger tagger) {
        this.badAdvice = badAdvice;
        this.tagger = tagger;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toUpperCase().equals("GET")) {

                Headers reqHeaders = exchange.getRequestHeaders();

                // String auth_token = reqHeaders.get("Authorization").get(0);

                OutputStream respBody = exchange.getResponseBody();

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                String respData;

                String request_uri = exchange.getRequestURI().getPath();
                String[] uri_array = request_uri.split("/");

                if (uri_array.length != 3) {
                    System.out.println("Incorrect number of parameters");
                    for (String each : uri_array) {
                        System.out.println(each);
                    }
                    throw new InvalidParameterException("Error: Incorrect number of parameters");
                }

                String question = uri_array[2].replaceAll("_", " ");

                AdviceResult adviceResult = new GetAdviceService().getAdvice(question, badAdvice, tagger);
                respData = JsonConverter.modelToJson(adviceResult);

                writeString(respData, respBody);
            } else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }

            exchange.getResponseBody().close();
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);

            exchange.getResponseBody().close();

            e.printStackTrace();
        }
    }

    void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(sw);
        bw.write(str);
        bw.flush();
    }
}
