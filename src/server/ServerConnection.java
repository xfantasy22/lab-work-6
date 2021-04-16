package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection {
    private static Socket clientSocket;
    private static ServerSocket serverSocket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

    public static void main(String[] args) {
        try {
            try {
                serverSocket = new ServerSocket(4004);
                System.out.println("Сервер запущен");
                clientSocket = serverSocket.accept();
                System.out.println("Соединение с клиентом установлено");
                try {
                    in = new ObjectInputStream(clientSocket.getInputStream());
                    out = new ObjectOutputStream(clientSocket.getOutputStream());
                    System.out.println("Потоки созданы");

                    do {
                        ServerObject o = (ServerObject) in.readObject();
                        System.out.println("Принял " + o);

                        out.writeObject("Привет");
                        //out.flush();
                    } while (true);
                } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт");
                serverSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка сервера");
            System.out.println(e);
        }
    }
}
