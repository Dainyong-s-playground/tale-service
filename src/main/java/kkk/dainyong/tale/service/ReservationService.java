package kkk.dainyong.tale.service;

import kkk.dainyong.tale.model.Reservation;
import kkk.dainyong.tale.model.dto.ReservationDTO;
import kkk.dainyong.tale.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // 예약 추가
    public void addReservation(Reservation reservation) {
        reservationRepository.insertReservation(reservation);
    }

    // 예약 취소
    public void cancelReservation(Long reservationId) {
        reservationRepository.deleteReservation(reservationId);
    }

    // 특정 프로필의 예약 목록 조회
    @Transactional(readOnly = true)
    public List<ReservationDTO> getReservationsByProfile(Long profileId) {
        return reservationRepository.getReservationsByProfileId(profileId);
    }
}