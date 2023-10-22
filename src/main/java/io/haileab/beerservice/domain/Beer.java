package io.haileab.beerservice.domain;

import io.haileab.beerservice.bootstrap.BeerStyleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Beer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdDate;

    @UpdateTimestamp
    private Instant lastModifiedDate;

    private String beerName;

    @Enumerated(EnumType.STRING)
    private BeerStyleEnum beerStyle;

    @Column(unique = true)
    private Long upc;

    private BigDecimal price;
    private Integer minOnHand;
    private Integer quantityToBrew;

}
