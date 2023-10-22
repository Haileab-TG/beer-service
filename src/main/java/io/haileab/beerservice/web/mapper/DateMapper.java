package io.haileab.beerservice.web.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.*;

@Component
public class DateMapper {
    public Instant toInstant(OffsetDateTime offsetDateTime){
        return offsetDateTime.toInstant();
    }

    public OffsetDateTime toOffsetDateTime(Instant instant){
        return OffsetDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
