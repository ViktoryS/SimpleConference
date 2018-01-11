package service;

import service.impl.UserServiceImpl;

/**
 * Created by Vikki on 10.01.2018.
 */
public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();

    public static ServiceFactory getInstance(){
        return serviceFactory;
    }

    public UserService createUserService(){
        return UserServiceImpl.getInstance();
    }
}
