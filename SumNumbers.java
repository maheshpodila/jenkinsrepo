import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.InetSocketAddress;

public class SumNumbersWeb {
    public static void main(String[] args) throws Exception {
        // Create HTTP server that listens on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/calculate", new SumHandler());
        server.setExecutor(null); // Creates a default executor
        server.start();
        System.out.println("Server started at http://localhost:8000/calculate");
    }

    static class SumHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Get the query string from the request (e.g., /calculate?numbers=1,2,3)
            String query = exchange.getRequestURI().getQuery();
            String response = "";

            if (query != null && query.startsWith("numbers=")) {
                // Extract numbers from the query
                String[] numbersStr = query.split("=")[1].split(",");
                int sum = 0;
                try {
                    // Calculate the sum of the numbers
                    for (String num : numbersStr) {
                        sum += Integer.parseInt(num);
                    }
                    response = "<html><body><h1>The sum of the numbers is: " + sum + "</h1></body></html>";
                } catch (NumberFormatException e) {
                    response = "<html><body><h1>Invalid input, please provide valid integers.</h1></body></html>";
                }
            } else {
                response = "<html><body><h1>Please provide numbers as a query parameter (e.g., ?numbers=1,2,3).</h1></body></html>";
            }

            // Send the response to the browser
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
