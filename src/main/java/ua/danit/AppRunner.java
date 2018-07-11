package ua.danit;

import ua.danit.util.TinderServer;

import java.util.Arrays;

public class AppRunner {
  public static void main(String[] args) throws Exception {
    System.out.println("Command line arguments:");
    System.out.println(Arrays.toString(args));
    String port = null;

    port = args.length > 0 ? args[0] : "8081";

    TinderServer server = new TinderServer();
    server.start(port);
  }
}
