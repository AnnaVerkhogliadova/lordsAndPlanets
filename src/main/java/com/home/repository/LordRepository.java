package com.home.repository;

import com.home.model.Lord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LordRepository extends CrudRepository<Lord, Integer> {

    @Query(value = "select * from lords", nativeQuery = true)
    Page<Lord> getYoungestLords (Pageable pageable);


    @Query(value = "SELECT l.id, l.name, l.age FROM lords l " +
            "LEFT JOIN planets p ON l.id = p.lord_id " +
            "GROUP BY l.id HAVING count(*) = :planetsCount", nativeQuery = true)
    Page<Lord> getLordWithoutPlanets(@Param("planetsCount") Integer planetsCount, Pageable pageable);


}

