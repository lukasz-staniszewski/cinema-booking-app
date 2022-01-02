package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.exception.ResourceNotFoundException;
import com.thaichicken.cinemabooking.model.ReservationEntity;
import com.thaichicken.cinemabooking.repository.ReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DefaultReservationServiceIntegrationTest {

    @Autowired
    private DefaultReservationService reservationService;

    @MockBean
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp() {
        ReservationEntity reservation1 = new ReservationEntity();
        reservation1.setReservationId(1);
        ReservationEntity reservation2 = new ReservationEntity();
        reservation2.setReservationId(2);

        List<ReservationEntity> allReservations = Arrays.asList(reservation1, reservation2);
        Mockito.when(reservationRepository.findById(reservation1.getReservationId())).thenReturn(java.util.Optional.of(reservation1));
        Mockito.when(reservationRepository.findById(reservation2.getReservationId())).thenReturn(java.util.Optional.of(reservation2));
        Mockito.when(reservationRepository.findAll()).thenReturn(allReservations);

    }

    @Test
    public void whenValidReservationId_thenReservationShouldBeFound() {
        ReservationEntity found1 = reservationService.getReservationById(1);
        ReservationEntity found2 = reservationService.getReservationById(2);
        Assertions.assertEquals(1, found1.getReservationId());
        Assertions.assertEquals(2, found2.getReservationId());
    }

    @Test
    public void whenInvalidReservationId_thenReservationShouldNotBeFound() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> reservationService.getReservationById(3), "Reservation not found with id 3");
    }

    @Test
    public void whenGetAllReservations_thenAllReservationsShouldBeFound() {
        ReservationEntity reservation1 = new ReservationEntity();
        reservation1.setReservationId(1);
        ReservationEntity reservation2 = new ReservationEntity();
        reservation2.setReservationId(2);
        List<ReservationEntity> allReservations = Arrays.asList(reservation1, reservation2);
        Assertions.assertEquals(allReservations, reservationService.getAllReservations());
    }

    @Test
    public void whenDeleteInvalidReservation_thenReservationShouldNotBeDeleted() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> reservationService.deleteReservation(3));
    }

    @Test
    public void whenCreateValidReservation_thenReservationShouldBeFound() {
        ReservationEntity newReservation = new ReservationEntity();
        Mockito.when(reservationRepository.save(newReservation)).thenReturn(newReservation);
        Mockito.when(reservationRepository.findById(3)).thenReturn(java.util.Optional.of(newReservation));


        ReservationEntity found = reservationService.getReservationById(3);
        Assertions.assertEquals(reservationService.createReservation(newReservation), found);
    }
}
