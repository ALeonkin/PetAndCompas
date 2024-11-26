package com.example.compas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class CompasController {
    @Autowired
    private CompasConfig compasConfig;

    @PostMapping("/ranges")
    public ResponseEntity<?> setRanges(@RequestBody Map<String, String> ranges){
        compasConfig.setCompassRanges(ranges);
        return ResponseEntity.ok("Границы компаса установленны");
    }

    @GetMapping("/degree")
    public Map<String,String> getDirection(@RequestBody Map<String, Integer> request){
        int degree = request.get("Degree");
        String side = compasConfig.getSideByDegree(degree);
        return ResponseEntity.ok(Collections.singletonMap("Side", side)).getBody();
    }

}
