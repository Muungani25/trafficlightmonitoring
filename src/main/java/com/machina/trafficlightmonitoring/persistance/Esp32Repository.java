package com.machina.trafficlightmonitoring.persistance;

import com.machina.trafficlightmonitoring.domain.Esp32;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Esp32Repository extends JpaRepository<Esp32,Long> {

}
