package com.mycompany.app;

import akka.actor.*;
import akka.http.javadsl.*;
import akka.stream.*;

public class Main {

  public static void main(String[] args) throws Exception {
    // boot up server using the route as defined below
    var system = ActorSystem.create("routes");
    var http = Http.get(system);
    var materializer = Materializer.createMaterializer(system);

    System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
    System.in.read(); // let it run until user presses return
  }
}