package movie.controller;

import movie.dto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class productController {

    @GetMapping("/api/movies")
    public MoviesResponse movies(){
        // 첫 번째 영화 (인터스텔라)
        MovieResponse interstellar = new MovieResponse(
                1L,
                "인터스텔라",
                "169",
                List.of(
                        new ScreeningResponse(101L,
                                LocalDateTime.of(2025, 9, 20, 13, 30),
                                LocalDateTime.of(2025, 9, 20, 16, 19)),
                        new ScreeningResponse(102L,
                                LocalDateTime.of(2025, 9, 20, 18, 0),
                                LocalDateTime.of(2025, 9, 20, 20, 49))
                )
        );

        // 두 번째 영화 (오펜하이머)
        MovieResponse oppenheimer = new MovieResponse(
                2L,
                "오펜하이머",
                "180",
                List.of(
                        new ScreeningResponse(201L,
                                LocalDateTime.of(2025, 9, 20, 10, 0),
                                LocalDateTime.of(2025, 9, 20, 13, 0))
                )
        );

        // 응답 객체 생성
        return new MoviesResponse(List.of(interstellar, oppenheimer));
    }

    @PostMapping("/reservations")
    public ReservationResponse reservation(@RequestBody ReservationRequest request){
        return new ReservationResponse(1L, request.getScreeningId(), request.getSeats(), 54000L);
    }

}
