package JMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TemperatureReciver {

    @Autowired
    private TemperatureRepository temperaturespoRepository;

    @JmsListener(destination = "TemperatureQueue", containerFactory = "myFactory")
    public void receiveMessage(Temperaturetransaction transaction) {
        System.out.println("Received <" + transaction + ">");
        temperaturespoRepository.save(transaction);
    }

}
