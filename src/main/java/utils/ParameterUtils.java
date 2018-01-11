package utils;

import exceptions.NullParatemerException;

/**
 * Created by Vikki on 10.01.2018.
 */
public final class ParameterUtils {
    public static void checkNull(String... params) throws NullParatemerException{
        for(String param: params){
            if(param==null || param.equals("")){
                throw new NullParatemerException();
            }
        }
    }
}
