package org.coursework.backend.person.student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.coursework.Main;

import org.coursework.backend.roles.Role;
import org.coursework.database.core.CoreDatabaseLink;
import org.coursework.database.table.TableBuilder;
import org.coursework.database.table.TableLink;

public class StudentOption implements TableLink {

    int id;
    Student student;
    List<Role> roles = new ArrayList<>();

    public StudentOption(Student student, Role... roles) throws SQLException {
        this(student, true, roles);
    }

    public StudentOption(Student student, Collection<Role> roles) throws SQLException {
        this(student, true, roles);
    }

    public StudentOption(Student student, boolean useDatabase, Role... roles) throws SQLException {
        this(TableLink.getUniquieId(StudentOptionTableBuilder.TABLE_NAME, useDatabase), student, roles);
    }

    public StudentOption(Student student, boolean useDatabase, Collection<Role> roles) throws SQLException {
        this(TableLink.getUniquieId(StudentOptionTableBuilder.TABLE_NAME, useDatabase), student, roles);
    }

    public StudentOption(int id, Student student, Role... roles) {
        this(id, student, Arrays.asList(roles));
    }

    public StudentOption(int id, Student student, Collection<Role> collection) {
        this.student = student;
        this.roles.addAll(collection);
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Optional<Role> getPreferredRole() {
        if (roles.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(roles.get(0));
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void registerRole(Role... roles) {
        for (Role role : roles) {
            this.roles.add(role);
        }
    }

    public void deregisterRole(Role... roles) {
        for (Role role : roles) {
            this.roles.remove(role);
        }
    }

}
