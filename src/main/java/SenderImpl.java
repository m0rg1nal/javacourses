import java.util.ArrayList;
import java.util.List;

public class SenderImpl implements Sender{
    private List<Observer> observers = new ArrayList<>();
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(User user) {
        for (Observer observer : observers){
            observer.update(user);
        }
    }
}
