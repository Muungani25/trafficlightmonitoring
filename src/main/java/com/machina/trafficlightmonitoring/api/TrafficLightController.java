package com.machina.trafficlightmonitoring.api;

import com.machina.trafficlightmonitoring.dto.TrafficLightStatus;
import com.machina.trafficlightmonitoring.persistance.Esp32Repository;
import com.machina.trafficlightmonitoring.service.TrafficService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class TrafficLightController {
    private final TrafficService trafficService;
    private final Esp32Repository esp32Repository;

    @PostMapping("/traffic_status")
    public ResponseEntity<?> receiveTrafficStatus(@RequestBody TrafficLightStatus lightStatus){
        return trafficService.saveStatus(lightStatus);

    }

    @GetMapping("/traffic_light_status")
        public ResponseEntity<?> getTrafficLightStatus(){
        return trafficService.getLatest();
    }

    @GetMapping("/status")
    public ResponseEntity<?> getStatus(){
       return trafficService.getStatus();
    }
    @PostMapping("/add_host")
        public void addHost(@RequestBody String host){
trafficService.addHost("http://"+host);
        }


    @GetMapping("/reset/{action}")
    public String controlTraffic(@PathVariable boolean action) {
        var host=esp32Repository.findById(1L).orElseThrow();
        String esp32Url = host.getHost();
        if (action) {
            sendHttpRequest(esp32Url + "/reset");
            return "intersection reset";

        } else {
            return "Invalid action";
        }


    }
    private void sendHttpRequest(String urlString) {
        log.info("url..{}",urlString);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(urlString, String.class);
    }
}
// ADD STATUS END POINT TO SHOW STATUS OFF TRAFFIC LIGHTS