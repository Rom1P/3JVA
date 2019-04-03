package entities;

public class Picture {
    private String name;
    private String description;
    private String date;
    private String category;

    public Picture(String name, String description, String date, String category) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.category = category;
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

    public String getcategory() {
        return category;
    }

    public void setcategory(String category) {
        this.category = category;
    }

}
