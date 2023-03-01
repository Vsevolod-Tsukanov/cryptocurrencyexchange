package ru.relex.cryptocurrencyexchange.service;

import ru.relex.cryptocurrencyexchange.dto.operationdetail.OperationDetailRequest;
import ru.relex.cryptocurrencyexchange.logger.OperationDetail;

import java.util.List;

public interface OperationDetailService {

    List<OperationDetail> getAllOperationDetailsBetween(OperationDetailRequest request);

    Integer getOperationCountBetween(OperationDetailRequest request);
}
