package model.entity;

/**
 * Created by Vikki on 22.12.2017.
 */
public abstract class Item {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
