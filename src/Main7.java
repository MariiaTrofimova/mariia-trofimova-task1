import model.Person;
import util.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import static util.InputService.inputFirstName;
import static util.InputService.inputLastName;

public class Main7 {
    private static final List<Person> personList = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Main7.class.getName());

    private static boolean exitFlag = false;

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);

        while (!exitFlag) {
            menu.printMenu();
            MenuItem item = menu.geItem();
            item.execute(personList);
        }
    }

    private interface Exec {
        void exec(List<Person> data) throws Exception;
    }

    private static class MenuItem {
        private final String name; // Наименование пункта меню
        private final Exec exec; // Доступное действие

        public MenuItem(String name, Exec exec) {
            this.name = name;
            this.exec = exec;
        }

        public String getName() {
            return name;
        }

        public void execute(List<Person> data) throws Exception {
            exec.exec(data);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MenuItem)) return false;
            MenuItem menuItem = (MenuItem) o;
            return Objects.equals(name, menuItem.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static class Menu {
        private static final List<MenuItem> items = new ArrayList<>();
        static Scanner scanner;

        public Menu(Scanner scanner) {
            Menu.scanner = scanner;
            fillItems();
        }

        private static void fillItems() {
            MenuItem add = new MenuItem("Add", data -> addPerson(scanner, data));
            items.add(add);
            MenuItem show = new MenuItem("Show", Menu::showList);
            items.add(show);
            MenuItem showUnique = new MenuItem("Show sorted unique", Menu::showUniqueList);
            items.add(showUnique);
            MenuItem saveToFile = new MenuItem("Save to file", FileService::save);
            items.add(saveToFile);
            MenuItem readFromFile = new MenuItem("Read from file", FileService::read);
            items.add(readFromFile);
            MenuItem clearData = new MenuItem("Clear data", List::clear);
            items.add(clearData);
            MenuItem exit = new MenuItem("Exit", data -> exitFlag = true);
            items.add(exit);
        }

        private static void showUniqueList(List<Person> data) {
            if (data.isEmpty()) {
                System.out.println("The List is empty.");
            } else {
                data.stream().distinct().sorted().forEach(System.out::println);
            }
        }

        private static void showList(List<Person> data) {
            if (data.isEmpty()) {
                System.out.println("The List is empty.");
            } else {
                data.stream().sorted().forEach(System.out::println);
            }
        }

        private static void addPerson(Scanner scanner, List<Person> data) {
            String firstName = inputFirstName(scanner);
            String lastName = inputLastName(scanner);
            Person person = new Person(firstName, lastName);
            data.add(person);
            logger.info(String.format("Person added: %s. List size: %d", person, data.size()));
        }

        public void printMenu() {
            StringBuilder out = new StringBuilder();
            AtomicInteger i = new AtomicInteger(1);
            items.forEach(item -> out.append(i.getAndIncrement()).append(" ").append(item.getName()).append("\n"));
            System.out.println(out);
        }

        public MenuItem geItem() {
            int idx = 0;
            try {
                idx = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                logger.warning(e.getMessage());
            }
            return items.get(idx - 1);
        }
    }
}