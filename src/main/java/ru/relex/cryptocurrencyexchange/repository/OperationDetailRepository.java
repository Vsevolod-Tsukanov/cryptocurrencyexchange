package ru.relex.cryptocurrencyexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.cryptocurrencyexchange.logger.OperationDetail;

import java.time.LocalDate;
import java.util.List;

public interface OperationDetailRepository extends JpaRepository<OperationDetail, Long> {
    List<OperationDetail> findAllByCallbackTimeBetween(LocalDate dateFrom, LocalDate dateTo);
}
