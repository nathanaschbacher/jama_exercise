package aschbacher.jacob.nathan;

public class MForNDiscount implements DiscountRule {
  private int M = 1;
  private int N = 1;

  public MForNDiscount(int baseQuantity, int thresholdQuantity) {
    M = baseQuantity;
    N = thresholdQuantity;
  }

  public int apply(int quantity, int unitPrice) {
    int groupsOfM = quantity / M;  
    int remainderOfM = quantity - (groupsOfM * M);

    return (unitPrice * groupsOfM * N) + (unitPrice * remainderOfM);
  }
}
