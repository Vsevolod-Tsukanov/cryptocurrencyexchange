package ru.relex.cryptocurrencyexchange.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(schema = "log_schema", name = "operation_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operation_performer_id")
    private UUID operationPerformerId;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "callback_time")
    private LocalDate callbackTime;

    @Column(name = "balance_after_operation")
    private BigDecimal balanceAfterOperation;


}
