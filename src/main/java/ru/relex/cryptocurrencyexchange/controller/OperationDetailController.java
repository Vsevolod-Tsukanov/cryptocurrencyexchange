package ru.relex.cryptocurrencyexchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.relex.cryptocurrencyexchange.dto.operationdetail.OperationDetailRequest;
import ru.relex.cryptocurrencyexchange.logger.OperationDetail;
import ru.relex.cryptocurrencyexchange.service.OperationDetailService;

import java.util.List;

@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class OperationDetailController {

    private final OperationDetailService operationDetailService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN_READ')")
    public List<OperationDetail> getAllOperationDetailsBetween(@RequestBody OperationDetailRequest request) {
        return operationDetailService.getAllOperationDetailsBetween(request);
    }

    @PostMapping("/count")
    @PreAuthorize("hasAuthority('ADMIN_READ')")
    public Integer getOperationCountBetween(@RequestBody OperationDetailRequest request) {
        return operationDetailService.getOperationCountBetween(request);
    }

}
