package com.broker.mensajeria.JMS;


import com.broker.mensajeria.Classes.Temperaturetransaction;
import com.broker.mensajeria.Repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TemperaturaTransactionReceiver {

    @Autowired
    private TemperatureRepository temperaturespoRepository;

    @JmsListener(destination = "TemperatureQueue", containerFactory = "myFactory")
    public void receiveMessage(Temperaturetransaction transaction) {
        System.out.println("Received <" + transaction + ">");
        temperaturespoRepository.save(transaction);
    }

}
