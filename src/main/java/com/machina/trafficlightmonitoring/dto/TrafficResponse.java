package com.machina.trafficlightmonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrafficResponse {
    private int red1;
    private int yellow1;
    private int green1;
    private int red2;
    private int yellow2;
    private int green2;
    private int red3;
    private int yellow3;
    private int green3;
    private int red4;
    private int yellow4;
    private int green4;
}
