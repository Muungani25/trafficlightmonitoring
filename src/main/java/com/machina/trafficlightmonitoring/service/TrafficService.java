package com.machina.trafficlightmonitoring.service;

import com.machina.trafficlightmonitoring.dto.Response;
import com.machina.trafficlightmonitoring.dto.TrafficLightStatus;
import com.machina.trafficlightmonitoring.dto.TrafficResponse;
import org.springframework.http.ResponseEntity;


public interface TrafficService {

    ResponseEntity<String> saveStatus(TrafficLightStatus lightStatus);
    ResponseEntity<TrafficResponse> getLatest();

    ResponseEntity<Response> getStatus();

    void addHost(String host);
}
