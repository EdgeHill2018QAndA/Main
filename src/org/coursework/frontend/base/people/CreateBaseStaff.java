package org.coursework.frontend.base.people;

import java.sql.SQLException;
import org.coursework.Main;
import org.coursework.backend.person.staff.Staff;

public interface CreateBaseStaff extends CreateBasePerson {

    public default Staff createStaff() throws SQLException {
        return new Staff(getFirstName(), getLastName());
    }

    public default Staff registerStaff() throws SQLException {
        return registerStaff(createStaff());
    }

    public default Staff registerStaff(Staff staff) {
        Main.register(staff);
        Main.getDatabaseLink().get().saveData();
        return staff;
    }
}
