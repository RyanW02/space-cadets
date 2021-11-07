package chat.server;

import chat.protocol.Packet;
import chat.protocol.impl.HelloPacket;
import com.google.gson.Gson;

public class Main {
  public static void main(String[] args) {
    short port = 3000;
    try {
      port = Short.parseShort(args[0]);
    } catch (Exception e) {
      System.out.println("No port provided, defaulting to 3000");
    }

    Server server = new Server(port);
    server.run();
  }
}
