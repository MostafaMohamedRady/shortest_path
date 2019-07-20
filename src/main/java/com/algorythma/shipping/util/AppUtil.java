package com.algorythma.shipping.util;

import java.io.File;
import java.io.FilenameFilter;

public class AppUtil {

    /**
     * util method to list csv files from folder
     * @param dirName
     * @return list of csv file
     */
    public static File[] listFilesForFolder(String dirName){
        File dir = new File(dirName);
        return dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename)
            { return filename.endsWith(".csv"); }
        } );

    }

    public static File readFileFromResources(String fileName){
        ClassLoader classLoader = AppUtil.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}
