package com.jepepper.sellingApp.service.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;


@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private static String location = "sellingApp/src/main/resources/static/images";
    /**
     * Get the private location of the file*/
    public String getLocation() {
        return location;
    }
    /**
     * Set the location of the file*/
    public void setLocation(String location) {
        StorageProperties.location = location;
    }

}
