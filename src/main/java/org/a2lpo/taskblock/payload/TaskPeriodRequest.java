package org.a2lpo.taskblock.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskPeriodRequest {
    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;

    public TaskPeriodRequest() {
    }

    public TaskPeriodRequest(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }
}
