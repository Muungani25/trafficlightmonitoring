package com.machina.trafficlightmonitoring.persistance;

import com.machina.trafficlightmonitoring.domain.TrafficLight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrafficLightRepository extends JpaRepository<TrafficLight,Long> {


}
