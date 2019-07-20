package com.algorythma.shipping;

import com.algorythma.shipping.model.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PackageTest {

    @Test
    public void testGetNormalizedWeight(){
        Package packageInfo=DataInitializer.getPackageInfo();
        Assertions.assertEquals(2.5,packageInfo.getNormalizedWeight());
    }
}
