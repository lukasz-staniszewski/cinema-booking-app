package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.exception.ResourceNotFoundException;
import com.thaichicken.cinemabooking.model.ShowTimeEntity;
import com.thaichicken.cinemabooking.repository.ShowTimeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DefaultShowTimeService implements ShowTimeService {

    private final ShowTimeRepository showTimeRepository;

    public DefaultShowTimeService(ShowTimeRepository showTimeRepository) {
        this.showTimeRepository = showTimeRepository;
    }

    @Override
    public ShowTimeEntity createShowTime(ShowTimeEntity showTime) {
        return showTimeRepository.save(showTime);
    }

    @Override
    public ShowTimeEntity updateShowTime(Integer id, ShowTimeEntity showTime) {
        return showTimeRepository.findById(id)
                .map(showTime1 -> {
                    showTime1.setMovieByMovieId(showTime.getMovieByMovieId());
                    showTime1.setDate(showTime.getDate());
                    showTime1.setHour(showTime.getHour());
                    return showTimeRepository.save(showTime1);
                }).orElseThrow(() -> new ResourceNotFoundException("Show Time not found with id " + id));
    }

    @Override
    public void deleteShowTime(Integer id) {
        ShowTimeEntity showTime = showTimeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Show Time not found with id " + id));
        showTimeRepository.delete(showTime);
    }

    @Override
    public List<ShowTimeEntity> getAllShowTimes() {
        return showTimeRepository.findAll();
    }

    @Override
    public ShowTimeEntity getShowTimeById(Integer id) {
        return showTimeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Show Time not found with id " + id));
    }

    public List<ShowTimeEntity> getShowTimesByDate(Date date) {
        return showTimeRepository.getAllByDate(date);
    }
}
