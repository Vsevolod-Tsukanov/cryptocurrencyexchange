package ru.relex.cryptocurrencyexchange.logger;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.relex.cryptocurrencyexchange.model.CryptoWallet;
import ru.relex.cryptocurrencyexchange.repository.OperationDetailRepository;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class OperationLogger {

    private final OperationDetailRepository operationDetailRepository;


    public void log(CryptoWallet wallet, String operationType) {
        OperationDetail operationDetail = new OperationDetail();
        operationDetail.setCallbackTime(LocalDate.now());
        operationDetail.setOperationType(operationType);
        operationDetail.setBalanceAfterOperation(wallet.getBalance());
        operationDetail.setOperationPerformerId(wallet.getWalletOwner().getId());

        operationDetailRepository.save(operationDetail);
    }
}
