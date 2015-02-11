package aschbacher.jacob.nathan;

public class Product {
  private String id = null;
  private int price = 0;

  public Product(String id, int price) {
    this.id = id;
    this.price = price;
  }

  public String getId() {
    return this.id;
  }

  public int getPrice() {
    return this.price;
  }
}