package aschbacher.jacob.nathan;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Supermarket {
  public static void main(String[] args) {
    HashMap<String,Product> products = new HashMap<String,Product>();
    products.put("A",new Product("A", 20));
    products.put("B",new Product("B", 50));
    products.put("C",new Product("C", 30));

    HashMap<String,DiscountRule> discounts = new HashMap<String,DiscountRule>();
    discounts.put("B", new MForNDiscount(5,3));

    Supermarket market = new Supermarket(products, discounts);

    System.out.println("Opened a new Supermarket!");
    System.out.println("Enter checkout item list:");
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

    try {
      String items = r.readLine().toUpperCase();
      System.out.format("Total is: %d%n", market.checkout(items));
    } catch (IOException ioe) {
      System.out.println(ioe.toString());
      System.exit(1);
    }
  }

  private HashMap<String,Product> products;
  private HashMap<String,DiscountRule> discounts;

  public Supermarket(HashMap<String,Product> products, HashMap<String,DiscountRule> discounts) {
    this.products = products;
    this.discounts = discounts;
  }

  public int checkout(String list) {
    HashMap<String,Integer> bag = bagItems(list.split(""));
    return getTotal(bag, products, discounts);
  }

  private HashMap<String,Integer> bagItems(String[] items) {
    HashMap<String,Integer> bag = new HashMap<String,Integer>();

    for(String item: items) {
      if(bag.containsKey(item)) {
        bag.put(item, bag.get(item)+1);
      }
      else {
        bag.put(item, 1);
      }
    }
    return bag;
  }

  private int getTotal(HashMap<String,Integer> bag, 
                       HashMap<String,Product> products, 
                       HashMap<String,DiscountRule> discounts) {
    int total = 0;
    
    for(HashMap.Entry<String,Integer> item: bag.entrySet()) {
      if(products.containsKey(item.getKey())) {
        Product product = products.get(item.getKey());

        if(discounts.containsKey(item.getKey())) {
          DiscountRule discount = discounts.get(item.getKey());
          total += discount.apply(item.getValue(), product.getPrice());
        }
        else {
          total += product.getPrice() * item.getValue();
        }
      }
    }
    return total;
  }
}
