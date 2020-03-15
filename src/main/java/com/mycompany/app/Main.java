package com.mycompany.app;

import akka.actor.typed.*;
import akka.http.javadsl.*;
import akka.stream.*;
import com.mycompany.actor.*;
import com.mycompany.route.*;

public class Main {

  public static void main(String[] args) throws Exception {
    // boot up server using the route as defined below
    var system = ActorSystem.create(GuardianActor.create(), "userGuardian");
    var materializer = Materializer.createMaterializer(system);

    var http = Http.get(system.classicSystem());
    var allRoute = new AllRoute();
    var routeFlow = allRoute.route().flow(system.classicSystem(), materializer);
    var binding = http.bindAndHandle(routeFlow, ConnectHttp.toHost("localhost", 8080), materializer);

    System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
    System.in.read(); // let it run until user presses return

    binding
      .thenCompose(ServerBinding::unbind)
      .thenAccept(unbound -> system.terminate());
  }
}