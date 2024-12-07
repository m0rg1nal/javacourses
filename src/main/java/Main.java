import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        String apiUrl = "https://jsonplaceholder.typicode.com/posts/1";

        Sender sender = new SenderImpl();
        Listener listener1 = new Listener("Ivan");
        Listener listener2 = new Listener("Anton");

        sender.registerObserver(listener1);
        sender.registerObserver(listener2);

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line).append("\n");
                }
                reader.close();

                System.out.println("Response from server:");
                System.out.println(response);

                ObjectMapper mapper = new ObjectMapper();
                User user = mapper.readValue(response.toString(), User.class);
                sender.notifyObservers(user);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
