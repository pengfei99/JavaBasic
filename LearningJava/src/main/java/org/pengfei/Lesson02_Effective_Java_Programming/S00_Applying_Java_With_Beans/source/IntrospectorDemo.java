package org.pengfei.Lesson02_Effective_Java_Programming.S00_Applying_Java_With_Beans.source;

import java.beans.*;

public class IntrospectorDemo {
    public static void main(String[] args){
        try {
            Class<?> MyColorsClass = Class.forName("org.pengfei.Lesson02_Effective_Java_Programming.S00_Applying_Java_With_Beans.source.MyColors");
            BeanInfo beanInfo=Introspector.getBeanInfo(MyColorsClass);

            System.out.println("MyColors properties:");
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor propertyDescriptor: propertyDescriptors){
                System.out.println("\t"+propertyDescriptor.getName());
            }

            System.out.println("MyColors events:");
            EventSetDescriptor[] eventDescriptors = beanInfo.getEventSetDescriptors();
            for(EventSetDescriptor eventSetDescriptor:eventDescriptors){
                System.out.println("\t"+eventSetDescriptor.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
