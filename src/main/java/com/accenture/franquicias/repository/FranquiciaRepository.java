package com.accenture.franquicias.repository;

import com.accenture.franquicias.model.Franquicia;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranquiciaRepository extends ReactiveMongoRepository<Franquicia, String> {
}