package com.mycompany.actor;

import akka.actor.typed.*;
import akka.actor.typed.javadsl.*;

public class GuardianActor {
  public static Behavior<Void> create() {
    return Behaviors.empty();
  }
}
