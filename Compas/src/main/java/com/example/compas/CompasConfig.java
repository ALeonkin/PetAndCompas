package com.example.compas;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CompasConfig {

    private Map<String, String> compassRanges = new HashMap<>();

    public void setCompassRanges(Map<String, String> compassRanges) {
        this.compassRanges = compassRanges;
    }

    public String getSideByDegree(int degree) {
        for (Map.Entry<String, String> entry : compassRanges.entrySet()) {
            String[] range = entry.getValue().split("-");
            int startRange = Integer.parseInt(range[0]);
            int endRange = Integer.parseInt(range[1]);
            if (degree >= startRange && degree <= endRange) {
                return entry.getKey();
            }
        }
        return "Unknown";
    }
}
