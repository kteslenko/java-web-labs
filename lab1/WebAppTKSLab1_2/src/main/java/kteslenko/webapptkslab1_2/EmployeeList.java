package kteslenko.webapptkslab1_2;

import java.util.ArrayList;

public class EmployeeList extends ArrayList<Employee> {
    private static final long serialVersionUID = 1L;
    private static EmployeeList instance;

    private EmployeeList() {
    }

    public static EmployeeList getInstance() {
        if (instance == null) {
            instance = new EmployeeList();
            instance.add(new Employee("Employee 1", "14.08.1972", "male", 100000, "Java"));
            instance.add(new Employee("Employee 2", "12.05.1990", "female", 55000, "Java"));
            instance.add(new Employee("Employee 3", "19.08.2004", "male", 80000, "Java"));
            instance.add(new Employee("Employee 4", "30.04.1996", "female", 72000, ".NET"));
            instance.add(new Employee("Employee 5", "08.03.1986", "male", 65000, "Java"));
            instance.add(new Employee("Employee 6", "11.05.2001", "female", 58000, ".NET"));
            instance.add(new Employee("Employee 7", "07.07.1997", "female", 70000, "Java"));
            instance.add(new Employee("Employee 8", "15.08.1997", "male", 75000, "Java"));
            instance.add(new Employee("Employee 9", "20.09.1998", "female", 68000, "Java"));
            instance.add(new Employee("Employee 10", "25.10.1998", "male", 63000, "Java"));
            instance.add(new Employee("Employee 11", "26.12.2002", "female", 52000, "PHP"));
            instance.add(new Employee("Employee 12", "10.11.2000", "male", 67000, "Java"));
        }
        return instance;
    }
}
