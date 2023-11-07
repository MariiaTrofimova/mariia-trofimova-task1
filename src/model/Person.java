package model;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Comparable<Person>, Serializable {
    private final String firstName;
    private final String lastName;


    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int compareTo(Person o) {
        return lastName.equals(o.lastName)? firstName.compareTo(o.firstName) : lastName.compareTo(o.lastName);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) && lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}
