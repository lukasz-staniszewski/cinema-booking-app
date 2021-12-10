package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.model.ShowTimeEntity;
import com.thaichicken.cinemabooking.service.DefaultShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("show_times")
public class ShowTimeController {

    @Autowired
    private DefaultShowTimeService showTimeService;

    @GetMapping
    public List<ShowTimeEntity> getAllShowTimes() {
        return showTimeService.getAllShowTimes();
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
