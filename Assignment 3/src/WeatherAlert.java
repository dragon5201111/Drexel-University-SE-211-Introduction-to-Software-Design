import java.util.ArrayList;
import java.util.List;

public class WeatherAlert implements Subject{
    private final List<Observer> observers;
    private final WeatherDecisionEngine weatherDecisionEngine;

    public WeatherAlert(WeatherDecisionEngine weatherDecisionEngine) {
        this.weatherDecisionEngine = weatherDecisionEngine;
        this.observers = new ArrayList<>();
    }

    public void refresh(){
        if (this.weatherDecisionEngine.cancellationInEffect()){
            notifyObservers("University classes are cancelled due to " + this.weatherDecisionEngine.cancellationReason() + ".");
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : this.observers) {
            observer.update(message);
        }
    }
}
