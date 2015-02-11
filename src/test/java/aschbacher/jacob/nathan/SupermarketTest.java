package aschbacher.jacob.nathan;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit tests for Supermarket class checkout functionality.
 */
public class SupermarketTest {
    @Test
    public void expected() {
        assertEquals(240, new Supermarket(getProducts(), getDiscounts()).checkout("ABBACBBAB"));
    }

    @Test
    public void arbitrary() {
        assertEquals(0, new Supermarket(getProducts(), getDiscounts()).checkout("jkasdhfpaiey8f29p8 3hhasdfj''asefu0[2390ha 'psd jf'java. "));
    }

    @Test
    public void casey() {
        assertEquals(240, new Supermarket(getProducts(), getDiscounts()).checkout("abbacbbabABBACBBAB"));
    }

    @Test
    public void empty() {
        assertEquals(0, new Supermarket(getProducts(), getDiscounts()).checkout(""));
    }

    @Test
    public void plusALittleExtra() {
        assertEquals(300, new Supermarket(getProducts(), getDiscounts()).checkout("BBBBBBBB"));
    }

    @Test
    public void terribleDeal() {
        assertEquals(200, new Supermarket(getProducts(), getDiscounts()).checkout("BBBB"));
    }

    @Test
    public void noDeal() {
        assertEquals(150, new Supermarket(getProducts(), getDiscounts()).checkout("ACACAC"));
    }

    @Test
    public void noProducts() {
        assertEquals(0, new Supermarket(new HashMap<String,Product>(), getDiscounts()).checkout("ABBACBBAB"));
    }

    @Test
    public void noDiscounts() {
        assertEquals(340, new Supermarket(getProducts(), new HashMap<String,DiscountRule>()).checkout("ABBACBBAB"));
    }

    private HashMap<String,Product> getProducts() {
        HashMap<String,Product> products = new HashMap<String,Product>();
        products.put("A",new Product("A", 20));
        products.put("B",new Product("B", 50));
        products.put("C",new Product("C", 30));
        return products;
    }

    private HashMap<String,DiscountRule> getDiscounts() {
        HashMap<String,DiscountRule> discounts = new HashMap<String,DiscountRule>();
        discounts.put("B", new MForNDiscount(5,3));
        return discounts;
    }
}
