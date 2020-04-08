package org.pengfei.Lesson02_Effective_Java_Programming.S00_Applying_Java_With_Beans;

public class S00_Applying_Java_With_Beans {

    /********************************************* 0.1 Introduction ***********************************************/

    /*
    * A Java Bean is a software component that has been designed to be reusable in a variety of different environments.
    * There is no restriction on the capability of a Bean. It may perform a simple function, such as obtaining an
    * inventory value, or a complex function, such as forecasting the performance of a stock portfolio.
    *
    * A JavaBean is a Java class that should follow the following conventions:
    * - It should have a no-arg constructor.
    * - It should be Serializable.
    * - It should provide methods to set and get the values of the properties, known as getter and setter methods.
    *
    * The following list enumerates some of the benefits that JavaBeans technology provides for a component developer:
    * - A Bean obtains all the benefits of Java’s “write-once, run-anywhere” paradigm.
    * - The properties, events, and methods of a Bean that are exposed to another application can be controlled.
    * - Auxiliary software can be provided to help configure a Bean. This software is only needed when the design-time
    *   parameters for that component are being set. It does not need to be included in the run-time environment.
    * - The state of a Bean can be saved in persistent storage and restored at a later time.
    * - A Bean may register to receive events from other objects and can generate events that are sent to other objects.
    * */

    /********************************************* 0.2 Introspection ***********************************************/

    /*
    * At the core of Bean programming is introspection. This is the process of analyzing a Bean to determine its
    * capabilities. This is an essential feature of the JavaBeans API because it allows another application, such as
    * a design tool, to obtain information about a component. Without introspection, the JavaBeans technology could
    * not operate.
    *
    * There are two ways in which the developer of a Bean can indicate which of its properties, events, and methods
    * should be exposed:
    * 1. Use the naming convention
    * 2. Use an additional class that extends the BeanInfo interface that explicitly supplies this information.
    * */

    /*********************** 0.3 Naming convention for java bean introspection **********************************/

    /** 0.3.1 Design Patterns for Properties
     *
     * A property is a subset of a Bean’s state. The values assigned to the properties determine the behavior and
     * appearance of that component. A property is set through a setter method. A property is obtained by a getter
     * method. There are two types of properties:
     * 1. simple
     * 2. indexed.
     *
     * Simple Properties
     * A simple property has a single value. It can be identified by the following design patterns, where N is the
     * name of the property and T is its type:
     * - public T getN( )
     * - public void setN(T arg)
     * A read/write property has both of these methods to access its values. A read-only property has only a get
     * method. A write-only property has only a set method.
     *
     *
     * Indexed Properties
     * An indexed property consists of multiple values. It can be identified by the following design patterns, where
     * N is the name of the property and T is its type:
     * - public T getN(int index);
     * - public void setN(int index, T value);
     * - public T[] getN();
     * - public void setN(T values[]);
     *
     * In other words, indexed properties work with list of values. So you can get/set list and get/set single value of
     * the list.
     * */

/** 0.3.2 Design Patterns for Events
 *
 * Delegation Event Model
 * Back in the old days, Java used a Chain of Responsibility pattern to process events. For example, when a button is
 * clicked, a event is generated, which then is passed through a chain of components. The chain of components is
 * defined by the hierarchy of classes and interfaces. An event is caught and handled by the handler class. This
 * mechanism was used by Java version 1.0.
 *
 * The processing model of Java 1.1's event hierarchy facilitates more than one receiver subscription. The subscriber
 * thus can send notifications to all of them in response to an change or updates. In the delegation event model, a
 * class designated as an event source generates an event and sends it to one or more listeners. The design scheme is
 * neatly decoupled from the main application logic that generates the event.
 *
 * However, the listeners must register or agree with the event source class to receive any notification. This means
 * that a particular event is processed only by a specific listener. The overhead of going through a chain of
 * containment hierarchy of listeners is eliminated. a event does not need to go through many listeners that do
 * not process the particular event, wasting valuable time. The modern approach made the delegation simple, efficient,
 * and effective in view of its decoupled nature and performance issues.
 *
 * Beans use the delegation event model. Beans can generate events and send them to other objects. These can be
 * identified by the following design patterns, where T is the type of the event:
 * - public void addTListener(TListener eventListener): It adds a listener to a specific event. this version can be used
 *           to multicast an event, which means that more than one listener can register for the event notification.
 * - public void addTListener(TListener eventListener) throws java.util.TooManyListenersException: This version unicasts
 *           the event, which means that the number of listeners can be restricted to one.
 * - public void removeTListener(TListener eventListener): It removes the listener
 */

 /** 0.3.3 Design Patterns for methods
 * Design patterns are not used for naming non-property methods. The introspection mechanism finds all of the public
  * methods of a Bean. Protected and private methods are not presented.
 */

    /*********************** 0.4 BeanInfo interface for java bean introspection **********************************/

    /*
    * As the preceding discussion shows, design patterns implicitly determine what information is available to the
    * user of a Bean. The BeanInfo interface enables you to explicitly control what information is available. The
    * BeanInfo interface defines several methods, including these:
    * - PropertyDescriptor[] getPropertyDescriptors(): It returns an array of objects that provide information about the
    *             properties of a Bean.
    * - EventSetDescriptor[] getEventSetDescriptors(): It returns an array of objects that provide information about the
    *             event.
    * - MethodDescriptor[] getMethodDescriptors(): It returns an array of objects that provide information about the
    *             methods.
    *
    * The classes PropertyDescriptor, EventSetDescriptor, and MethodDescriptor are defined within the java.beans
    * package, and they describe the indicated elements. By implementing these methods, a developer can designate
    * exactly what is presented to a user, bypassing introspection based on design patterns.
    *
    * When creating a class that implements BeanInfo, you must call that class bnameBeanInfo, where bname is the name
    * of the Bean. For example, if the Bean is called MyBean, then the information class must be called MyBeanBeanInfo.
    *
    * To simplify the use of BeanInfo, JavaBeans supplies the SimpleBeanInfo class. It provides default implementations
    * of the BeanInfo interface, including the three methods just shown. You can extend this class and override one or
    * more of the methods to explicitly control what aspects of a Bean are exposed. If you don’t override a method,
    * then design-pattern introspection will be used. For example, if you don’t override getPropertyDescriptors(),
    * then design patterns are used to discover a Bean’s properties.
    * */

    /*********************** 0.5 Bound and Constrained Properties **********************************/

    /*
    * A Bean that has a bound property generates an event when the property is changed. The event is of type
    * PropertyChangeEvent and is sent to objects that previously registered an interest in receiving such
    * notifications. A class that handles this event must implement the PropertyChangeListener interface.
    *
    * A Bean that has a constrained property generates an event when an attempt is made to change its value. It also
    * generates an event of type PropertyChangeEvent. It too is sent to objects that previously registered an interest
    * in receiving such notifications. However, those other objects have the ability to veto the proposed change by
    * throwing a PropertyVetoException. This capability allows a Bean to operate differently according to its run-time
    * environment. A class that handles this event must implement the VetoableChangeListener interface.
    * */

    /*********************************** 0.5 Persistence **********************************/


   /*
   * Persistence is the ability to save the current state of a Bean, including the values of a Bean’s properties and
   * instance variables, to nonvolatile storage and to retrieve them at a later time. The object serialization
   * capabilities provided by the Java class libraries are used to provide persistence for Beans.
   *
   * The easiest way to serialize a Bean is to have it implement the java.io.Serializable interface, which is simply a
   * marker interface. Implementing java.io.Serializable makes serialization automatic. Your Bean need take no
   * other action. Automatic serialization can also be inherited. Therefore, if any superclass of a Bean implements
   * java.io.Serializable, then automatic serialization is obtained.
   *
   * When using automatic serialization, you can selectively prevent a field from being saved through the use of the
   * transient keyword. Thus, data members of a Bean specified as transient will not be serialized.
   *
   * If a Bean does not implement java.io.Serializable, you must provide serialization yourself, such as by
   * implementing java.io.Externalizable. Otherwise, containers cannot save the configuration of your component.
   * */

    /************************************** 0.6 Customizers *******************************************/


    /*
    * A Bean developer can provide a customizer that helps another developer configure the Bean. A customizer can
    * provide a step-by-step guide through the process that must be followed to use the component in a specific context.
    * Online documentation can also be provided. A Bean developer has great flexibility to develop a customizer that
    * can differentiate his or her product in the marketplace.
    *
    * */


    /*************************************** 0.7 The JavaBeans API ********************************************/

    /*
    * The JavaBeans functionality is provided by a set of classes and interfaces in the java.beans package. Beginning
    * with JDK 9, this package is in the java.desktop module.
    *
    * java.beans package has the following Interface:
    * - AppletInitializer: Methods in this interface are used to initiate beans that are also applet. (Deprecated bu
    *         jdk 9)
    * - BeanInfo: A bean implementor who wishes to provide explicit information about their bean may provide a
    *         BeanInfo class that implements this BeanInfo interface and provides explicit information about the
    *         methods, properties, events, etc, of their bean.
    * - Customizer: A customizer class provides a complete custom GUI for customizing a target Java Bean.
    * - DesignMode: Method in this interface determine if a Bean is executing in design mode.
    * - ExceptionListener: A method in this interface is invoked when an exception occurred.
    * - PropertyChangeListener: A method in this interface is invoked when a "bound" property is changed.
    * - PropertyEditor: Objects that implement this interface allow designers to change and display property values.
    *                 A PropertyEditor class provides support for GUIs that want to allow users to edit a property
    *                 value of a given type.
    * - VetoableChangeListener: A VetoableChange event gets fired whenever a bean changes a "constrained" property.
    * - Visibility: Methods in this interface allow a bean to run on servers where a GUI is not available.
    *
    * For the full class list, please check https://docs.oracle.com/javase/9/docs/api/java/beans/package-summary.html
    *
    * Here, we only examine four classes:
    * - Introspector
    * - PropertyDescriptor
    * - EventSetDescriptor
    * - MethodDescriptor
    * */

    /**  0.7.1 Introspector
     *
     * The Introspector class provides several static methods that support introspection. Of most interest is
     * getBeanInfo(). This method returns a BeanInfo object that can be used to obtain information about the Bean. The
     * getBeanInfo() method has several forms, including the one shown here:
     * - static BeanInfo getBeanInfo(Class<?> bean) throws IntrospectionException: It returns an object which
     *             contains information about the Bean specified by bean.
     * */

    /** 0.7.2 PropertyDescriptor
     *
     * The PropertyDescriptor class describes the characteristics of a Bean property. It supports several methods
     * that manage and describe properties. For example, you can determine if a property is bound by calling isBound().
     * To determine if a property is constrained, call isConstrained( ). You can obtain the name of a property by
     * calling getName().
     *
     * */

    /** 0.7.3 EventSetDescriptor
     *
     * The EventSetDescriptor class represents a set of Bean events. It supports several methods that obtain the
     * methods that a Bean uses to add or remove event listeners, and to otherwise manage events. For example,
     * to obtain the method used to add listeners, call getAddListenerMethod(). To obtain the method used
     * to remove listeners, call getRemoveListenerMethod(). To obtain the type of a listener, call getListenerType().
     * You can obtain the name of an event set by calling getName().
     * */

    /** 0.7.4 MethodDescriptor
     *
     * The MethodDescriptor class represents a Bean method. To obtain the name of the method, call getName(). You can
     * obtain information about the method by calling getMethod(), shown here:
     * - Method getMethod(): It returns an object of type Method that describes the method is returned.
     * */

    /*************************************** 0.8 A simple Java Bean example ****************************************/

    /*
    * In this example, we have three classes:
    * - MyColors: It is the bean class which displays a colored object within a frame.
    * - MyColorsBeanInfo: It is a subclass of SimpleBeanInfo that provides explicit information about Colors. In this
    *           example, we only override the getPropertyDescriptor(). We can also rewrite getEventDescriptor() and
    *           get getMethodDescriptor(). If they are not override, the SimpleBeanInfo will use the default patterns to
    *           display the information about property, event and method of beans.
    * - IntrospectorDemo: It uses introspection to display the properties and events that are available within the
    *           MyColors Bean.
    *
    * Notice two things in the output. First, because MyColorsBeanInfo overrides getPropertyDescriptors() such that
    * the only property returned is rectangular, only the rectangular property is displayed. However, because
    * getEventSetDescriptors() is not overridden by MyColorsBeanInfo, design-pattern introspection is used, and all
    * events are found, including those in MyColors’ superclass, Canvas. Remember, if you don’t override one of the
    * "get" methods defined by SimpleBeanInfo, then the default, design-pattern introspection is used. To observe
    * the difference that ColorsBeanInfo makes, erase its class file and then run IntrospectorDemo again. This time
    * it will report more properties.
    * */


}
