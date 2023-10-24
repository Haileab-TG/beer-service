package io.haileab.beerservice.service;

import io.haileab.beerservice.domain.Beer;
import io.haileab.beerservice.exceptions.NotFoundException;
import io.haileab.beerservice.repository.BeerRepo;
import io.haileab.beerservice.web.mapper.BeerMapper;
import io.haileab.beerservice.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService{
    private final BeerRepo beerRepo;
    private final BeerMapper beerMapper;
    @Override
    public BeerDTO getById(UUID beerId) {
        Beer beer = beerRepo.findById(beerId).orElseThrow(NotFoundException::new);
        return beerMapper.toBeerDto(beer);
    }

    @Override
    public BeerDTO save(BeerDTO beerDTO) {
        Beer beer = beerMapper.toBeer(beerDTO);
        beer.setCreatedDate(Instant.now());
        beer.setLastModifiedDate(Instant.now());

        return beerMapper.toBeerDto(beerRepo.save(beer));
    }

    @Override
    public BeerDTO updateById(UUID beerId, BeerDTO beerDTO) {
        Beer beerInDB = beerRepo.findById(beerId).orElseThrow(NotFoundException::new);
        beerInDB.setBeerName(beerDTO.getBeerName());
        beerInDB.setBeerStyle(beerDTO.getBeerStyle());
        beerInDB.setPrice(beerDTO.getPrice());
        beerInDB.setUpc(beerDTO.getUpc());
        beerInDB.setQuantityOnHand(beerDTO.getQuantityOnHand());
        beerInDB.setLastModifiedDate(Instant.now());

        return beerMapper.toBeerDto(
                beerRepo.save(beerInDB)
        );
    }
}
