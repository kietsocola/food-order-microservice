package com.foodordermicroservice.orderservice.infrastructure.message.outbox;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "order_outbox")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OutBoxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String aggregateType;
    private String aggregateId;
    private String type;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private String payload;        // JSON event data
    private String status;         // PENDING, SENT, FAILED
    private Instant createdAt;
}

