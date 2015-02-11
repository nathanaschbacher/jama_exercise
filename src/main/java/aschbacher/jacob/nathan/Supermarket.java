package aschbacher.jacob.nathan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.actor.SupervisorStrategy;
import static akka.actor.SupervisorStrategy.resume;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.stop;
import static akka.actor.SupervisorStrategy.escalate;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import scala.collection.immutable.Seq;
import scala.concurrent.Await;
import static akka.pattern.Patterns.ask;
import scala.concurrent.duration.Duration;
import akka.testkit.AkkaSpec;
import akka.testkit.TestProbe;


public class Supermarket {
  private ActorSystem system;
  private ActorRef clerk;

  public static void main(String[] args) {
    Supermarket market = new Supermarket();
    System.out.println("Opened a new Supermarket!");
    //System.out.println("Enter checkout item list:");
    //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

    //try {
      //String items = r.readLine().toUpperCase();
      String items = "ABBACBBAB";
      market.checkout(items);
      //System.out.format("Total is: %d", market.checkout(items));
    //} catch (IOException ioe) {
      //System.out.println(ioe.toString());
      //System.exit(1);
    //}
  }

  public Supermarket() {
    system = ActorSystem.create("Supermarket");
    clerk = system.actorOf(Props.create(Clerk.class), "clerk");
  }

  public int checkout(String list) {
    String[] items = list.split("");
    for(String item: items) {
      System.out.println("Telling Clerk to Scan a " + item);
      clerk.tell(item, null);
    }

    Timeout timeout = new Timeout(Duration.create(10, SECONDS));
    Future<Object> future = Patterns.ask(clerk, Clerk.Msg.CHECKOUT, timeout);
    Response res = (Response ) Await.result(future, timeout.duration());
  }
}
