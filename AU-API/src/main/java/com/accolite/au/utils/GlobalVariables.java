package com.accolite.au.utils;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GlobalVariables {
    private List<String> locations = new ArrayList(Arrays.asList("Delhi", "Hyderabad", "Chennai", "Mumbai", "Banglore"));

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
