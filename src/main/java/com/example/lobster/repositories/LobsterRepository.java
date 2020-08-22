package com.example.lobster.repositories;

import com.example.lobster.model.Lobster;
import org.springframework.data.repository.CrudRepository;

public interface LobsterRepository extends CrudRepository<Lobster, Long> {
}
