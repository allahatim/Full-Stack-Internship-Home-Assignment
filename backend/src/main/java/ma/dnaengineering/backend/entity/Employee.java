package ma.dnaengineering.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {
    @Id
    private long id;
    private String EmployeeName;
    private String JobTitle;
    private String Salary;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public Employee(){
    }

    public Employee(long id, String employeeName, String jobTitle, String salary) {
        super();
        this.id = id;
        EmployeeName = employeeName;
        JobTitle = jobTitle;
        Salary = salary;
    }
}
