package io.haileab.beerservice.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class BeerPagedList extends PageImpl<BeerDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 4618796521374990491L;

    public BeerPagedList(List<BeerDTO> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPagedList(List<BeerDTO> content) {
        super(content);
    }
}
