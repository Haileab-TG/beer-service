package io.haileab.beerservice.service;

import io.haileab.beerservice.domain.BeerStyleEnum;
import io.haileab.beerservice.domain.Beer;
import io.haileab.beerservice.exceptions.NotFoundException;
import io.haileab.beerservice.repository.BeerRepo;
import io.haileab.beerservice.web.mapper.BeerMapper;
import io.haileab.beerservice.web.mapper.BeerMapperType;
import io.haileab.beerservice.web.mapper.BeerMapperWithDecorator;
import io.haileab.beerservice.web.model.BeerDTO;
import io.haileab.beerservice.web.model.BeerPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService{
    private final BeerRepo beerRepo;
    private final BeerMapper beerMapper;
    private final BeerMapperWithDecorator beerMapperWithDecorator;

//    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventory == false")
    @Override
    public BeerDTO getById(UUID beerId, boolean showInventory) {
        System.out.println("I was called");
        Beer beer = beerRepo.findById(beerId).orElseThrow(NotFoundException::new);
        BeerMapperType mapperToUse = showInventory ? beerMapperWithDecorator : beerMapper;
        return mapperToUse.toBeerDto(beer);
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
        beerInDB.setMinOnHand(beerDTO.getMinOnHand());
        beerInDB.setLastModifiedDate(Instant.now());

        return beerMapper.toBeerDto(
                beerRepo.save(beerInDB)
        );
    }

//    @Cacheable(cacheNames = "beerListCache", condition = "#showInventory == false")
    @Override
    public BeerPagedList listBeers(
            String beerName,
            BeerStyleEnum beerStyle,
            PageRequest pageRequest,
            boolean showInventory
    ) {
        System.out.println("I was called");
        Page<Beer> beerPage;
        if(!ObjectUtils.isEmpty(beerName) && !ObjectUtils.isEmpty(beerStyle)){
            beerPage = beerRepo.findBeerByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        }else if(!ObjectUtils.isEmpty(beerName) && ObjectUtils.isEmpty(beerStyle)){
            beerPage = beerRepo.findBeerByBeerName(beerName, pageRequest);
        }else if(ObjectUtils.isEmpty(beerName) && !ObjectUtils.isEmpty(beerStyle)){
            beerPage = beerRepo.findBeerByBeerStyle(beerStyle, pageRequest);
        }else {
            beerPage = beerRepo.findAll(pageRequest);
        }

        BeerMapperType mapperToUse = showInventory ? beerMapperWithDecorator : beerMapper;

        List<BeerDTO> beerDTOList = beerPage.stream()
                .map(mapperToUse::toBeerDto)
                .toList();


        return new BeerPagedList(
              beerDTOList,
                PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements()
        );
    }

    @Override
    public BeerDTO getBeerByUPC(String upc, boolean showInventory) {
        BeerMapperType mapperToUse = showInventory ? beerMapperWithDecorator : beerMapper;
        return mapperToUse.toBeerDto(beerRepo.findBeerByUpc(upc));
    }
}
