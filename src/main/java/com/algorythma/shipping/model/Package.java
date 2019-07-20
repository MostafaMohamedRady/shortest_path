package com.algorythma.shipping.model;

public class Package {

    private double width;
    private double length;
    private double height;
    private double weight;

    public Package(String width, String length, String height, String weight) {
        this.width = Double.valueOf(width);
        this.length = Double.valueOf(length);
        this.height = Double.valueOf(height);
        this.weight = Double.valueOf(weight)/1000;
    }

    /**
     * getNormalizedWeight (the grater value of an actual weight or a volumetric weight)
     * @return
     */
    public double getNormalizedWeight() {
        double volumetricWeight=getVolumetricWeight();
        if(volumetricWeight > weight)
            return getNormalizedWeightRounded(volumetricWeight);
        return 	getNormalizedWeightRounded(weight);

    }

    private double getNormalizedWeightRounded(double normalizedWeight) {
        return normalizedWeight %5 == 0 ? normalizedWeight : (int) (normalizedWeight / .5) * .5 + .5;
    }

    /**
     * get getVolumetricWeight for package
     * @return VolumetricWeight
     */
    private double getVolumetricWeight(){
        double volume = width * length * height;
        return (volume / 5000);
    }
}
