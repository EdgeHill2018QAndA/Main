package org.coursework.frontend.base.login;

import java.util.Optional;

import org.coursework.Main;
import org.coursework.backend.person.Person;

public interface BaseLogin {

    public String getFirstName();
    public String getLastName();
    public void nextPage();

    public default Optional<Person> checkAuthorization() {
        return Main.getPeople().stream().filter(p -> p.getFirstName().equalsIgnoreCase(getFirstName()) && p.getLastName().equalsIgnoreCase(getLastName())).findFirst();
    }

    public default void forceAuthorization(Person person) {
        Main.setLoggedInAs(person);
        nextPage();
    }

}
