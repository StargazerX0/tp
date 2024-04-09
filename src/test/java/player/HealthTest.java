package player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthTest {
    @Test
    void testHealth() {
        Health health = new Health();
        assertEquals(100, health.outputHealth());
        health.deduct(10);
        assertEquals(90, health.outputHealth());
        health.addHealth(10);
        assertEquals(100, health.outputHealth());
        health.deduct(100);
        assertEquals(0, health.outputHealth());
        health.addHealth(100);
        assertEquals(100, health.outputHealth());
    }
}

