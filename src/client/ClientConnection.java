import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection {
    private static Socket clientSocket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static InputHandler ih;

    public static void main(String[] args) {
        try {
            try {
                ih = new InputHandler();
                clientSocket = new Socket("localhost", 4004);
                System.out.println("Создан сокет");

                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

                System.out.println("Клиент запущен");

                do {
                    InputHandler.ServerObject so = ih.setStart();

                    out.writeObject(so); // пишем сообщение (команда и ее аргументы) на сервер
                    //out.flush();

                    String serverAnswer = (String) in.readObject(); //ждем ответ сервера, т.е. строку-результат выполненной команды
                    System.out.println(serverAnswer);
                }while (true);
            } catch (UnknownHostException e) {
                System.out.println("Неизвестный хост");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Клиент закрыт");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println("Ошибка клиента"+e);
        }
    }
}


