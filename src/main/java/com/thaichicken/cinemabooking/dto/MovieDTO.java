package com.thaichicken.cinemabooking.dto;

import lombok.Data;

@Data
public class MovieDTO {
    private int movieId;
    private String name;
    private String description;
    private Integer length;
    private Integer productionYear;
    private String type;
    private String director;
}
