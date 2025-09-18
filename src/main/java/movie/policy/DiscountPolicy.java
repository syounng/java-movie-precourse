package movie.policy;

import movie.domain.Reservation;

public interface DiscountPolicy {
    /**
     * 할인을 적용하고 할인된 금액을 반환합니다.
     *
     * @param reservation 할인 대상 예매 정보
     * @return 할인 금액
     */
    long calculateDiscountAmount(Reservation reservation);
}
