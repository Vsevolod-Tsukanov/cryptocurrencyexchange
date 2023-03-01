package ru.relex.cryptocurrencyexchange.dto.operationdetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationDetailRequest {
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
