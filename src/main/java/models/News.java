package models;

import java.util.Objects;

public class News {
    private String news;
    private int department_id;
    private int id;

    public News(String news, int department_id, int id) {
        this.news = news;
        this.department_id = department_id;
        this.id = id;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
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
        if (!(o instanceof News)) return false;
        News news1 = (News) o;
        return getDepartment_id() == news1.getDepartment_id() &&
                getId() == news1.getId() &&
                Objects.equals(getNews(), news1.getNews());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNews(), getDepartment_id(), getId());
    }
}
