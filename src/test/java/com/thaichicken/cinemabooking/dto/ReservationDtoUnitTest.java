package com.thaichicken.cinemabooking.dto;

import com.thaichicken.cinemabooking.model.ClientEntity;
import com.thaichicken.cinemabooking.model.ReservationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;

public class ReservationDtoUnitTest {
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertReservationEntityToReservationDto_thenCorrect() {
        ReservationEntity reservation = new ReservationEntity();
        reservation.setReservationId(1);
        reservation.setTimestamp(Timestamp.valueOf("2022-01-01 00:00:00"));
        ClientEntity client = new ClientEntity();
        client.setClientId(1);
        client.setName("Test");
        client.setSurname("Test");
        client.setEmail("test@test.com");
        reservation.setClientByClientId(client);

        ReservationDTO reservationDTO = modelMapper.map(reservation, ReservationDTO.class);
        Assertions.assertEquals(reservationDTO.getReservationId(), reservation.getReservationId());
        Assertions.assertEquals(reservationDTO.getTimestamp(), reservation.getTimestamp());
        Assertions.assertEquals(reservationDTO.getClientId(), reservation.getClientByClientId().getClientId());
        Assertions.assertEquals(reservationDTO.getClientName(), reservation.getClientByClientId().getName());
        Assertions.assertEquals(reservationDTO.getClientSurname(), reservation.getClientByClientId().getSurname());
        Assertions.assertEquals(reservationDTO.getClientEmail(), reservation.getClientByClientId().getEmail());
    }

    @Test
    public void whenConvertReservationDtoToReservationEntity_thenCorrect() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservationId(1);
        reservationDTO.setTimestamp(Timestamp.valueOf("2022-01-01 00:00:00"));

        ReservationEntity reservation = modelMapper.map(reservationDTO, ReservationEntity.class);
        Assertions.assertEquals(reservationDTO.getReservationId(), reservation.getReservationId());
        Assertions.assertEquals(reservationDTO.getTimestamp(), reservation.getTimestamp());
    }
}
