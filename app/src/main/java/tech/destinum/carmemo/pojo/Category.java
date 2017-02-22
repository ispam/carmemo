package tech.destinum.carmemo.pojo;

public class Category {
    private String name, description;
    private int image;
    private long _id;

    public Category(String name, String description, int image, long _id) {
        this.name = name;
        this.description = description;
        this.image = image;
        this._id = _id;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }
}
