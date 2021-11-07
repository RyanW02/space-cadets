package chat.client;

import java.io.IOException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("Usage: java -jar chat-client.jar [host] [port]");
      return;
    }

    String host = args[0];
    short port = Short.parseShort(args[1]);

    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter your username: ");
    String username = scanner.nextLine();

    Client client = new Client(username);
    try {
      client.connect(host, port);
      client.login();
      new Thread(() -> {
        try {
          client.readMessages();
        } catch (IOException e) {
          e.printStackTrace();
          client.disconnect();
        }
      }).start();

      while (client.isConnected()) {
        String input = scanner.nextLine();
        client.sendMessage(input);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
