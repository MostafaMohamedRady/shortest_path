package com.algorythma.shipping;

import com.algorythma.shipping.model.Person;
import com.algorythma.shipping.model.ShippingScenario;
import com.algorythma.shipping.service.Dijkstra;
import com.algorythma.shipping.service.NetworkGenerator;
import com.algorythma.shipping.util.AppUtil;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class PackageShipping {


    public List<ShippingScenario> calculatePackageCost(File file){
        NetworkGenerator networkGenerator = new NetworkGenerator(file);
        Map<String, Person> personsMap = networkGenerator.getPersonsMap();
        List<ShippingScenario> shippingScenarios = networkGenerator.getShippingScenarios();
        // compute all paths and distances from Person (ME)
        Dijkstra.computePaths(personsMap.get("ME"));
        return  shippingScenarios;

    }



    public static void main(String[] args) {

        File[] files = AppUtil.listFilesForFolder("/Users/mostafarady/algorythma task/Backend-Dev-Test");
        for (File file:files) {

            NetworkGenerator networkGenerator = new NetworkGenerator(file);
            Map<String, Person> personsMap = networkGenerator.getPersonsMap();
            List<ShippingScenario> shippingScenarios = networkGenerator.getShippingScenarios();

            // compute all paths and distances from Person (ME)
            Dijkstra.computePaths(personsMap.get("ME"));

            // loop over all scenarios (person to send packages to)
            for (ShippingScenario sc : shippingScenarios) {
                System.out.println("delivery to " + sc.getTarget() + " --- >>>");
                System.out.println("shortest path " + Dijkstra.getShortestPathTo(sc.getTarget()));

                System.out.println("result cost :" + sc.calculatedCost());
                System.out.println("testing cost " + sc.getTestingCost());
                System.err.println("Are equals : " + (sc.calculatedCost() == sc.getTestingCost()));

            }
        }

    }
}
