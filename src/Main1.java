import model.Person;

import java.util.Scanner;

import static util.InputService.*;

public class Main1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstName = inputFirstName(scanner);
        String lastName = inputLastName(scanner);

        Person person = new Person(firstName, lastName);

        System.out.println("Person: ");
        System.out.println(person);
    }
}