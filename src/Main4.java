import exception.NotFoundException;
import model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static util.InputService.inputFirstName;
import static util.InputService.inputLastName;

public class Main4 {
    private static final List<Person> personList = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(Main4.class.getName());

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String menu = "Menu: \n 1.Add\n 2.Show\n 3.Exit";
        boolean exit = false;

        while (!exit) {
            System.out.println(menu);
            switch (scanner.nextLine()) {
                case "1":
                    addPerson(scanner);
                    break;
                case "2":
                    printResult();
                    break;
                case "3":
                    exit = true;
                    break;
                default:
                    LOGGER.warning("Unknown command");
                    throw new NotFoundException("Unknown command");
            }
        }

    }

    private static void addPerson(Scanner scanner) {
        String firstName = inputFirstName(scanner);
        String lastName = inputLastName(scanner);
        Person person = new Person(firstName, lastName);
        personList.add(person);
        LOGGER.info(String.format("Person added: %s. List size: %d", person, personList.size()));
    }

    private static void printResult() {
        StringBuilder out = new StringBuilder();
        personList.stream().sorted().forEach(person -> out.append(person.toString()).append("\n"));
        System.out.println(out);
    }
}
