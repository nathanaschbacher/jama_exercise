package aschbacher.jacob.nathan;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.Config;

public class Bagboy extends UntypedActor {
  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
  Product responsibility = null;
  DiscountRule discount = null;
  int count = 0;

  @Override
  public void preStart() {
    log.info("Hired a Bagboy.");
  }

  @Override
  public void onReceive(Object message) throws Exception {
    if (message instanceof String && responsibility == null) {
      log.info("Hi! I'm the Bagboy who handles " + message + "'s.  I'm not dependable for other things.");
      takeResponsibility(message.toString());
    }
    else if(message instanceof String && responsibility.equals(message)) {
      bagItem(message.toString());
    }
    else {
      log.info("Whoa! That's not my responsibility. I'm dropping that " + message + " on the floor bub.");
      unhandled(message);
    }
  }

  private void takeResponsibility(String item) {
    log.info("Taking responsibility for the " + item + "'s");
    responsibility = loadProductDetails(item);
    discount = loadDiscountDetails(item);
  }

  private void bagItem(String item) {
    log.info("Bagging a " + item);
    count++;
  }

  private Product loadProductDetails(String item) {
    Config config = ConfigFactory.load().getConfig("jama_exercise.products");
    return new Product(item, config.getInt(item));
  }

  private DiscountRule loadDiscountDetails(String item) {
    Config config = ConfigFactory.load().getConfig("jama_exercise.discounts");
    return new MForNDiscount(1,1);
  }

  private int tally(Product product, DiscountRule discount, int count) {
    return discount.apply(count, product.getPrice());
  }
}