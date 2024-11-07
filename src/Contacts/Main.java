package Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/Contacts/contacts.txt");
        file.createNewFile();

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Ivan", "1232352451"));
        contacts.add(new Contact("Maria", "54265767"));
        contacts.add(new Contact("Yaroslav", "435671467"));
        contacts.add(new Contact("Arseniy", "246711433"));
        contacts.add(new Contact("Anton", "134673725"));

        try (BufferedWriter writer=new BufferedWriter(new FileWriter(file))){
            for (Contact contact : contacts){
                writer.write(contact.getName() + " " + contact.getPhoneNumber() + "\n");
            }
        }
        catch (IOException e){
            e.getStackTrace();
        }

        System.out.println(readAllContacts(file));
    }

    public static List<Contact> readAllContacts(File file){
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader=new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null){
                String name = line.split(" ")[0];
                String phone = line.split(" ")[1];
                contacts.add(new Contact(name, phone));
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return contacts;
    }
}
