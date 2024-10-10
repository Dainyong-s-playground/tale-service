package kkk.dainyong.tale.repository;

import kkk.dainyong.tale.model.dto.Reservation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReservationRepository {
    void insertReservation(Reservation reservation);
    void deleteReservation(@Param("profileId") Long profileId, @Param("readsDay") String readsDay, @Param("title") String title);
    List<Reservation> getReservationsByProfileId(@Param("profileId") Long profileId);
}
