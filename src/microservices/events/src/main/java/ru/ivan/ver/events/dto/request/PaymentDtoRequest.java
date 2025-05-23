package ru.ivan.ver.events.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDtoRequest {
    @JsonProperty("payment_id")
    Long paymentId;
    @JsonProperty("user_id")
    Long userId;
    Double amount;
    String status;
    LocalDateTime timestamp;
    @JsonProperty("method_type")
    String methodType;
}
