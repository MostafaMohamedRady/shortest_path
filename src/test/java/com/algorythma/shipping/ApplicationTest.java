package com.algorythma.shipping;

import com.algorythma.shipping.model.ShippingScenario;
import com.algorythma.shipping.service.Dijkstra;
import com.algorythma.shipping.util.AppUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.misc.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

import static com.algorythma.shipping.service.Dijkstra.getShortestPathTo;

public class ApplicationTest {


    @DisplayName("test cost resource file 01.csv")
    @Test
    public void tsetShippingScenarioFromResourceFile() {

        System.out.println("=========test shipping cost from 01.csv file in resources folder");
        File file = AppUtil.readFileFromResources("01.csv");
        PackageShipping packageShipping = new PackageShipping();
        List<ShippingScenario> shippingScenarios = packageShipping.calculatePackageCost(file);

        for (ShippingScenario sc : shippingScenarios) {
            System.out.println("<<< --- delivery to " + sc.getTarget() + " --- >>>");
            System.out.println("shortest path " + Dijkstra.getShortestPathTo(sc.getTarget()));
            System.out.println("result cost :" + sc.calculatedCost());
            System.out.println("testing cost " + sc.getTestingCost());
            Assertions.assertEquals(sc.getTestingCost(), sc.calculatedCost());
        }

    }

    @DisplayName("test according csv file in path default=/tmp/Algorythma")
    @Test
    public void tsetShippingScenarioFromDirectory() {
        System.out.println("=========test shipping cost for all csv files in given path");
        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            String path = p.getProperty("path");

            File[] files = AppUtil.listFilesForFolder(path);
            for (File file : files) {
                System.out.println("<<< ============================ >>>");
                System.out.println("file name =====> " + file.getName());
                PackageShipping packageShipping = new PackageShipping();
                List<ShippingScenario> shippingScenarios = packageShipping.calculatePackageCost(file);
                // loop over all scenarios (person to send packages to)
                for (ShippingScenario sc : shippingScenarios) {
                    System.out.println("<<< --- delivery to :" + sc.getTarget() + " --- >>>");
                    System.out.println("shortest path :" + Dijkstra.getShortestPathTo(sc.getTarget()));
                    System.out.println("result cost :" + sc.calculatedCost());
                    System.out.println("testing cost " + sc.getTestingCost());
                    Assertions.assertEquals(sc.getTestingCost(), sc.calculatedCost());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
