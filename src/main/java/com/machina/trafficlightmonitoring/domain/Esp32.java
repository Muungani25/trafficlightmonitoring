package com.machina.trafficlightmonitoring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Esp32 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String host;
}
