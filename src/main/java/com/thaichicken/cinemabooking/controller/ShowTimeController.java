package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.model.ShowTimeEntity;
import com.thaichicken.cinemabooking.service.DefaultShowTimeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("show_times")
public class ShowTimeController {

    private final DefaultShowTimeService showTimeService;

    public ShowTimeController(DefaultShowTimeService showTimeService) {
        this.showTimeService = showTimeService;
    }

    @GetMapping
    public List<ShowTimeEntity> getAllShowTimes() {
        return showTimeService.getAllShowTimes();
    }

    @GetMapping("/date")
    public List<ShowTimeEntity> getShowTimesByDate(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return showTimeService.getShowTimesByDate(date);
    }

    @GetMapping("/show_time/{id}")
    public ShowTimeEntity getShowTime(@PathVariable(value = "id") Integer id) {
        return showTimeService.getShowTimeById(id);
    }

    @PostMapping("/show_time")
    public ShowTimeEntity createShowTime(@RequestBody ShowTimeEntity showTime) {
        return showTimeService.createShowTime(showTime);
    }

    @PutMapping("/show_time/{id}")
    public ShowTimeEntity updateShowTime(@PathVariable(value = "id") Integer id,
                                         @RequestBody ShowTimeEntity showTime) {
        return showTimeService.updateShowTime(id, showTime);
    }

    @DeleteMapping("/show_time/{id}")
    public ResponseEntity<HttpStatus> deleteShowTime(@PathVariable(value = "id") Integer id) {
        showTimeService.deleteShowTime(id);
        return ResponseEntity.ok().build();
    }
}
