package com.mycompany.route;

import akka.http.javadsl.server.*;

public class AllRoute extends AllDirectives {
  public AllRoute(){}

  public Route route(){
    return get(() -> complete("hello world"));
  }
}
