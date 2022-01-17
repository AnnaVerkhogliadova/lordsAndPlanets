package com.home.service;

import com.home.model.Planet;
import com.home.repository.PlanetRepository;
import com.home.service.exception.LordIdNotFoundException;
import com.home.service.exception.PlanetNotFoundException;

import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {
    private final PlanetRepository planetRepository;
    private static final String CONSTANT_FK = "fk_planets_lord_id__lords_id";


    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public Planet createPlanet(String name) {
        return planetRepository.save(new Planet(name, null));
    }


    public Planet getPlanet(int getId) {
        return planetRepository.findById(getId).
                orElseThrow(PlanetNotFoundException::new);
    }

    public Planet addLord(int id, int lordId) {
        Planet oldPlanet = getPlanet(id);
        oldPlanet.setLordId(lordId);
        try {
            return planetRepository.save(oldPlanet);
        } catch (DataIntegrityViolationException ex) {
            ServerErrorMessage sem = ((PSQLException) ex.getCause().getCause())
                    .getServerErrorMessage();
            if (sem == null) {
                throw ex;
            }
            if (CONSTANT_FK.equals(sem.getConstraint())) {
                throw new LordIdNotFoundException();
            }
            throw ex;
        }
    }

    public void deletePlanet(int planetId) {
        getPlanet(planetId);
        planetRepository.deleteById(planetId);
    }
}
