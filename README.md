# 로또 미션 저장소

---

## 코딩 전 설계

<img src="class diagram - 2.png" width="768px">

<br>

## 기능 구현 목록

- [X] 로또 구입 금액을 입력한다.
    - [X] (입력값 검증) 정수가 아닌 문자열을 입력 받을 수 없다.
    - [X] (입력값 검증) 0이하의 숫자를 입력 받을 수 없다.
    - [X] (입력값 검증, 선택) 1000으로 나누어 떨어지지 않는 금액은 입력받을 수 없다.
- [X] 로또를 생성한다.
    - [X] 로또 생성 개수는 구입금액 /1000
    - [X] 랜덤 번호 6개를 생성한다
        - [X] 번호의 범위는 `1 ~ 45`
- [ ] 생성된 로또 개수와 각 로또들을 출력한다.
- [ ] 당첨 번호를 입력한다.
    - [ ] 일반 당첨 번호를 입력한다.
        - [X] 각 번호는 `, ` 로 구분한다 (ex `1, 2, 3, 4, 5, 6`)
        - [X] (입력값 검증) 입력 숫자의 개수는 6개여야 한다.
        - [X] (입력값 검증) 정수가 아닌 문자열을 입력 받을 수 없다.
        - [ ] (입력 값 검증) 0이하 46이상의 숫자를 입력 받을 수 없다.
        - [ ] (입력값 검증) 중복된 숫자가 없어야한다.
    - [ ] 보너스 볼 번호를 입력한다.
        - [ ] (입력값 검증) 정수가 아닌 문자열을 입력 받을 수 없다.
        - [ ] (입력값 검증) 0이하 46이상의 숫자를 입력 받을 수 없다.
        - [ ] (입력값 검증) 일반 당첨 번호에 존재하는 번호는 입력받을 수 없다.
- [X] 도메인
    - [X] LottoNumber
        - [X] 유효성 검사 - 1 ~ 45 사이의 정수값으로 생성
        - [X] 동등성 검사를 위한 equals & hashCode 재정의
    - [X] Lotto
        - [X] 유효성 검사
            - [X] 생성자에 전달된 List<Lotto> 의 길이가 6인지 검사
            - [X] 생성자에서 전달받은 List 에 중복되는 LottoNumber 가 포함되어있는지 검사
        - [X] 다른 Lotto 와 비교하여 일치하는 LottoNumber 의 개수를 반환한다.
    - [X] Lottos
        - [X] 유효성 검사
            - [X] 비어있는 Lotto 가 전달 되었는지 검사
        - [X] WinningLotto 를 전달 받아, 당첨 결과를 반환
    - [X] WinningLotto
        - [X] 유효성 검사
            - [X] BonusNumber 가 Lotto 에 포함되어 있는지 검사
    - [X] InputMoney
        - [X] 유효성 검사
            - [X] 1000 미만 입력 시 IAE 발생
            - [X] 1000으로 나누어 떨어지지 않는 금액 입력 시 IAE 발생
    - [X] RandomLottoNumberGenerateStrategy
        - [X] 랜덤 번호 7개 생성
- [ ] 총 수익률을 (`당첨금액/로또구입금액`) 계산한다.
- [ ] 당첨 통계를 출력한다.
