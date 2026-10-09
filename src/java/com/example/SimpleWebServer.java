package com.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleWebServer {
    public static void main(String[] args) throws IOException {
        // Create server listening on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Map root URL ("/") to HTML handler
        server.createContext("/", new ImageWebpageHandler());
        
        server.setExecutor(null); 
        server.start();
        System.out.println("Server started! Open http://localhost:8080 in your web browser.");
    }

    static class ImageWebpageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String htmlResponse = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Java Powered Webpage</title>
                    <style>
                        body { font-family: Arial, sans-serif; text-align: center; background-color: #f4f4f9; padding: 20px; }
                        h1 { color: #333; }
                        .image-container { display: flex; justify-content: center; gap: 20px; margin-top: 20px; }
                        img { width: 300px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }
                    </style>
                </head>
                <body>
                    <h1>Hello from a Structured Maven Project!</h1>
                    <p>This layout uses the standard src/main/java structure.</p>
                    
                    <div class="image-container">
                        <img src="https://unsplash.com" alt="Coffee">
                        <img src="https://unsplash.com" alt="Nature">
                    </div>
                </body>
                </html>
                """;

            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, htmlResponse.getBytes().length);
            
            OutputStream os = exchange.getResponseBody();
            os.write(htmlResponse.getBytes());
            os.close();
        }
    }
}
