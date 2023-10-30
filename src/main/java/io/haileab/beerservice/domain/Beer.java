package io.haileab.beerservice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
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
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @GenericGenerator(name = "UUID",  type = org.hibernate.id.uuid.UuidGenerator.class)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(updatable = false, nullable = false)
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
    private String upc;

    private BigDecimal price;
    private Integer minOnHand;
    private Integer quantityToBrew;

}
