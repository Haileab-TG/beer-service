package io.haileab.beerservice.web.model;

import io.haileab.beerservice.bootstrap.BeerStyleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Currency;
import org.hibernate.validator.constraints.Mod10Check;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 871662316799140343L;
    @Null
    private UUID id;
    @Null
    private Integer version;

    @Null
    private OffsetDateTime createdDate;
    @Null
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    private String beerName;

    @NotNull
    private BeerStyleEnum beerStyle;

    @Positive
    @NotNull
    private Long upc;

    @NotNull
    @Positive
    private BigDecimal price;

    @Positive
    @NotNull
    private Integer quantityOnHand;
}
