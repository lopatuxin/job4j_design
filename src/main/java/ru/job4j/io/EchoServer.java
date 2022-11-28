package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream())
                     )) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str.contains("msg=Exit")) {
                        out.write("Bye".getBytes());
                        server.close();
                    } else if (str.contains("msg=Hello")) {
                        out.write("Hello, dear friend.".getBytes());
                    } else {
                        out.write(str.split("=")[1].getBytes());
                    }
                    System.out.println(str);
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Ошибка вывода", e);
        }
    }
}
