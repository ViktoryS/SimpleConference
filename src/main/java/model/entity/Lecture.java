package model.entity;

import java.util.List;

/**
 * Created by Vikki on 22.12.2017.
 */
public class Lecture extends Item {
    private String topic;
    private User spiker;
    private List<Event> events;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public User getSpiker() {
        return spiker;
    }

    public void setSpiker(User spiker) {
        this.spiker = spiker;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
