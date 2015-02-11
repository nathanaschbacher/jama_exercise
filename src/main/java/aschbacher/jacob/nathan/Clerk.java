package aschbacher.jacob.nathan;

import java.util.HashMap;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Clerk extends UntypedActor {
  public static enum Msg {
    CHECKOUT;
  }

  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
  HashMap<String,ActorRef> bagboys = new HashMap<String,ActorRef>();

  @Override
  public void preStart() {
    log.info("Hired a Clerk.");
  }

  @Override
  public void onReceive(Object message) throws Exception {
    if(message instanceof String) {
      if(!bagboys.containsKey((String)message)) {
        log.info("Hiring Bagboy to handle " + message + "'s");
        bagboys.put((String)message, getContext().actorOf(Props.create(Bagboy.class)));
      }
      ActorRef bagboy = bagboys.get(message);
      bagboy.tell(message, getSelf());
    }
    else if(message.equals(Msg.CHECKOUT)) {
      
      log.info("The Clerk wants to ask for the total from the bagboys");
    }
    else {
      unhandled(message);
    }
  }

  private int total() {
    return 1;
    //ask all children for response and return it.
  }
}