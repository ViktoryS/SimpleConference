package model.entity;

/**
 * Created by Vikki on 22.12.2017.
 */
public enum Role {
    ADMINISTRATOR(1l, "ADMINISTRATOR"),
    MODERATOR(2l, "MODERATOR"),
    USER(3l, "USER"),
    SPEAKER(4l, "SPEAKER");

    private final Long id;
    private final String name;

    Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Role findById(Long id){
        for(Role role : Role.values()){
            if(role.getId() == id){
                return role;
            }
        }
        return null;
    }
}
