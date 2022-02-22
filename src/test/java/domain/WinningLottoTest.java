package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {
    private Lotto lotto;
    private LottoNumber bonusNumber;

    @BeforeEach
    void setup() {
        lotto = new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));

        bonusNumber = new LottoNumber(7);
    }

    @Test
    @DisplayName("생성자에 Lotto 와 LottoNumber 인스턴스를 전달하여 WinningLotto 인스턴스 생성")
    void createWinningLottoTest() {
        // given
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

        // when & then
        assertThat(winningLotto).isNotNull();
    }

    @Test
    @DisplayName("생성자에 전달된 bonusNumber 가 lotto 에 포함되어 있을 경우 IAE 발생")
    void createWinningLottoWithDuplicateBonusNumberShouldFail() {
        assertThatThrownBy(() -> new WinningLotto(lotto, new LottoNumber(1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("보너스 번호는 로또 번호와 중복될 수 없습니다.");
    }
}
