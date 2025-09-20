package domain;

enum SeatGrade {
    S(18_000L),
    A(15_000L),
    B(12_000L);

    private final long price;

    SeatGrade(long price) {
        this.price = price;
    }

    public long getPrice() {
        return price;
    }
}
