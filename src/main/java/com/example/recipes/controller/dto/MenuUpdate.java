package com.example.recipes.controller.dto;

import java.time.LocalDate;

public class MenuUpdate {
    private LocalDate date;
    private Long breakfastId;
    private Long snackId;
    private Long lunchId;
    private Long dinnerId;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getBreakfastId() {
        return breakfastId;
    }

    public void setBreakfastId(Long breakfastId) {
        this.breakfastId = breakfastId;
    }

    public Long getSnackId() {
        return snackId;
    }

    public void setSnackId(Long snackId) {
        this.snackId = snackId;
    }

    public Long getLunchId() {
        return lunchId;
    }

    public void setLunchId(Long lunchId) {
        this.lunchId = lunchId;
    }

    public Long getDinnerId() {
        return dinnerId;
    }

    public void setDinnerId(Long dinnerId) {
        this.dinnerId = dinnerId;
    }
}
