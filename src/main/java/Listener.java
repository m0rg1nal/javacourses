public class Listener implements Observer{
    private String name;

    public Listener(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(User user) {
        System.out.println(name + " has recieved new data: " + user.toString() + "\n");
    }
}
