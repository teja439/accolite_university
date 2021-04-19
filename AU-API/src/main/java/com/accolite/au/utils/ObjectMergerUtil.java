package com.accolite.au.utils;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class ObjectMergerUtil {

    public <T> void compareObjects(T updatedObject, T originalObject, T mergedObject){
        System.out.println("Class of new object is: " + updatedObject.getClass());
        System.out.println("Class of original object is: " + originalObject.getClass());
        System.out.println("Class of merged object is: " + mergedObject.getClass());
        System.out.println("Updated Object is" + updatedObject.toString());
        System.out.println("Original Object is" + originalObject.toString());
        System.out.println("Merged Object is" + mergedObject.toString());
    }

    public <T> T merger(T updatedObject, T originalObject) throws IllegalAccessException {
        /** Needed: Provide 2 DTO objects or two freshly prepared model objects,
         * because when we retrieve an object from DB then its of type classA$HibernateProxy$EQCyNRV9 and not only class A,
         * so it would cause IllegalAccessException
         */

        if(originalObject == null && updatedObject == null){
            return null;
        }
        else if(originalObject == null){
            return updatedObject;
        }
        else if(updatedObject == null){
            return originalObject;
        }
        Object mergedObject = originalObject;
        // Object mergedObject = originalObject.getClass().cast(originalObject);
        //this.compareObjects(updatedObject, originalObject, mergedObject);

        for(Field field: originalObject.getClass().getDeclaredFields()){
            field.setAccessible(true);
            Object value1 = field.get(updatedObject);
            Object value2 = field.get(originalObject);
            Object value = (value1 != null) ? value1 : value2;
            field.set(mergedObject, value);
        }
        return (T) mergedObject;
    }
}