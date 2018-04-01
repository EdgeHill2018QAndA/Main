package org.coursework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import org.coursework.backend.group.Group;
import org.coursework.backend.group.GroupTableBuilder;
import org.coursework.backend.person.Person;
import org.coursework.backend.person.PersonTableBuilder;
import org.coursework.backend.person.student.StudentOption;
import org.coursework.backend.person.student.StudentOptionTableBuilder;
import org.coursework.backend.roles.Role;
import org.coursework.backend.roles.RoleTableBuilder;
import org.coursework.database.core.CoreDatabaseLink;
import org.coursework.database.table.TableBuilder;
import org.coursework.database.table.TableLink;
import org.coursework.frontend.face.connection.ConnectionPanel;
import org.coursework.frontend.face.frame.MFrame;

public class Main {

    private static final List<Person> PEOPLE = new ArrayList<>();
    private static final List<Role> ROLES = new ArrayList<>();
    private static final List<StudentOption> ENTERED_OPTIONS = new ArrayList<>();
    private static final List<TableBuilder<? extends TableLink>> TABLE_BUILDERS = new ArrayList<>();

    private static int recommendedGroupSize = 5;
    private static int minRolesSize = 1;
    private static int maxRolesSize = 5;
    private static Collection<Group> groups = new ArrayList<>();
    private static CoreDatabaseLink coreDatabaseLink;
    private static Person loggedInAs;
    private static MFrame frame;

    public static MFrame getFrame() {
        return frame;
    }

    public static Set<Group> getGroups() {
        return new HashSet<>(groups);
    }

    public static void setGroups(Collection<Group> groupsC) {
        System.out.println("Set groups");
        groups = groupsC;
    }

    public static Optional<Person> getLoggedInAs() {
        return Optional.ofNullable(loggedInAs);
    }

    public static void setLoggedInAs(Person person) {
        loggedInAs = person;
    }

    public static Optional<CoreDatabaseLink> getDatabaseLink() {
        return Optional.ofNullable(coreDatabaseLink);
    }

    public static void setDatabaseLink(CoreDatabaseLink link) {
        coreDatabaseLink = link;
    }

    public static int getRecommendedGroupSize() {
        return recommendedGroupSize;
    }

    public static boolean setRecommendedGroupSize(int size) {
        if (size > 1) {
            return false;
        }
        recommendedGroupSize = size;
        return true;
    }

    public static int getMaxRoleSize() {
        return maxRolesSize;
    }

    public static boolean setMaxRoleSize(int size) {
        if (size > ROLES.size()) {
            return false;
        }
        if (size < 1) {
            return false;
        }
        maxRolesSize = size;
        return true;
    }

    public static int getMinRoleSize() {
        return minRolesSize;
    }

    public static boolean setMinRoleSize(int size) {
        if (size > ROLES.size()) {
            return false;
        }
        if (size < 1) {
            return false;
        }
        minRolesSize = size;
        return true;
    }

    public static Set<TableBuilder<? extends TableLink>> getTableBuilders() {
        return new HashSet<>(TABLE_BUILDERS);
    }

    public static Set<Person> getPeople() {
        return new HashSet<>(PEOPLE);
    }

    @SuppressWarnings("unchecked")
	public static <T extends Person> Set<T> getPeople(Class<T> class1) {
        return (Set<T>) PEOPLE.stream().filter(p -> class1.isInstance(p)).collect(Collectors.toSet());
    }

    public static Set<Role> getRoles() {
        return new HashSet<>(ROLES);
    }

    public static Set<StudentOption> getEnteredOptions() {
        return new HashSet<>(ENTERED_OPTIONS);
    }

    public static void register(Person... people) {
        for (Person person : people) {
            PEOPLE.add(person);
        }
    }

    public static void register(Role... roles) {
        for (Role role : roles) {
            ROLES.add(role);
        }
    }

    public static void register(StudentOption... options) {
        for (StudentOption option : options) {
            ENTERED_OPTIONS.add(option);
        }
    }

    @SafeVarargs
	public static void register(TableBuilder<? extends TableLink>... builders) {
        for (TableBuilder<? extends TableLink> builder : builders) {
            TABLE_BUILDERS.add(builder);
        }
    }

    public static void deregister(Person... people) {
        for (Person person : people) {
            PEOPLE.remove(person);
        }
    }

    public static void deregister(Role... roles) {
        for (Role role : roles) {
            ROLES.remove(role);
        }
    }

    public static void deregister(StudentOption... options) {
        for (StudentOption option : options) {
            ENTERED_OPTIONS.remove(option);
        }
    }

    @SafeVarargs
	public static void deregister(TableBuilder<? extends TableLink>... builders) {
        for (TableBuilder<? extends TableLink> builder : builders) {
            TABLE_BUILDERS.remove(builder);
        }
    }

    public static void main(String[] args) {
        System.out.println("Loading backend");
        register(new RoleTableBuilder(), new GroupTableBuilder(), new PersonTableBuilder(), new StudentOptionTableBuilder());
        System.out.println("Registered TableBuilders");
        JPanel panel = new ConnectionPanel();
        frame = new MFrame("Test", panel);
        System.out.println("Loaded");

    }

}
