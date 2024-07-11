package com.machina.trafficlightmonitoring.domain;

import com.machina.trafficlightmonitoring.dto.TrafficLightStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TrafficLight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TrafficLightStatus trafficLightStatus;

}
