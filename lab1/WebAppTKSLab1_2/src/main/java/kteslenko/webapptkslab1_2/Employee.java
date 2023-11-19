package kteslenko.webapptkslab1_2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {
    private static int employeeNum = 0;
    private int id;
    private String name;
    private LocalDate birthday;
    private boolean gender;
    private double salary;
    private int programLanguage;


    public Employee(String name, String birthday, String gender, double salary, String programLanguage) {
        employeeNum++;
        id = employeeNum;
        this.name = name;
        this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        this.gender = "male".equals(gender);
        this.salary = salary;
        switch (programLanguage) {
            case ".NET":
                this.programLanguage = 1;
                break;
            case "PHP":
                this.programLanguage = 2;
                break;
            case "Java":
                this.programLanguage = 3;
                break;
            default:
                this.programLanguage = 3;
        }
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String getGender() {
        return gender ? "male" : "female";
    }

    public boolean isGender() {
        return gender;
    }

    public String getSalaryCurrency() {
        return String.format("$%.2f", salary);
    }

    public String getSalaryStr() {
        return String.format("$%.2f", salary);
    }

    public double getSalary() {
        return salary;
    }

    public String getProgramLanguage() {
        String plName = "";
        switch (programLanguage) {
            case 1:
                plName = ".NET";
                break;
            case 2:
                plName = "PHP";
                break;
            case 3:
                plName = "Java";
                break;
        }
        return plName;
    }
}

