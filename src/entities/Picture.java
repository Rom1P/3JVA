package entities;

import javax.persistence.*;

@Entity

@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private String date;

    @Column(name = "category")
    private String category;

    @Column(name = "path")
    private String path;

    @Column(name = "usernamePublisher")
    private String usernamePublisher;

    public Picture() {

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsernamePublisher() {
        return usernamePublisher;
    }

    public void setUsernamePublisher(String usernamePublisher) {
        this.usernamePublisher = usernamePublisher;
    }
}
