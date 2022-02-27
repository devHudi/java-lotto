package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTest {
    private Set<LottoNumber> lottoNumbers;

    @BeforeEach
    void setup() {
        lottoNumbers = new HashSet<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    @Test
    @DisplayName("6개 LottoNumber 를 전달 받아 Lotto 생성")
    void createLotto() {
        // given
        Lotto lotto = new Lotto(lottoNumbers);

        // when & then
        assertThat(lotto).isNotNull();
    }

    @Test
    @DisplayName("Lotto 생성시 전달된 List 길이가 6이 아니면 IAE 발생")
    void createLottoWithInvalidSizeOfLottoNumbersShouldFail() {
        // given
        lottoNumbers.add(new LottoNumber(8));

        // then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("\\d개의 숫자를 골라주세요.");
    }

    @Test
    @DisplayName("Lotto는 다른 Lotto 객체를 전달 받아 같은 숫자의 수를 반환할 수 있다")
    void lottoReturnsNumberOfSameNumberCount() {
        // given
        Lotto lotto = new Lotto(lottoNumbers);

        // when
        Set<LottoNumber> newLottoNumbers = new HashSet<>(lottoNumbers);
        newLottoNumbers.remove(new LottoNumber(6));
        newLottoNumbers.add(new LottoNumber(7));
        Lotto anotherLotto = new Lotto(newLottoNumbers);

        // then
        assertThat(lotto.getSameNumberCount(anotherLotto)).isEqualTo(5);
    }

    @ParameterizedTest(name = "포함 여부를 확인할 숫자 : {0}")
    @CsvSource(value = {"1,true", "6,true", "7,false"})
    @DisplayName("Lotto에 LottoNumber를 전달하여 포함 여부 확인")
    void lottoContainsLottoNumberTest(int lottoNumber, boolean expected) {
        // given
        LottoNumber lottoNumber1 = new LottoNumber(lottoNumber);

        // when
        Lotto lotto = new Lotto(lottoNumbers);

        // then
        assertThat(lotto.containsLottoNumber(lottoNumber1)).isEqualTo(expected);
    }
}
