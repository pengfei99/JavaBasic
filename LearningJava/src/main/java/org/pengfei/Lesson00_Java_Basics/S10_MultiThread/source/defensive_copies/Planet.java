package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.defensive_copies;


import java.util.*;

/**
 * Planet is an immutable class, since there is no way to change its state after construction.
 *
 * A class that is declared final cannot be subclassed. This is particularly useful, for example, when creating an
 * immutable class like the String class.
 *
 */
public final class Planet {



    /**
     *  Planet has three fields:
     *  - mass: final primitive data is always immutable.
     *  - name: An immutable object field. (String objects never change state.)
     *  - dateOfDiscovery: A mutable object field. In this case, the state of this mutable field is to be changed only
     *            by this class. (In other cases, it makes perfect sense to allow the state of a field to be changed
     *            outside the native class; this is the case when a field acts as a "pointer" to an object created
     *            elsewhere.)
     */
    private final double mass;
    private final String name;

    /**
     * java.util.Date is used here only because its convenient for illustrating a point about mutable objects. In
     * new code, you should use java.time classes, not java.util.Date.
     */
    private final Date dateOfDiscovery;

    private final List<String> satellites;

    private final long serialNum;

    public Planet (double mass, String name, Date dateOfDiscovery, List<String> satellites) {
        this.mass = mass;
        this.name = name;
       /*
       * make a private copy of aDateOfDiscovery  this is the only way to keep the fDateOfDiscovery field private,
       * and shields this class from any changes that the caller may make to the original aDateOfDiscovery object
       * */
        this.dateOfDiscovery = new Date(dateOfDiscovery.getTime());

        // make a private copy, in case the caller modifies the input list
        this.satellites=new ArrayList<>(satellites);

        // Constructor calls a internal method, this method should be final to make the class immutable.
       serialNum=initSerialNum();
    }

    /**
     * Methods called from constructors should generally be declared final. If a constructor calls a non-final method, a
     * subclass may redefine that method with surprising or undesirable results.
     * */
    public final long initSerialNum(){
        return 10L;
    }

    /**
     * Returns a primitive value.
     *
     * The caller can do whatever they want with the return value, without affecting the internals of this class.
     * Why? Because this is a primitive value. The caller sees its "own" double that simply has the same value as fMass.
     */
    public double getMass() {
        return mass;
    }

    /**
     * Returns an immutable object.
     *
     * The caller gets a direct reference to the internal field. But this is not dangerous, since String is immutable
     * and cannot be changed.
     */
    public String getName() {
        return name;
    }

  /**
  * Returns a mutable object - likely bad style.
  *
  * The caller gets a direct reference to the internal field. This is usually dangerous,
  * since the Date object state can be changed both by this class and its caller.
  * That is, this class is no longer in complete control of dateOfDiscovery.
  */
//  public Date getDateOfDiscovery() {
//    return dateOfDiscovery;
//  }

    /**
     * Returns a mutable object - good style.
     *
     * Returns a defensive copy of the field. The caller of this method can do anything they want with the returned
     * Date object, without affecting the internals of this class in any way. Why? Because they do not have a reference
     * to fDate. Rather, they are playing with a second Date that initially has the same data as fDate.
     */
    public Date getDateOfDiscovery() {
        return new Date(dateOfDiscovery.getTime());
    }

    /**
     * Returns a defensive copy, which is a immutable list
     * */
    public List<String> getSatellites(){
        return Collections.unmodifiableList(satellites);
    }


}