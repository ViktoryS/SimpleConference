package model.entity.builders;

import model.entity.Event;
import model.entity.Lecture;
import model.entity.User;
import model.entity.proxy.LectureProxy;

import java.util.List;

/**
 * Created by Vikki on 23.12.2017.
 */
public class LectureBuilder {
    private Lecture lecture;

    public LectureBuilder(){
        lecture = new LectureProxy();
    }

    public LectureBuilder setId(Long id){
        lecture.setId(id);
        return this;
    }

    public LectureBuilder setTopic(String topic){
        lecture.setTopic(topic);
        return this;
    }

    public LectureBuilder setSpiker(User spiker){
        lecture.setSpiker(spiker);
        return this;
    }

    public LectureBuilder setEvents(List<Event> events){
        lecture.setEvents(events);
        return this;
    }

    public Lecture buildProxy(){
        return lecture;
    }

    public Lecture build(){
        Lecture simpleLecture = new Lecture();
        simpleLecture.setId(lecture.getId());
        simpleLecture.setTopic(lecture.getTopic());
        simpleLecture.setSpiker(lecture.getSpiker());
        simpleLecture.setEvents(lecture.getEvents());
        return simpleLecture;
    }
}
