package org.coursework.frontend.base.groups;

import java.util.Set;

import org.coursework.backend.group.Group;
import org.coursework.backend.person.student.Student;
import org.coursework.backend.person.student.StudentOption;

public interface BaseGroups {

    /**
     * gets all groups that are being displayed
     */
    Set<Group> getGroups();

    /**
     * swap 2 students from different groups that are being displayed.
     *
     */
    public default void swap(Student origin, Student other) {
        Group gOrigin = getGroups().stream().filter(g -> g.getStudents().contains(origin)).findFirst().get();
        Group gOther = getGroups().stream().filter(g -> g.getStudents().contains(other)).findFirst().get();
        StudentOption oOrigin = gOrigin.getOptions().stream().filter(o -> o.getStudent().equals(origin)).findFirst().get();
        StudentOption oOther = gOther.getOptions().stream().filter(o -> o.getStudent().equals(other)).findFirst().get();
        gOrigin.swap(oOther, oOrigin);
        gOther.swap(oOrigin, oOther);
    }

}
