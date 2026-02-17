import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Interfaces (simulates) with several weather-related data sources, fuses the information, and automatically
// decides whether class cancellations are in effect.
public class WeatherDecisionEngine {

    // Simulate whether cancellation is in effect due to weather
    boolean cancellationInEffect(){
        return new Random().nextBoolean();
    }

    // Simulates a cancellation reason (kind of weather)
    String cancellationReason(){
        List<String> cancellationReasons = new ArrayList<>();
        cancellationReasons.add("rain");
        cancellationReasons.add("fog");
        cancellationReasons.add("snow");
        cancellationReasons.add("thunder");
        cancellationReasons.add("sleet");
        cancellationReasons.add("hail");
        cancellationReasons.add("extreme heat");
        return cancellationReasons.get(new Random().nextInt(cancellationReasons.size()));
    }

    // Other methods that provide information about a cancellation decision
}
