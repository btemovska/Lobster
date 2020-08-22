package com.example.lobster.repositories;

import com.example.lobster.model.Specie;
import org.springframework.data.repository.CrudRepository;

public interface SpecieRepository extends CrudRepository<Specie, Long> {
    Specie findSpecieByType(String type);


}
