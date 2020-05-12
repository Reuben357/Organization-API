package models;

import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String address;
    private int phone;
    private String email;
    private int department_id;
    private String position;
    private String roles;

    public User(int id, String username, String address, int phone, String email, int department_id, String position, String roles) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.department_id = department_id;
        this.position = position;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                getPhone() == user.getPhone() &&
                getDepartment_id() == user.getDepartment_id() &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getAddress(), user.getAddress()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPosition(), user.getPosition()) &&
                Objects.equals(getRoles(), user.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getAddress(), getPhone(), getEmail(), getDepartment_id(), getPosition(), getRoles());
    }
}
