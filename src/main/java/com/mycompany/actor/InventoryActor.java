package com.mycompany.actor;

import akka.actor.typed.*;
import akka.actor.typed.javadsl.*;

public class InventoryActor {
  /********************************************************************************
   *  Actor Behaviors
   *******************************************************************************/
  // public: the only Behavior factory method accessed from outside the actor
  public static Behavior<Message> create(int ticketId, int quantity){
    if(quantity < 0)
      throw new RuntimeException("Inventory quantity cannot be negative");
    else
      return Behaviors.setup(context -> behavior(context, ticketId, quantity));
  }

  // private: never accessed from outside the actor
  private static Behavior<Message> behavior(ActorContext<Message> context, int ticketId, int quantity) {
    return Behaviors.receive(Message.class)
      .onMessage(InventoryDecrement.class, message -> {
        int decrementedQuantity = quantity - message.quantityDecrementedBy;
        if (decrementedQuantity < 0) {
          message.sender.tell("inventory cannot have a negative quantity");
          return Behaviors.same();
        } else {
          return behavior(context, ticketId, decrementedQuantity);
        }
      })
      .build();
  }

  /********************************************************************************
   *  Actor Messages
   *******************************************************************************/
  public interface Message {}

  public static final class InventoryDecrement implements Message {
    public final int ticketId;
    public final int quantityDecrementedBy;
    public final ActorRef<String> sender;

    public InventoryDecrement(int ticketId, int quantityDecrementedBy, ActorRef<String> sender) {
      this.ticketId = ticketId;
      this.quantityDecrementedBy = quantityDecrementedBy;
      this.sender = sender;
    }
  }

}
