package com.algorythma.shipping;

import com.algorythma.shipping.service.NetworkGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NetworkGeneratorTest {

    @Test
    public void testGetPersonsMap() {
        NetworkGenerator networkGenerator = DataInitializer.getNetworkGenerator();
        Assertions.assertNotNull(networkGenerator.getPersonsMap());
        Assertions.assertTrue(networkGenerator.getPersonsMap().size() > 0);
        Assertions.assertNotNull(networkGenerator.getPersonsMap().get("ME"));
    }

    @Test
    public void testGetShippingScenarios() {
        NetworkGenerator networkGenerator = DataInitializer.getNetworkGenerator();
        Assertions.assertNotNull(networkGenerator.getShippingScenarios());
        Assertions.assertTrue(networkGenerator.getShippingScenarios().size() > 0);
    }
}
