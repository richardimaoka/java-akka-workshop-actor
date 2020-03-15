package com.mycompany.app;

import akka.actor.typed.*;
import akka.http.javadsl.*;
import akka.stream.*;
import com.mycompany.actor.*;

public class Main {

  public static void main(String[] args) throws Exception {
    // boot up server using the route as defined below
    var system = ActorSystem.create(GuardianActor.create(), "userGuardian");
    var http = Http.get(system.classicSystem());
    var materializer = Materializer.createMaterializer(system);

    System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
    System.in.read(); // let it run until user presses return
  }
}