package util;

import model.Person;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;

public class FileService {
    private static final Logger logger = Logger.getLogger(FileService.class.getName());
    private static final String PATH = "src" + File.separator + "resources" + File.separator + "log.txt";

    public static void save(List<Person> data) {
        try (
                FileOutputStream out = new FileOutputStream(PATH, false);
                ObjectOutputStream oos = new ObjectOutputStream(out)
        ) {
            data.forEach(person -> {
                try {
                    oos.writeObject(person);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            logger.info(String.format("List of Persons was saved to file %s. Lists size: %d", PATH, data.size()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void read(List<Person> data) {
        try (
                FileInputStream in = new FileInputStream(PATH);
                ObjectInputStream ois = new ObjectInputStream(in)
        ) {
            while (true) {
                try {
                    Person person = (Person) ois.readObject();
                    data.add(person);
                } catch (Exception ex) {
                    System.err.println("End of reader file ");
                    break;
                }
            }
            logger.info(String.format("List of Persons has been read from file %s. Lists size: %d", PATH, data.size()));
        } catch (Exception ex) {
            logger.warning(String.format("End of reader file %s", PATH));
            ex.printStackTrace();
        }
    }
}
