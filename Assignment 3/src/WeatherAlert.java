import java.util.List;

public class WeatherAlert implements Subject{
    private List<Observer> observers;
    private final WeatherDecisionEngine weatherDecisionEngine;

    public WeatherAlert(WeatherDecisionEngine weatherDecisionEngine) {
        this.weatherDecisionEngine = weatherDecisionEngine;
    }

    public void refresh(){
        if (weatherDecisionEngine.cancellationInEffect()){
            notifyObservers("University classes are cancelled due to " + weatherDecisionEngine.cancellationReason() + ".");
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
