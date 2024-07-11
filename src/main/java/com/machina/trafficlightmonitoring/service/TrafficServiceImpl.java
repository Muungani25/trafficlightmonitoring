package com.machina.trafficlightmonitoring.service;

import com.machina.trafficlightmonitoring.domain.Esp32;
import com.machina.trafficlightmonitoring.domain.TrafficLight;
import com.machina.trafficlightmonitoring.domain.TrafficStatus;
import com.machina.trafficlightmonitoring.dto.Response;
import com.machina.trafficlightmonitoring.dto.TrafficLightStatus;
import com.machina.trafficlightmonitoring.dto.TrafficResponse;
import com.machina.trafficlightmonitoring.persistance.Esp32Repository;
import com.machina.trafficlightmonitoring.persistance.TrafficLightRepository;
import com.machina.trafficlightmonitoring.persistance.TrafficStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrafficServiceImpl implements TrafficService {
    private final TrafficLightRepository lightRepository;
    private final TrafficStatusRepository statusRepository;
    private final Esp32Repository esp32Repository;

    @Override
    public ResponseEntity<String> saveStatus(TrafficLightStatus lightStatus) {
        TrafficLight existingTrafficLight = lightRepository.findById(1L).orElse(null);
        TrafficStatus trafficLightStatus = statusRepository.findById(1L).orElse(null);

        // If an existing entry exists, update its status with the new lightStatus
        if (existingTrafficLight != null) {
            existingTrafficLight.setTrafficLightStatus(lightStatus);
            lightRepository.save(existingTrafficLight);
        } else {
            // If no existing entry, create a new TrafficLight entity with the provided lightStatus
            TrafficLight trafficLight = TrafficLight.builder()
                    .trafficLightStatus(lightStatus)
                    .build();
            lightRepository.save(trafficLight);
        }
        if (trafficLightStatus != null) {
            trafficLightStatus.setStatus(lightStatus.getStatus());
            statusRepository.save(trafficLightStatus);
        } else {
            TrafficStatus status = TrafficStatus.builder()
                    .status(lightStatus.getStatus())
                    .build();
            statusRepository.save(status);
        }

        return ResponseEntity.ok("done");
    }

    @Override
    public ResponseEntity<TrafficResponse> getLatest() {
        var light = lightRepository.findById(1L).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(TrafficResponse.builder()
                .green1(light.getTrafficLightStatus().getGreen1())
                .green2(light.getTrafficLightStatus().getGreen2())
                .red1(light.getTrafficLightStatus().getRed1())
                .red2(light.getTrafficLightStatus().getRed2())
                .yellow1(light.getTrafficLightStatus().getYellow1())
                .yellow2(light.getTrafficLightStatus().getYellow2())
                        .yellow3(light.getTrafficLightStatus().getYellow3())
                        .red3(light.getTrafficLightStatus().getRed3())
                        .green3(light.getTrafficLightStatus().getGreen3())
                        .red4(light.getTrafficLightStatus().getRed4())
                        .yellow4(light.getTrafficLightStatus().getYellow4())
                        .green4(light.getTrafficLightStatus().getGreen4())

                .build());
    }

    @Override
    public ResponseEntity<Response> getStatus() {
        var status = statusRepository.findById(1L).orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(Response.builder()
                .status(status.getStatus())
                .build());
    }

    @Override
    public void addHost(String host) {
        if (esp32Repository.findById(1L).isPresent()) {
            var currentHost = esp32Repository.findById(1L);
            currentHost.get().setHost(host);
            esp32Repository.save(currentHost.get());
        } else {
            esp32Repository.save(Esp32.builder()
                    .host(host)
                    .build());
        }
    }
}
