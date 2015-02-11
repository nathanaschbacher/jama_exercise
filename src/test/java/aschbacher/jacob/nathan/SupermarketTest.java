package aschbacher.jacob.nathan;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit test for Supermarket Supervisor.
 */
public class SupermarketTest {
    @Test
    public void testCheckout() {
        assertEquals(new Supermarket().checkout("ABBACBBAB"), 240);
    }
}
