package main.BadAdvice;

import com.sun.net.httpserver.HttpServer;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import main.BadAdvice.handlers.*;

import java.io.IOException;
import java.net.InetSocketAddress;

public class BadAdviceServer {

    BadAdvice badAdvice = new BadAdvice();
    MaxentTagger tagger = new MaxentTagger("/home/matthew/IdeaProjects/BadAdvice/english-bidirectional-distsim.tagger");

    public static void main(String[] args) throws IOException {
        BadAdviceServer badAdviceServer = new BadAdviceServer();
        int port = 4000;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        badAdviceServer.startServer(port);
    }

    private void startServer(int port) throws IOException {
        InetSocketAddress serverAddress = new InetSocketAddress(port);
        HttpServer server = HttpServer.create(serverAddress, 10);
        registerHandlers(server);
        server.start();
        System.out.println("BadAdviceServer listening on port " + port);
    }

    private void registerHandlers(HttpServer server) {
        server.createContext("/", new FileRequestHandler());
        server.createContext("/get_advice", new GetAdviceHandler(badAdvice, tagger));
    }

}
