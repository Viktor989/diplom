import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        // порт и хост
        int port = 8989;
        String host = "localhost";

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out.println("бизнес");
            String prettyJson;
            do {
                prettyJson = in.readLine();
                System.out.println(prettyJson);
            } while (!prettyJson.endsWith("]"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
