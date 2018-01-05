package model.entity.builders;

import model.entity.Address;
import model.entity.Event;
import model.entity.proxy.AddressProxy;

import java.util.List;

public class AddressBuilder {
    private Address address;

    public AddressBuilder(){
        address = new AddressProxy();
    }

    public AddressBuilder setId(Long id){
        address.setId(id);
        return this;
    }

    public AddressBuilder setCity(String city){
        address.setCity(city);
        return this;
    }

    public AddressBuilder setStreet(String street){
        address.setStreet(street);
        return this;
    }

    public AddressBuilder setHouse(String house){
        address.setHouse(house);
        return this;
    }

    public AddressBuilder setEvents(List<Event> events){
        address.setEvents(events);
        return this;
    }

    public Address buildProxy(){
        return address;
    }

    public Address build(){
        Address simpleAddress = new Address();
        simpleAddress.setId(address.getId());
        simpleAddress.setCity(address.getCity());
        simpleAddress.setStreet(address.getStreet());
        simpleAddress.setHouse(address.getHouse());
        simpleAddress.setEvents(address.getEvents());
        return simpleAddress;
    }

}
