package ru.relex.cryptocurrencyexchange.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.relex.cryptocurrencyexchange.dto.operationdetail.OperationDetailRequest;
import ru.relex.cryptocurrencyexchange.logger.OperationDetail;
import ru.relex.cryptocurrencyexchange.repository.OperationDetailRepository;
import ru.relex.cryptocurrencyexchange.service.OperationDetailService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationDetailServiceImpl implements OperationDetailService {

    private final OperationDetailRepository operationDetailRepository;

    @Override
    public List<OperationDetail> getAllOperationDetailsBetween(OperationDetailRequest request) {
        return operationDetailRepository.findAllByCallbackTimeBetween(request.getDateFrom(), request.getDateTo());
    }

    @Override
    public Integer getOperationCountBetween(OperationDetailRequest request) {
        return operationDetailRepository.findAllByCallbackTimeBetween(request.getDateFrom(), request.getDateTo())
                .size();
    }


}
