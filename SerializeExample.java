package MiscClasses;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.io.File;
import java.io.IOException;

public class SerializeExample {

    public static void main(String[] args) throws IOException {
        File file = new File("fallout4.ser");
        if (file.exists()) {
            // deserialize and print
            byte[] data = FileUtils.readFileToByteArray(file);
            Game fallout4 = SerializationUtils.deserialize(data);
            System.out.println(fallout4);
        } else {
            // create game and serialize
            System.out.println("Your game does not exist... creating one...");
            Game fallout4 = new Game("Fallout 4", "RPG", 60);
            byte[] data = SerializationUtils.serialize(fallout4);
            FileUtils.writeByteArrayToFile(file,data);
        }
    }
}