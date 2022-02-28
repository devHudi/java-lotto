package controller;

import constants.LottoConstants;
import domain.InputMoney;
import domain.Lotto;
import domain.LottoNumber;
import domain.LottoQuantity;
import domain.Lottos;
import domain.WinningLotto;
import domain.WinningResult;
import domain.strategy.LottoNumberGenerateStrategy;
import dto.WinningResultDto;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import view.InputView;
import view.ResultView;

public class LottoController {
    private final LottoNumberGenerateStrategy lottoNumberGenerator;

    public LottoController(LottoNumberGenerateStrategy lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public void start() {
        InputMoney inputMoney = getInputMoney();
        LottoQuantity lottoQuantity = getLottoQuantityByInputMoney(inputMoney);
        InputView.printLottoQuantity(lottoQuantity.getLottoQuantity());

        Lottos autoLottos = new Lottos(lottoQuantity, lottoNumberGenerator);
        InputView.printLottos(autoLottos);

        WinningLotto winningLotto = setupWinningLotto();
        WinningResult winningResult = autoLottos.getWinningResultByWinningLotto(winningLotto);
        ResultView.printResult(WinningResultDto.of(winningResult, lottoQuantity));
    }

    private InputMoney getInputMoney() {
        try {
            return new InputMoney(InputView.scanInputMoney());
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return getInputMoney();
        }
    }

    private LottoQuantity getLottoQuantityByInputMoney(InputMoney inputMoney) {
        return new LottoQuantity(inputMoney.getMoney() / LottoConstants.SINGLE_LOTTO_PRICE);
    }

    private WinningLotto setupWinningLotto() {
        Lotto lotto = generateLotto();
        LottoNumber bonusNumber = generateBonusNumber();

        try {
            return new WinningLotto(lotto, bonusNumber);
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return setupWinningLotto();
        }
    }

    private Lotto generateLotto() {
        try {
            List<Integer> winningNumberValues = InputView.scanWinningNumbers();

            Set<LottoNumber> lottoNumbers = winningNumberValues.stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toSet());
            return new Lotto(lottoNumbers);
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return generateLotto();
        }
    }

    private LottoNumber generateBonusNumber() {
        try {
            return new LottoNumber(InputView.scanBonusNumber());
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return generateBonusNumber();
        }
    }
}
