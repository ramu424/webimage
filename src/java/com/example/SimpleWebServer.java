package com.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// This annotation automatically roots your webpage at the landing path
@WebServlet(urlPatterns = {"/"})
public class SimpleWebServer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set response context headers
        response.setContentType("text/html; charset=UTF-8");
        
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
                <h1>Hello from a Deployable WAR File!</h1>
                <p>This layout is hosted inside a traditional web application container.</p>
                
                <div class="image-container">
                    <img src="https://unsplash.com" alt="Coffee">
                    <img src="https://unsplash.com" alt="Nature">
                </div>
            </body>
            </html>
            """;

        // Print the output layout to the web stream
        PrintWriter out = response.getWriter();
        out.print(htmlResponse);
        out.flush();
    }
}
