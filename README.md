# 스프링부트로 배우는 디자인 패턴

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)

> 다양한 디자인 패턴을 스프링부트 환경에서 쉽게 이해하고 구현해보는 프로젝트입니다.

## 목차
- [프로젝트 소개](#프로젝트-소개)
- [개발 환경](#개발-환경)
- [디자인 패턴](#디자인-패턴)
  - [1. 전략 패턴 (Strategy Pattern)](#1-전략-패턴-strategy-pattern)
    - [1.1. 간단한 버전](#11-간단한-버전---결제-전략)
    - [1.2. 팩토리 패턴 활용 버전](#12-팩토리-패턴-활용-버전---할인-전략)
- [실행 방법](#실행-방법)
- [기여 방법](#기여-방법)
- [라이센스](#라이센스)

## 프로젝트 소개

이 프로젝트는 소프트웨어 개발에서 자주 사용되는 디자인 패턴을 스프링부트 환경에서 실제로 구현하고 테스트해볼 수 있도록 만들어진 예제 모음입니다. 각 디자인 패턴은 실제 비즈니스 시나리오를 기반으로 구현되어 있어 실무에서의 활용법을 쉽게 이해할 수 있습니다.

현재는 전략 패턴이 구현되어 있으며, 앞으로 더 많은 디자인 패턴이 추가될 예정입니다.

## 개발 환경

- Java 17
- Spring Boot 3.4.4
- Gradle
- H2 Database
- Lombok
- JUnit 5

## 디자인 패턴

### 1. 전략 패턴 (Strategy Pattern)

전략 패턴은 알고리즘을 캡슐화하여 런타임에 알고리즘을 선택할 수 있게 해주는 행동 디자인 패턴입니다. 이 패턴은 알고리즘이 자주 변경되거나, 여러 알고리즘 중 하나를 동적으로 선택해야 할 때 유용합니다.

이 프로젝트에서는 전략 패턴을 두 가지 버전으로 구현했습니다:

#### 1.1. 간단한 버전 - 결제 전략

첫 번째 구현은 팩토리 패턴 없이 간단하게 구현한 전략 패턴으로, 결제 방식(신용카드, 카카오페이, 계좌이체)을 동적으로 선택하는 예제입니다.

**주요 구성 요소:**
- `PaymentStrategy` 인터페이스: 모든 결제 전략의 기본 인터페이스
- 구체적인 전략 클래스들: `CreditCardStrategy`, `KakaoPayStrategy`, `BankTransferStrategy`
- `PaymentContext`: 선택된 전략을 실행하는 컨텍스트 클래스
- `SimplePaymentService`: 요청에 따라 적절한 전략을 선택하는 서비스

**장점:**
- 구현이 간단하고 이해하기 쉬움
- 스프링의 의존성 주입을 직접 활용

**단점:**
- 새로운 전략을 추가할 때 서비스 코드를 수정해야 함
- 확장성이 제한적임

**샘플 페이지**: `http://localhost:8080/simple-strategy.html`

#### 1.2. 팩토리 패턴 활용 버전 - 할인 전략

두 번째 구현은 팩토리 패턴을 활용한 전략 패턴으로, 다양한 할인 전략(퍼센트 할인, 고정 금액 할인)을 관리하는 예제입니다.

**주요 구성 요소:**
- `DiscountStrategy` 인터페이스: 모든 할인 전략의 기본 인터페이스
- 구체적인 전략 클래스들: `PercentageDiscountStrategy`, `FixedAmountDiscountStrategy`, `NoDiscountStrategy`
- `DiscountStrategyFactory`: 전략 객체들을 생성하고 관리하는 팩토리
- `PriceService`: 적절한 전략을 사용하여 가격을 계산하는 서비스

**장점:**
- 높은 확장성: 새로운 전략을 쉽게 추가할 수 있음
- 코드 중복 감소: 전략 생성 로직이 한 곳에 집중
- OCP(Open-Closed Principle) 준수: 기존 코드 수정 없이 새 전략 추가 가능

**샘플 페이지**: `http://localhost:8080/`

### 전략 패턴 UML 다이어그램

간단한 전략 패턴:

```
+----------------+         +--------------------+
| PaymentContext |<>-------| PaymentStrategy   |
+----------------+         +--------------------+
| -strategy      |         | +pay(amount)       |
| +setStrategy() |         +--------------------+
| +executePayment|                ^
+----------------+                |
                        +---------+---------+---------+
                        |                   |         |
            +-----------------+  +----------------+  +-------------------+
            | CreditCardStrategy | KakaoPayStrategy | BankTransferStrategy |
            +-----------------+  +----------------+  +-------------------+
            | +pay(amount)    |  | +pay(amount)   |  | +pay(amount)      |
            +-----------------+  +----------------+  +-------------------+
```

팩토리를 활용한 전략 패턴:

```
+------------------+       +--------------------+
| PriceService     |<>-----| DiscountStrategy   |
+------------------+       +--------------------+
| -currentStrategy |       | +applyDiscount()   |
| +calculatePrice()|       | +getStrategyName() |
| +changeStrategy()|       +--------------------+
+------------------+                ^
        |                          |
        |                 +--------+--------+---------+
        v                 |                 |         |
+--------------------+    |                 |         |
| StrategyFactory    |    |                 |         |
+--------------------+    |                 |         |
| -strategies        |    |                 |         |
| +getStrategy()     |    |                 |         |
| +getAllStrategies()|    |                 |         |
+--------------------+    |                 |         |
                          |                 |         |
                +-----------------+ +----------------+ +-------------+
                | PercentageStrategy | FixedAmountStrategy | NoDiscountStrategy |
                +-----------------+ +----------------+ +-------------+
                | +applyDiscount()  | | +applyDiscount() | | +applyDiscount() |
                | +getStrategyName()| | +getStrategyName()| | +getStrategyName() |
                +-----------------+ +----------------+ +-------------+
```

## 실행 방법

### 요구사항
- JDK 17 이상
- Gradle

### 실행 단계
1. 프로젝트 클론
   ```bash
   git clone https://github.com/yourusername/springboot-design-patterns.git
   cd springboot-design-patterns
   ```

2. 애플리케이션 실행
   ```bash
   ./gradlew bootRun
   ```

3. 웹 브라우저에서 접속
   - 팩토리 패턴 활용 전략 패턴: http://localhost:8080/
   - 간단한 전략 패턴: http://localhost:8080/simple-strategy.html

### API 엔드포인트

#### 할인 전략 API (팩토리 패턴 활용)
- `GET /api/discount/strategy` - 현재 적용된 할인 전략 조회
- `POST /api/discount/strategy` - 할인 전략 변경
- `GET /api/discount/strategies` - 사용 가능한 모든 할인 전략 조회
- `POST /api/discount/calculate` - 가격 계산

#### 결제 전략 API (간단한 구현)
- `POST /api/simple-payment` - 결제 처리
- `GET /api/simple-payment/methods` - 사용 가능한 결제 방법 조회

## 기여 방법

1. 프로젝트를 포크합니다.
2. 새로운 브랜치를 생성합니다 (`git checkout -b feature/amazing-feature`).
3. 변경 사항을 커밋합니다 (`git commit -m 'Add some amazing feature'`).
4. 브랜치에 푸시합니다 (`git push origin feature/amazing-feature`).
5. Pull Request를 생성합니다.

## 라이센스

이 프로젝트는 MIT 라이센스를 따릅니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.
