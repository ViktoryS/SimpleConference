package model.entity.builders;

import model.entity.Address;
import model.entity.Event;
import model.entity.Lecture;
import model.entity.proxy.EventProxy;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Vikki on 30.12.2017.
 */
public class EventBuilder {
    private Event event;

    public EventBuilder() {
        this.event = new EventProxy();
    }

    public EventBuilder setId(Long id){
        event.setId(id);
        return this;
    }

    public EventBuilder setName(String name){
        event.setName(name);
        return this;
    }

    public EventBuilder setDate(LocalDate date){
        event.setDate(date);
        return this;
    }

    public EventBuilder setAddress(Address address){
        event.setAddress(address);
        return this;
    }

    public EventBuilder setLecture(Lecture lecture){
        event.setLecture(lecture);
        return this;
    }

    public EventBuilder setRegisteredUsers(List<Event.RegisteredUser> registeredUsers){
        event.setRegisteredUsers(registeredUsers);
        return this;
    }

    public Event buildProxy(){
        return event;
    }

    public Event build(){
        Event simpleEvent = new Event();
        simpleEvent.setId(event.getId());
        simpleEvent.setName(event.getName());
        simpleEvent.setDate(event.getDate());
        simpleEvent.setAddress(event.getAddress());
        simpleEvent.setLecture(event.getLecture());
        simpleEvent.setRegisteredUsers(event.getRegisteredUsers());
        return simpleEvent;
    }
}
