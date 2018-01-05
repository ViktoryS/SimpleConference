package model.entity.builders;

import model.entity.Event;
import model.entity.Lecture;
import model.entity.Role;
import model.entity.User;
import model.entity.proxy.UserProxy;

import java.util.List;

/**
 * Created by Vikki on 23.12.2017.
 */
public class UserBuilder {
    public User user;

    public UserBuilder() {
        user = new UserProxy();
    }

    public UserBuilder setId(Long id){
        user.setId(id);
        return this;
    }

    public UserBuilder setName(String name){
        user.setName(name);
        return this;
    }

    public UserBuilder setLogin(String login){
        user.setLogin(login);
        return this;
    }

    public UserBuilder setPassword(String password){
        user.setPassword(password);
        return this;
    }

    public UserBuilder setEmail(String email){
        user.setEmail(email);
        return this;
    }

    public UserBuilder setRole(Role role){
        user.setRole(role);
        return this;
    }

    public UserBuilder setMark(Long mark){
        user.setMark(mark);
        return this;
    }

    public UserBuilder setEvents(List<Event> events){
        user.setEvents(events);
        return this;
    }

    public UserBuilder setLectures(List<Lecture> lectures){
        user.setLectures(lectures);
        return this;
    }

    public User buildProxy(){
        return user;
    }

    public User build(){
        User simpleUser = new User();
        simpleUser.setId(user.getId());
        simpleUser.setName(user.getName());
        simpleUser.setLogin(user.getLogin());
        simpleUser.setPassword(user.getPassword());
        simpleUser.setEmail(user.getEmail());
        simpleUser.setMark(user.getMark());
        simpleUser.setRole(user.getRole());
        simpleUser.setEvents(user.getEvents());
        simpleUser.setLectures(user.getLectures());
        return simpleUser;
    }
}
