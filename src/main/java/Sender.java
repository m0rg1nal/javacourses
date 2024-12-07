public interface Sender {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(User user);
}
