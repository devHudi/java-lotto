package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("WinningLotto 테스트")
public class WinningLottoTest {
    private Lotto lotto;
    private LottoNumber bonusNumber;

    @BeforeEach
    void setup() {
        lotto = new Lotto(Set.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
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
                .hasMessage(WinningLotto.ERROR_MESSAGE_FOR_DUPLICATE_BONUS_NUMBER);
    }

    @Test
    @DisplayName("Lotto 를 전달받아 Rank를 반환한다")
    void getRankByLotto() {
        // given
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

        // when
        Rank rank = winningLotto.getRankByLotto(lotto);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }
}
