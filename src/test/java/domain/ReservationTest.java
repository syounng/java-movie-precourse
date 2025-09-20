package domain;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    void 결제_금액을_계산한다(){
        var reservation = new Reservation(new Screening(List.of(
                new Schedule(LocalTime.of(1, 0), LocalTime.of(2, 4)),
                new Schedule(LocalTime.of(2, 5), LocalTime.of(4, 4))
                )),
                List.of(new Seat("A",1, SeatGrade.S), new Seat("B",1, SeatGrade.A))
                );
        long price = reservation.calculatedPrice();
        assertThat(price).isEqualTo(33000);
    }

    @Test
    void 예매하고자하는_좌석이_겹치면_예외를_던진다(){
        assertThatIllegalArgumentException().isThrownBy(()-> new Reservation(new Screening(List.of(
                    new Schedule(LocalTime.of(1, 0), LocalTime.of(2, 4)),
                    new Schedule(LocalTime.of(2, 5), LocalTime.of(4, 4))
            )),
                    List.of(new Seat("A",1, SeatGrade.S), new Seat("A",1, SeatGrade.A))
            )
        );


    }

}