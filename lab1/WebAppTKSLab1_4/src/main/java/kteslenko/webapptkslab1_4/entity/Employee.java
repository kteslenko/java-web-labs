package kteslenko.webapptkslab1_4.entity;

import java.time.LocalDate;
import java.time.Period;
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

    public boolean isYoung() {
        return getAge() >= 20 && getAge() <= 30;
    }

    public String getName() {
        return name;
    }

    public String getBirthdayAsString() {
        return birthday.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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

    public String getProgrammingLanguage() {
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

    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthday, currentDate);
        return period.getYears();
    }


}

