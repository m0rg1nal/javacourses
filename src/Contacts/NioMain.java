package Contacts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class NioMain {
    public static void main(String[] args) {
        Path filePath = Path.of("src/Contacts/contactsNio.txt");

        if (!(Files.exists(filePath))){
            try {
                Files.createFile(filePath);
                System.out.println("File created at: " + filePath.toAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Ivan", "1232352451"));
        contacts.add(new Contact("Maria", "54265767"));
        contacts.add(new Contact("Yaroslav", "435671467"));
        contacts.add(new Contact("Arseniy", "246711433"));
        contacts.add(new Contact("Anton", "134673725"));

        writeContactsToFile(contacts, filePath);

        System.out.println(readAllContacts(filePath));
    }

    static void writeContactsToFile(List<Contact> contacts, Path filePath){
        try (FileChannel writeChannel = FileChannel.open(filePath, StandardOpenOption.READ, StandardOpenOption.WRITE)){
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            for (Contact contact : contacts){
                byteBuffer.put((contact.getName() + " " + contact.getPhoneNumber() + "\n").getBytes());

            }
            byteBuffer.flip();
            writeChannel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static List<Contact> readAllContacts(Path filePath){
        List<Contact> contacts = new ArrayList<>();
        StringBuilder output = new StringBuilder();
        try (FileChannel readChannel = new FileInputStream(filePath.toString()).getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            readChannel.read(byteBuffer);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                output.append((char) byteBuffer.get());
            }

            String[] lines = output.toString().split("\n");
            for (String line : lines) {
                String[] parts = line.split(" ");
                String name = parts[0];
                String phoneNumber = parts[1];
                contacts.add(new Contact(name, phoneNumber));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
