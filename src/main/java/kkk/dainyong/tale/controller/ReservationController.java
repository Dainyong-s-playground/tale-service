package kkk.dainyong.tale.controller;

import kkk.dainyong.tale.model.Reservation;
import kkk.dainyong.tale.model.dto.ReservationDTO;
import kkk.dainyong.tale.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 예약 추가
    @PostMapping("/add")
    public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) {
        reservationService.addReservation(reservation);
        return ResponseEntity.ok("예약이 성공적으로 추가되었습니다.");
    }

    // 예약 취소
    @DeleteMapping("/cancle/{reservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok("예약이 성공적으로 취소되었습니다.");
    }

    // 특정 프로필의 예약 목록 조회
    @GetMapping("/load/{profileId}")
    public ResponseEntity<List<ReservationDTO>> getReservations(@PathVariable Long profileId) {
        List<ReservationDTO> reservations = reservationService.getReservationsByProfile(profileId);
        return ResponseEntity.ok(reservations);
    }
}