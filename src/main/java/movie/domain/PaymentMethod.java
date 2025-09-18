package movie.domain;

import java.util.function.Function;

public enum PaymentMethod {
    CREDIT_CARD(price -> price * 95 / 100), // 5% discount
    CASH(price -> price * 98 / 100);      // 2% discount

    private final Function<Long, Long> discountFunction;

    PaymentMethod(Function<Long, Long> discountFunction) {
        this.discountFunction = discountFunction;
    }

    public long applyDiscount(long price) {
        return discountFunction.apply(price);
    }
}
