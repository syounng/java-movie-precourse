package movie.policy;

import movie.domain.Reservation;

public interface DiscountPolicy {
    /**
     * 할인을 적용한 후의 금액을 반환합니다.
     *
     * @param price       할인이 적용될 현재 금액
     * @param reservation 할인 대상 예매 정보
     * @return 할인 후 금액
     */
    long applyDiscount(long price, Reservation reservation);
}
