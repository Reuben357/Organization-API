package models;

import java.util.Objects;

public class Department {
    private String name;
    private String description;
    private int total_employees;
    private int id;

    public Department(String name, String description, int total_employees, int id) {
        this.name = name;
        this.description = description;
        this.total_employees = total_employees;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotal_employees() {
        return total_employees;
    }

    public void setTotal_employees(int total_employees) {
        this.total_employees = total_employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getTotal_employees() == that.getTotal_employees() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getTotal_employees());
    }
}
