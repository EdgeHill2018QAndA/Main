package org.coursework;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import org.coursework.backend.group.Group;
import org.coursework.backend.person.Person;
import org.coursework.backend.person.staff.Staff;
import org.coursework.backend.person.student.StudentOption;
import org.coursework.backend.roles.Role;
import org.coursework.console.ConsoleHelper;
import org.coursework.database.core.CoreDatabaseLink;
import org.coursework.frontend.face.frame.MFrame;
import org.coursework.frontend.face.login.LoginPanel;

public class Main {

    private static final List<Person> PEOPLE = new ArrayList<>();
    private static final List<Role> ROLES = new ArrayList<>();
    private static final List<StudentOption> ENTERED_OPTIONS = new ArrayList<>();

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

    public static Set<Person> getPeople() {
        return new HashSet<>(PEOPLE);
    }
    
    public static <T extends Person> Set<T> getPeople(Class<T> class1){
        return (Set<T>)PEOPLE.stream().filter(p -> class1.isInstance(p)).collect(Collectors.toSet());
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
    
    public static void saveToDatabase(){
        saveToDatabase(coreDatabaseLink);
    }
    
    public static void saveToDatabase(CoreDatabaseLink link){
        saveRolesToDatabase();
        savePeopleToDatabase();
        saveOptionsToDatabase();
    }
    
    private static void saveRolesToDatabase() {
        getRoles().forEach(r -> {
            try{
                r.saveInTable();
            }catch(SQLException e){
                System.err.println("Failed to save Role " + r.getDisplayName() + ":" + r.getId());
                e.printStackTrace();
            }
        });
    }
    
    private static void savePeopleToDatabase(){
        getPeople().forEach(p -> {
            try {
                p.saveInTable();
            } catch (SQLException ex) {
                System.err.println("Failed to save person " + p.getName());
                ex.printStackTrace();
            }
        });
    }
    
    private static void saveOptionsToDatabase(){
        getEnteredOptions().stream().forEach(o -> {
            try {
                o.saveInTable();
            } catch (SQLException ex) {
                System.err.println("Could not save StudentOption: " + o.getStudent().getName() + ", " + o.getRoles().toString());
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        register(new Staff(1, "User", "One")); //test code
        ConsoleHelper helper = new ConsoleHelper(System.out);
        helper.print("Loading: {][[[[[}");
        //JPanel panel = new ConnectionPanel();
        JPanel panel = new LoginPanel();
        helper.delete().print("Loading: {[][[[[}");
        frame = new MFrame("Test", panel);
        helper.delete().print("Loading: {[][][[}");
        coreDatabaseLink = new CoreDatabaseLink("jdbc:derby://localhost:1527/testDatabase", "username", "password");
        try {
            coreDatabaseLink.loadDriver();
            coreDatabaseLink.openConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        helper.delete().print("Loading: {[][][]}").newLine().print("Loaded");

    }

}
