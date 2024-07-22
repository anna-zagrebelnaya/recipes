package com.example.recipes.controller.dto;

public enum FilterByCalories {
    LESS_100(null, 100),
    MORE_100_LESS_200(100, 200),
    MORE_200_LESS_300(200, 300),
    MORE_300_LESS_400(300, 400),
    MORE_400_LESS_500(400, 500),
    MORE_500(500, null),
    ALL(null, null);

    private Integer lowerBound;
    private Integer upperBound;

    FilterByCalories(Integer lowerBound, Integer upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public Integer getLowerBound() {
        return lowerBound;
    }

    public Integer getUpperBound() {
        return upperBound;
    }
}
