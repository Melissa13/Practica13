package com.broker.mensajeria.Repository;

import com.broker.mensajeria.Classes.Temperaturetransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemperatureRepository extends MongoRepository<Temperaturetransaction, String> {}
