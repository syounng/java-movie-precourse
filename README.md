# 👾 Next Step 캠퍼스 프로그래밍 1기 프리코스 미션 👾

## 🚀 프로젝트 소개
이 프로젝트는 Java를 사용하여 영화 예매 시스템을 구현하는 프리코스 과제입니다.

## ✨ 주요 기능
- **영화 예매**: 좌석 등급(S, A, B)에 따라 차등 가격이 적용된 영화 예매 기능
- **예매 유효성 검증**: 단일/다중 예매 시 좌석 및 상영 시간 중복 방지
- **복합 할인 시스템**: 무비데이(비율), 시간(정액), 포인트, 결제 수단에 따른 순차적 다단계 할인 적용

## 📋 요구사항
### 상영 및 예매
- 하나의 영화는 주어진 상영 가능 기간에 하루에도 여러 차례 상영될 수 있습니다.
- 상영 시간은 영화의 상영 길이와 상영관 운영 시간에 따라 정해집니다.
- 여러 영화를 한 번에 예매할 수 있으며, 시간이 겹치는 상영은 함께 예매할 수 없습니다.

### 좌석 선택
- 사용자는 영화와 상영 시간을 고른 뒤 원하는 좌석을 선택할 수 있습니다.
- 좌석은 행(알파벳)과 열(숫자)로 표시됩니다. (예: A1, C3, E4)
- 이미 예약된 좌석은 다시 선택할 수 없습니다.

### 가격 정책
- **좌석 등급별 가격**
  - S석: 18,000원
  - A석: 15,000원
  - B석: 12,000원
- **할인 조건**
  - **무비데이**: 매월 10일, 20일, 30일에 상영되는 영화는 **10% 할인**됩니다.
  - **시간 할인**: 오전 11시 이전 또는 오후 8시 이후에 시작하는 상영은 **2,000원 할인**됩니다.
  - 중복 할인 시, 무비데이 할인(비율)이 먼저 적용된 후 시간 할인(정액)이 적용됩니다.
- **포인트 및 결제 수단 할인**
  - 보유 포인트를 사용하여 예매 금액에서 차감할 수 있습니다.
  - 포인트 적용 후, 결제 수단에 따라 추가 할인이 적용됩니다.
    - 신용카드: **5% 할인**
    - 현금: **2% 할인**

## 🔮 구현 전략
- 책임-주도 설계(RDD)를 통한 객체지향 모델링
    - 요구사항으로부터 객체 선정
    - 서비스가 제공해야 할 행동(Use Case)으로부터 책임을 선정
    - 각 책임을 적절한 객체에게 할당하여 '도메인 모델' 생성
    - 도메인 모델을 바탕으로 '클래스 다이어그램' 생성
    - 클래스 다이어그램을 바탕으로 전체적인 서비스의 클래스 구조 구현
    - 상세 로직과 테스트 코드 구현
- 점진적인 구현
    1) 기본적인 예매 기능 구현
    2) 좌석 중복 방지 기능 구현
    3) 여러 영화 예매 시 상영 시간 중복 방지 기능 구현
    4) 복잡한 할인 규칙 적용 기능 구현
    5) 사용자 포인트 사용 기능 구현
- 사용자 경험을 우선으로 하는 로직 설계
    - 포인트 사용은 최종 할인 금액을 확인한 후에 적용하도록 하여 사용자 입장에서 가장 편리하고 합리적인 방식으로 구현

## 🕹️ 설계 과정
[참고 포스팅](https://nkdev.tistory.com/category/etc/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8)

### 도메인 모델
![도메인 모델](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdna%2FJk5Zj%2FbtsQGlkYtXn%2FAAAAAAAAAAAAAAAAAAAAAFSKXzhaYGPcC5mKMOvyTfI0DQPwtgbka6l3Isxor8v-%2Fimg.png%3Fcredential%3DyqXZFxpELC7KVnFOS48ylbz2pIh7yKj8%26expires%3D1759244399%26allow_ip%3D%26allow_referer%3D%26signature%3DgG3SZe5zyDahrl24xKTaHE3oGlQ%253D)

### 객체의 책임
![객체의 책임](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdna%2FEpRSi%2FbtsQEiwmaZe%2FAAAAAAAAAAAAAAAAAAAAANs6-dkHE0LdVU3UKH8GaY99_19N8CLJ-1CqdVdN0DT0%2Fimg.png%3Fcredential%3DyqXZFxpELC7KVnFOS48ylbz2pIh7yKj8%26expires%3D1759244399%26allow_ip%3D%26allow_referer%3D%26signature%3DoEaukvShHb%252BB7lJMDo%252Bq%252BsbjJQI%253D)

### 클래스 다이어그램
![클래스 다이어그램](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdna%2FGMHX1%2FbtsQFMJZO6L%2FAAAAAAAAAAAAAAAAAAAAAFr82A9GvqQ7GBm2Ksm8vUPpic-6LEp0lljdzmoIr5zi%2Fimg.png%3Fcredential%3DyqXZFxpELC7KVnFOS48ylbz2pIh7yKj8%26expires%3D1759244399%26allow_ip%3D%26allow_referer%3D%26signature%3D76qdAC6fgi053zmYO2nrAPdK9L0%253D)

## 💭 프로젝트 진행 후기



## 🛠️ 사용 기술
- Java 11
- Gradle

## ⚙️ 실행 방법
1.  저장소를 복제합니다.
    ```bash
    git clone https://github.com/your-username/java-movie-precourse.git
    ```
2.  프로젝트 디렉토리로 이동합니다.
    ```bash
    cd java-movie-precourse
    ```
3.  프로젝트를 빌드합니다.
    ```bash
    ./gradlew build
    ```
4.  애플리케이션을 실행합니다.
    ```bash
    ./gradlew run
    ```

## 📝 라이선스
이 프로젝트는 MIT 라이선스를 따릅니다.