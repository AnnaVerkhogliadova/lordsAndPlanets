package com.home.controller;

import com.home.dto.AddLordRequest;
import com.home.dto.PlanetRequest;
import com.home.dto.PlanetResponse;
import com.home.model.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.home.service.LordService;
import com.home.service.PlanetService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/planets")
public class PlanetController {
    private final PlanetService planetService;
    private final LordService lordService;

    @Autowired
    public PlanetController(PlanetService planetService, LordService lordService) {
        this.planetService = planetService;
        this.lordService = lordService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public PlanetResponse createPlanet(@Valid @RequestBody PlanetRequest request) {
        Planet planet = planetService.createPlanet(request.getName());

        return new PlanetResponse(planet.getId(), planet.getName(), null);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlanetResponse addLord(@PathVariable("id") int id,
                                  @Valid @RequestBody AddLordRequest request) {
        Planet planet = planetService.addLord(id, request.getLordId());
        return new PlanetResponse(planet.getId(), planet.getName(), planet.getLordId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlanet(@PathVariable("id") int id) {
        planetService.deletePlanet(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlanetResponse getPlanet (@PathVariable("id") int id) {
        Planet planet = planetService.getPlanet(id);
        return new PlanetResponse(planet.getId(), planet.getName(), planet.getLordId());
    }

}
