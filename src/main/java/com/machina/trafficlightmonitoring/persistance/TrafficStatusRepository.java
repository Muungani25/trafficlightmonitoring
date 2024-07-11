package com.machina.trafficlightmonitoring.persistance;

import com.machina.trafficlightmonitoring.domain.TrafficStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrafficStatusRepository extends JpaRepository<TrafficStatus,Long> {
}
