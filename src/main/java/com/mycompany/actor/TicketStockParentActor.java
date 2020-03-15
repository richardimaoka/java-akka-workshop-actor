package com.mycompany.actor;

import akka.actor.typed.*;
import akka.actor.typed.javadsl.*;

import java.util.*;

public class TicketStockParentActor {
  /********************************************************************************
   *  Actor Behaviors
   *******************************************************************************/
  // public: the only Behavior factory method accessed from outside the actor
  public static Behavior<Message> create(ActorRef<OrderParentActor.Message> orderParent, int ticketId, int quantity){
    Map<String, ActorRef<TicketStockActor.Message>> children = Collections.emptyMap();
    return Behaviors.setup(context -> behavior(context, orderParent, children));
  }

  // private: never accessed from outside the actor
  private static Behavior<Message> behavior(
    ActorContext<Message> context,
    ActorRef<OrderParentActor.Message> orderParent,
    Map<String, ActorRef<TicketStockActor.Message>> children) {
    return Behaviors.receive(Message.class)
      .onMessage(CreateTicketStock.class, message -> {
        var id = UUID.randomUUID();
        var child = context.spawn(TicketStockActor.create(orderParent, message.ticketId, message.quantity), id.toString());
        children.put(id.toString(), child);
        return behavior(context, orderParent, children);
      })
      .build();
  }

  /********************************************************************************
   *  Actor Messages
   *******************************************************************************/
  public interface Message {}

  public static final class CreateTicketStock implements Message {
    public final int ticketId;
    public final int quantity;
    public final ActorRef<String> sender;

    public CreateTicketStock(int ticketId, int quantity, ActorRef<String> sender) {
      this.ticketId = ticketId;
      this.quantity = quantity;
      this.sender = sender;
    }
  }

}
