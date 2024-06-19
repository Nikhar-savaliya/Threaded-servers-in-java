import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() throws IOException {
        int port = 8010;
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
        System.out.println("Server is listening on port:" + port);

        while (true) {
            Socket acceptedConnection = null;
            PrintWriter toClient = null;
            BufferedReader fromClient = null;
            try {
                acceptedConnection = serverSocket.accept();
                System.out.println("Connection accepted from client " + acceptedConnection.getRemoteSocketAddress());
                toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                toClient.println("Hello from the server");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (toClient != null)
                    toClient.close();
                if (fromClient != null)
                    fromClient.close();
                if (acceptedConnection != null)
                    acceptedConnection.close();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
