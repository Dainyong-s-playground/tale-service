package kkk.dainyong.tale.repository;

import kkk.dainyong.tale.model.Reservation;
import kkk.dainyong.tale.model.dto.ReservationDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReservationRepository {
    void insertReservation(Reservation reservation);
    void deleteReservation(@Param("reservationId") Long reservationId);
    List<ReservationDTO> getReservationsByProfileId(@Param("profileId") Long profileId);
}
