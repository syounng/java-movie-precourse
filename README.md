# java-movie-precourse

# 프로젝트 소개
영화 예매 시스템

# 기능 소개
- [x] Seat : 행, 열, 좌석 중복 여부 확인, 좌석 가격 조회
- [x] Movie : 영화 제목, 러닝 타임
- [ ] Schedule: 상영 시간.
  - [ ] 시작/끝 시간을 가진다
  - [ ] 상영 시간이 영화 러닝 타입과 같아야 한다.
  - [ ] 
- Screening : 할인 혜택을 적용, 상영 시간이 겹치는지 확인
- DiscountPolicy : 할인 조건
- DiscountCondition : 할인 조건 확인 (무비데이 조건, 시간 조건)
- Reservation : 모든 예매 정보, 결제 금액 (정가)
- PaymentDiscountPolicy : 할인된 금액
- Payment : 최종 결제 금액 계산 (포인트 차감, 결제 수단에 의한 할인)
- Point : 포인트 차감