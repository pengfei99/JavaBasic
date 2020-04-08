package org.pengfei.Lesson02_Effective_Java_Programming.S00_Applying_Java_With_Beans.source;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class MyColorsBeanInfo extends SimpleBeanInfo {
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        PropertyDescriptor[] pd=null;
        try {
            /*
            * The PropertyDescriptor constructor that is used is shown here:
            * - PropertyDescriptor(String property, Class<?> beanCls) throws IntrospectionException
            * Here, the first argument is the name of the property, and the second argument is the class of the Bean
            * */
            PropertyDescriptor rectangular=new PropertyDescriptor("rectangular",MyColors.class);
            pd= new PropertyDescriptor[]{rectangular};
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return pd;
    }
}
