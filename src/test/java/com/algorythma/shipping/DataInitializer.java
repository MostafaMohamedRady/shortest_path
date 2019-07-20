package com.algorythma.shipping;

import com.algorythma.shipping.model.Package;
import com.algorythma.shipping.service.NetworkGenerator;
import com.algorythma.shipping.util.AppUtil;

public class DataInitializer {


    public static Package getPackageInfo() {
        return new Package("20","47","12","1890");
    }

    public static NetworkGenerator getNetworkGenerator(){
        return new NetworkGenerator(AppUtil.readFileFromResources("01.csv"));
    }
}
