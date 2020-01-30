package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util;

import org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.FormatterExp;
import org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.ResourceBundleExp;
import org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.ScannerExp;
import org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.UtilityClassesExp;

import java.util.Formatter;

public class S03_Exploring_Java_util_utility_classes {

    /************************************ 3.2 More Utility classes in java.util *************************************/

    /*
     * java.util contains not only the Collection framework, but also many other utility classes. In this section
     * we will see these classes in details.
     *
     * */

    /************************************ 3.2.1 StringTokenizer *************************************/

    /*
     * The processing of text often consists of parsing a formatted input string. Parsing is the division of
     * text into a set of discrete parts, aka. tokens, which in a certain sequence can convey a semantic meaning.
     *
     * The StringTokenizer class provides the first step in this parsing process, often called lexer (lexical analyzer)
     * or scanner. StringTokenizer implements the Enumeration interface. As Enumeration, StringTokenizer exists
     * only in legacy code. For new code, use regular expressions.
     *
     * StringTokenizer has three constructors:
     * - StringTokenizer(String str): str is the string that will be tokenized. Default delimiters are used.
     * - StringTokenizer(String str, String delimiters): str is the string that will be tokenized. delimiters is
     *              a string that specifies the delimiters. You can have multiple delimiters. For example ",;." specifies
     *              three delimiters.
     * - StringTokenizer(String str, String delimiters, boolean delimAsToken): If delimAsToken is true, then the
     *              delimiters are also returned as tokens.
     *
     * Check  UtilityClassesExp.exp1();
     * */

    /************************************ 3.2.2 BitSet *************************************/

    /*
     * A BitSet class creates a special type of array that holds bit values in the form of boolean values. The size of
     * BitSet can be increased as needed. This makes it similar to a vector of bits. All bits are initialized to false.
     *
     * Check UtilityClassesExp.exp2();
     * */

    /************************************ 3.2.3 Optional  *************************************/

    /*
     * Beginning with JDK 8, the classes called Optional, OptionalDouble, OptionalInt and OptionalLong offer a way
     * to handle situations in which a value may or may not be present. This can avoid null pointer exceptions if
     * an attempt is made to dereference a null reference.
     *
     * Note these classes are value-based, immutable and has various restrictions:
     * - Not using instances for synchronization
     * - Avoiding any use of reference equality. (Check value-based classes in java doc)
     *
     * Check  UtilityClassesExp.exp3(); for code Example. The OptionalDouble/Int/Long classes works like Optional.
     * They are designed for double, int and long primitive types
     * */

    /************************************ 3.2.4 Date *************************************/

    /*
     * The Date class encapsulates the current date and time. We also need to mention the Calendar and DateFormat
     * classes. Many functions in Date has been moved to the two classes in Java 1.1. It has two constructors:
     * - Date(): It creates an object of type Date with the current date and time.
     * - Date(long millisec): It accepts one argument that equals the number of milliseconds that have elapsed since
     *                  midnight, January 1, 1970.
     *
     * Date implements the Comparable interface, so it's easy to compare and sort date object.
     * check UtilityClassesExp.exp4();
     * */

    /************************************ 3.2.5 Calendar *************************************/

    /*
     * The abstract Calendar class provides a set of methods that allows you to convert a time in milliseconds to
     * a number of useful components. For example, you can get year, month, week, day, hour, minute, and second.
     * The subclass of Calendar will provide specific functionality to interpret time information according
     * to their own rules. GregorianCalendar is an example of Calendar subclass.
     * */

    /************************************ 3.2.6 GregorianCalendar *************************************/

    /*
     * GregorianCalendar is a concrete implementation of a Calendar that implements the normal Gregorian calendar.
     * The getInstance() method will return the current date and time. It add two fields: AD and BC. It provides
     * six constructors:
     * - GregorianCalendar(int year, int month, int dayOfMonth):
     * - GregorianCalendar(int year, int month, int dayOfMonth, int hours, int minutes):
     * - GregorianCalendar(int year, int month, int dayOfMonth, int hours, int minutes, int seconds):
     * - GregorianCalendar(Locale locale):
     * - GregorianCalendar(TimeZone timeZone):
     * - GregorianCalendar(TimeZone timeZone, Locale locale):
     * All the first three versions set the day, month, and year. Here, year specifies the year. The month is specified
     * by month,	with zero indicating January. The day of the month is specified by dayOfMonth.
     *
     * The last three version allows you to set local and time zone.
     *
     * GregorianCalendar implements all abstract method of Calendar, it adds some additional methods. For example,
     * boolean isLeapYear(int year) checks if a year is a leap year(Feb has 29 days, not 28).
     *
     *
     * */

    /************************************ 3.2.7 TimeZone *************************************/

    /*
     * The abstract TimeZone class allows you to work with time zone offsets from Greenwich mean time(GMT),
     * also referred to as Coordinated Universal Time(UTC). It also computes daylight saving time.
     *
     * The SimpleTimeZone class is a convenient subclass of TimeZone. It implements TimeZone's abstract methods and
     * allows you to work with time zones for a Gregorian calendar. It has four constructors:
     * - SimpleTimeZone(int timeDelta, String tzName): timeDelta is the offset of your local time zone relative to GMT.
     *                  tzName is the name of your time zone.
     * Other three constructors allows you to specify daylight saving time.
     *
     * Check UtilityClassesExp.exp7();
     *
     * */

    /************************************ 3.2.8 Locale *************************************/

    /*
     * The Local class is instantiated to produce objects that describe a geographical or cultural region.
     * It is one of several classes that provide you with the ability to write programs that can execute in
     * different international environments.
     *
     * The Locale class defines constants that are common locales. For example, the expression Locale.US represents
     * the Local object for USA. You can build your own locals by using the following constructors:
     * - Locale(String language)
     * - Locale(String language,String country)
     * - Locale(String language,String country, String variant)
     * You can specify language, country name, and variant(e.g. different dialect).
     *
     * Important methods:
     * - static void setDefault(Locale localeObj): This sets the default locale used by the JVM to that specified by
     *               localObj.
     * - final String getDisplayCountry(): returns the country name
     * - final String getDisplayLanguage(): returns the language
     *
     * JDK 9 added a method called getISOCountries(), which returns a collection of country codes for a given
     * Locale.IsoCountryCode enumeration value.
     *
     * Calendar	and	GregorianCalendar are examples of classes that operate in a locale-sensitive manner. DateFormat	and
     * SimpleDateFormat also depend on the locale.
     * */

    /************************************ 3.2.9 Random *************************************/

    /*
     * The Random class is a generator of pseudorandom numbers(because they are simply uniformly distributed sequences).
     * It has following constructors:
     * - Random(): It creates a random number generator that uses a reasonably unique seed.
     * - Random(long seed): you can specify a seed value manually.
     *
     * If you specify a seed, you define the starting point for the random sequence. If you use the same seed to
     * initialize another Random object, you will extract the same random sequence.
     *
     * Important methods:
     * - boolean nextBoolean(): Returns next boolean random number, has similar method for int, double, float, long, byte
     * - double nextGaussian(): Returns the next Gaussian random number.
     * - void setSeed(long newSeed): set the seed value of random to newSeed.
     *
     * Check UtilityClassesExp.exp8();
     *
     * JDK 8 added three methods to support the stream API:
     * - DoubleStream doubles():
     * - IntStream ints():
     * - LongStream longs():
     * */

    /************************************ 3.2.10 Timer and TimerTask *************************************/

    /*
     * Timer and TimerTask classes provide the function to schedule a task for execution at some future time. Using
     * these classes, you can create a thread that runs in the background, waiting for a specific time. When the time
     * arrives, the task linked to that thread is executed. They also have options which allow you to:
     * - schedule a task to run on a specific date.
     * - schedule a task for repeated execution.
     *
     * Timer is the class that you will use to schedule a task for execution. The task being scheduled must be an
     * instance of TimerTask. TimerTask implements the Runnable interface, and has the following methods:
     * - boolean cancel(): Terminates the task. Returns true if an execution of the task is prevented. Otherwise,
     *                     returns false.
     * - abstract void run(): Contains the code for the timer task
     * - long scheduledExecutionTime(): Returns the time at which the last execution of the task was scheduled to
     *                        have occurred.
     *
     * Once a TimerTask is created, we can use Timer to schedule it. Timer has four constructors:
     * - Timer(): It creates a Timer object that runs as a normal thread.
     * - Timer(boolean isDaemon): if true, creates a new timer whose associated thread may be specified to run as a daemon.
     *                   A daemon thread is called for if the timer will be used to schedule repeating
     *                   "maintenance activities", which must be performed as long as the application is running,
     *                    but should not prolong the lifetime of the application.
     * - Timer(String name): You can specify a name for the Timer object
     * - Timer(String name, boolean isDaemon):
     *
     * Timer has the following methods:
     * - void cancel(): Terminates this timer, discarding any currently scheduled tasks.\
     * - int	purge(): Removes all cancelled tasks from this timer's task queue.
     * - void schedule(TimerTask task, Date time): Schedules the specified task for execution at the specified time.
     * - void schedule(TimerTask task, Date firstTime, long period): Schedules the specified task for repeated
     *               fixed-delay execution, beginning at the specified time.
     * - void schedule(TimerTask task, long delay): Schedules the specified task for execution after the specified delay.
     * - void schedule(TimerTask task, long delay, long period): Schedules the specified task for repeated fixed-delay
     *               execution, beginning after the specified delay.
     * - void scheduleAtFixedRate(TimerTask task, Date firstTime, long period): Schedules the specified task for
     *               repeated fixed-rate execution, beginning at the specified time.
     * - void scheduleAtFixedRate(TimerTask task, long delay, long period): Schedules the specified task for repeated
     *               fixed-rate execution, beginning after the specified delay.
     *
     * Note, if you create a non-daemon task, then you will want to call cancel() to end the task when your programs
     * ends. If you don't do this, then your program may "hang" for a period of time.
     * */

    /************************************ 3.2.11 Currency *************************************/

    /*
     * The Currency class encapsulates information about a currency. It defines no constructors.
     * Check  UtilityClassesExp.exp10();
     * */

    /************************************ 3.2.12 Formatter *************************************/

    /*
     * At the core of Java’s support for creating formatted output is the Formatter class. It provides format
     * conversions that let you display numbers, strings, and time and date in virtually any format you like.
     * */

    /** 3.2.12.1 The Formatter constructors and methods
     *
     * In general, Formatter works by converting the binary form of data used by a program into formatted text. It
     * stores the formatted text in a buffer, the contents of which can be obtained by your program whenever they
     * are needed. Formatter defines many constructors, we just show few of them here:
     * - Formatter():
     * - Formatter(Appendable buf): buf specifies a buffer for the formatted output. If buf is null, then
     *             Formatter automatically allocates a StringBuilder to hold the formatted output.
     * - Formatter(Appendable buf, Locale loc): loc specifies a locale which may be used to set date format, etc. If
     *                    no loc is given, the default locale will be used
     * - Formatter(String filename): Filename specifies the name of a file that will receive the formatted output.
     * - Formatter(String filename, String charset): The charset specifies the character set(e.g. UTF-8, ASCII)
     * - Formatter(File file): Constructs a new formatter with the specified file.
     *
     * Important methods:
     * - Formatter format(Locale l, String format, Object... args): Writes a formatted string to this object's
     *           destination using the specified locale, format string, and arguments.
     * - Formatter format(String format, Object... args): Writes a formatted string to this object's destination
     *            using the specified format string and arguments.
     *
     * Find full constructors and methods list https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html
     * */

/** 3.2.12.2 Formatting Basic examples
 *
 * A simplest formatter is to use a string as format, followed by a list of arguments. These arguments will replace
 * the format conversion specifier in the format. Check  FormatterExp.exp1(); we use "Formatting %s is easy %d %f"
 * as format.
 *
 * Note all format conversion specifier is started by % and followed by one single character. Here is the full list:
 * - %%	: Inserts a % sign
 * - %x %X : Integer hexadecimal
 * - %t %T : Dime and Date
 * - %s %S : String
 * - %n : Inserts a newline character
 * - %o : Octal integer
 * - %f : Decimal floating-point
 * - %e %E : Scientific notation
 * - %g : Causes Formatter to use either %f or %e, whichever is shorter
 * - %h %H : Hash code of the argument
 * - %d : Decimal integer
 * - %c : Character
 * - %b %B : Boolean
 * - %a %A : Floating-point hexadecimal
 *
 * The %n and %% format specifiers differ from the others, because they do not match an argument. They are
 * simply escape sequences that insert a character into the output sequence. Check FormatterExp.exp3();
 * */

/** 3.2.12.3 Formatting Time and Date
 * One of the more powerful conversion specifiers is %t. It lets you format time and date information. The %t
 * specifier works a bit differently than the others because it requires the use of a suffix to describe the
 * portion and precise format of the time or date desired. The list of the format specifier is in table 20-13(P995).
 *
 * Check FormatterExp.exp2();
 * */

/** 3.2.12.4 Specifying a Minimum Field Width
 *
 * An integer placed between the % sing and the format conversion code acts as a minimum field-width specifier. This
 * pads the output with spaces to ensure that it reaches a certain minimum length. If the string or number is longer
 * than that minimum, it will be printed in full. The default padding is space. If you want to pad with 0, place a 0
 * before the field-width specifier. For example, %05d will pad a number of less than five digits with 0's to that
 * its total length is five.
 * Check FormatterExp.exp4();
 *
 * The minimum field-width is often used to produces tables in which the columns line up.
 * Check  FormatterExp.exp5();
 * */

/** 3.2.12.5 Specifying Precision
 *
 * A precision specifier can be applied to the %f, %e, %g and %s format specifiers, It follows the minimum field
 * width specifier(if exists) and consists of a period followed by an integer.
 *
 * When you apply the precision specifier to floating-point data using the %f or %e specifiers, it determines the
 * number of decimal places displayed. For example, %10.4f displays a number at least 10 characters wide with four
 * decimal places.
 *
 * When using %g the precision determines the number of significant digits. The default precision is 6.
 *
 * When using %s the precision determines the maximum field length. For example, %5.7s displays a string of
 * at least 5 and not exceeding 7 characters long. If the string is longer than the maximum field width, the end
 * characters will be truncated.
 *
 * Check FormatterExp.exp6();
 * */

/** 3.2.12.6 Justifying output with format flags
 *
 * Formatter recognizes a set of format flags. All format flags are single characters, they are shown here:
 * - - : left justification
 * - # : Alternate conversion format
 * - 0 : output is padded with zero rather than spaces
 * - space: Positive numeric output is preceded by a space
 * - + : Positive numeric output is preceded by a + sign
 * - , : Numeric values include grouping separators
 * ( : Negative numeric values are enclosed within parentheses
 *
 * By default, all output is right-justified. If the field width is larger than the data printed, the data will be
 * placed on the right edge of the field. You can force the output to be left-justified. For example,
 * %-10.2f means left-justifies a float with two decimal places in a 10 character width field. Check FormatterExp.exp7();
 *
 * When creating columns of numbers, it is sometimes useful to output a space before positive values so that positive
 * and negative line up. Check  FormatterExp.exp8(); we use space flag, so positive value starts with a space
 *
 * We can also enclose negative inside parentheses. Check FormatterExp.exp9();
 *
 * When display large numbers, it is often useful to add grouping separators(commas in English), Check
 * FormatterExp.exp10();
 *
 * The # can be applied to %o, %x, %e, and %f. For %e, and %f, the # ensures that there will be a decimal point even
 * if there are no decimal digits. If you precede the %x format specifier with a #, the hexadecimal number will be
 * printed with a 0x prefix. Preceding the %o specifier with # causes the number to be printed with a leading zero.
 * */

    /** 3.2.12.7 Using an Argument Index
     *
     * Formatter includes a very useful feature that lets you specify the argument to which a format specifier applies.
     * Normally, they are matched in order, from left to right. With argument index, you can explicitly control
     * which argument a format specifiers matches.
     *
     * An argument index immediately follows the %, has n$ form, here n is the index of argument. This can also help
     * us to print an argument multiple times without having to specify it twice.
     *
     * Use the previous format specifier argument index with <.
     *
     * Check FormatterExp.exp11();
     */

    /** 3.2.12.8 Closing a Formatter
     *
     * In general, you should close a Formatter when you are done using it. Dosing so frees any resources that it was
     * using. This is especially important when formatting to a file. You can call close() method to close a Formatter
     * explicitly. However, Formatter also implements the AutoCloseable interface. This means that it supports the try
     * with resource statement. Using this, the Formatter is automatically closed when it is no longer needed.
     *
     * Check FormatterExp.exp12();
     */

    /** 3.2.12.8 The Java printf() connection
     * It's convenient to use printf() to print formatted output on the console. The printf() method automatically uses
     * Formatter to create a formatted string. It then displays that string on System.out(console by default).
     * The printf() method is defined by both PrintStream and PrintWriter.
     *
     * Check FormatterExp.exp13();
     */

    /************************************ 3.2.13 Scanner *************************************/

    /*
    * Scanner is the complement of Formatter. It reads formatted input and converts it into its binary form. Scanner
    * can be used to read input from the console, a file, a string, or many other source that implements the Readable
    * interface or ReadableByteChannel. */

    /** 3.2.13.1 The Scanner Constructors
     *
     * Scanner defines many constructors. In general, a Scanner can be created for a String, an InputStream, a File, a
     * Path, or any object that implements the Readable or ReadableByteChannel.
     * - Scanner(File source): Constructs a new Scanner that produces values scanned from the specified file.
     * - Scanner(File source, String charsetName): Constructs a new Scanner that produces values scanned from the
     *               specified file.
     * - Scanner(InputStream source): Constructs a new Scanner that produces values scanned from the specified
     *               input stream.
     * - Scanner(InputStream source, String charsetName): Constructs a new Scanner that produces values scanned from
     *                      the specified input stream and charset(e.g. UTF8).
     * - Scanner(Path source): Constructs a new Scanner that produces values scanned from the specified file.
     * - Scanner(Path source, String charsetName): Constructs a new Scanner that produces values scanned from the
     *                 specified file.
     * - Scanner(Readable source): Constructs a new Scanner that produces values scanned from the specified source.
     * - Scanner(ReadableByteChannel source): Constructs a new Scanner that produces values scanned from the
     *                 specified channel.
     * - Scanner(ReadableByteChannel source, String charsetName): Constructs a new Scanner that produces values
     *                 scanned from the specified channel.
     * - Scanner(String source): Constructs a new Scanner that produces values scanned from the specified string.
     *
     * Check ScannerExp.exp1("/tmp/phonebook.db"); we create a scanner with a fileReader. This works because
     * FileReader implements Readable interface.
     *
     * Check  ScannerExp.exp2(); we create a scanner with System.in. This works because in is an object of type
     * InputStream.
     * */

    /** 3.2.13.2 Scanning Basics and important methods
     *
     * In general, to use Scanner, you need to follow this procedure:
     * 1. Determine if a specific type of input is available by calling one of Scanner's hasNext methods, which has
     *    many overload version for all types.
     * 2. If input is available, read it by calling one of Scanner's next methods to get the value.
     * 3. Repeat the 1,2 until input is exhausted
     * 4. Close the Scanner by calling close().
     *
     * Important methods:
     * - hasNext(Pattern pattern): Returns true if a token that matches the pattern is available to be read. It has many
     *            overload version for boolean, byte, float, int, etc.
     * - hasNextLine(): Returns true if an line of input is available
     * - String next(Pattern pattern): Returns the next token that matches the pattern. It has many overload version
     *            for boolean, byte, float, int, etc.
     * - nextLine(): Returns the next line of input as a string.
     *
     * Check ScannerExp.exp3(); we use hasNextDouble() method to check if input value is digit or not.
     * Check ScannerExp.exp4("/tmp/test"); It does the same thing, but read input from a file. An important point, we
     *         dont close the fileReader, after we close the Scanner. Because, when you close a Scanner, the Readable
     *         associated with it is also closed(If that Readable implements the Closeable interface).
     *
     * Scanner also implements the AutoCloseable interface, so you can use try with resource to close it automatically
     * when Scanner is no longer used. Check ScannerExp.exp5();
     *
     * You can use Scanner to read input that contains several different types of data, even if the order of that data
     * is unknown in advance. You must simply check what type of data is available before reading it. Check ScannerExp.exp6();
     * */

    /** 3.2.13.3 Setting Delimiters
     *
     * Scanner defines where a token starts and ends based on a set of delimiters. The default delimiters are the space,
     * and this is the delimiter that our preceding example have used. It is possible to change the delimiters by
     * calling useDelimiter() method. It has two overload version:
     * - Scanner useDelimiter(String pattern):
     * - Scanner useDelimiter(Pattern pattern):
     * Here pattern is a regular expression that specifies the delimiter set.
     * Check ScannerExp.exp7("/tmp/test"); we use a regular expression to define a new delimiter for scanner.
     */

    /** 3.2.13.4 Other Scanner Features
     *
     * Scanner can find a token in the next line of text which matches a specified pattern. It has two overload version:
     * - String findInLine(String pattern):
     * - String findInLine(Pattern pattern):
     * Here pattern is a regular expression. This method is useful if you want to locate a specific pattern.
     * Check ScannerExp.exp8;
     *
     * - String findWithinHorizon(String pattern, int count):
     * - String findWithinHorizon(Pattern pattern, int count):
     * This method attempts to find an occurrence of the specified pattern within the next count characters. If
     * successful, it returns the matching pattern. Otherwise, it returns null. If count is zero, then all input
     * is searched until either a match is found or the end of input is encountered.
     *
     * You can also bypass a pattern using skip()
     * - Scanner skip(Pattern pattern):
     * - Scanner skip(String pattern):
     * If pattern is matched, skip() simply advances beyond it and returns a reference to the invoking object.
     *
     * JDK9 added the methods:
     * - tokens(): it returns all tokens in the form of a Stream<String>.
     * - findAll(Pattern pattern): it returns tokens that match the specified pattern in the form of a Stream<MatchResult>
     */

    /************************ 3.2.14 ResourceBundle, ListResourceBundle, PropertyResourceBundle **********************/

    /*
    * The java.util package includes three classes that aid in the internationalization of your program.
    *
    * ResourceBundle is the abstract class. It defines methods that enable you to manage a collection of
    * locale-sensitive resources, such as the strings that are used to label the user interface elements in your program.
    * You can define two or more sets of translated strings that support various languages, such as English, or Chinese,
    * with each translation set residing in its own bundle.
    *
    * The ResourceBundle class has two subclasses called PropertyResourceBundle and ListResourceBundle.
    *
    * ListResourceBundle is an abstract class, which manages resources in an array of key/value pairs.
    * ListResourceBundle adds the method getContents(), which all subclasses must implement.
    * Check ResourceBundleExp.exp1(); for ListResourceBundle example
    *
    * PropertyResourceBundle, which manages resources by using property files. Check ResourceBundleExp.exp2();
    * */

    /************************ 3.2.15 Other classes and interfaces in java.util **********************/

    /*
    * It has two extra interface:
    * - EventListener: Indicates that a class is an event listener.
    * - Formattable: Enables a class to provide custom formatting.
    *
    * It has following extra classes:
    * - Base64: Supports base64 encoding, Encoder and Decoder are nested classes inside it.
    * - DoubleSummaryStatistics: Supports the compilation of double values. The following statistics are available:
    *                    average, minimum, maximum, count and sum. IntSummaryStatistics for Integer
    * - EventListenerProxy: Extends the EventListener class to allow additional parameters.
    * - EventObject: The superclass for all event classes.
    * - FormattableFlags: Defines formatting flags that are used with the Formattable interface.
    * - Objects: Various methods that operate on objects.
    * - PropertyPermission: Manages property permissions.
    * - ServiceLoader: Provides a means of finding service providers.
    * - Stringloiner: Supports the concatenation of CharSequences, which may include a separator, a prefix and a suffix.
    * - UUID: Encapsulates and manages Universally Unique Identifiers.
    * */

    /***********************************************************************************************************
     * ******************************************* Code example ***********************************************
     * **********************************************************************************************************/
    public static void main(String[] args) {

        //StringTokenizer
        //  UtilityClassesExp.exp1();

        //bitset
        //  UtilityClassesExp.exp2();

        //Optional
        //  UtilityClassesExp.exp3();

        //Date
        // UtilityClassesExp.exp4();

        //Calendar
        // UtilityClassesExp.exp5();
        //UtilityClassesExp.exp6();

        //TimeZone
        // UtilityClassesExp.exp7();

        //random
        //   UtilityClassesExp.exp8();

        // Timer and TimerTask
        //  UtilityClassesExp.exp9();

        // Currency
        // UtilityClassesExp.exp10();

        /** formatter*/
       // FormatterExp.exp1();

        // Time date
       // FormatterExp.exp2();

      //  FormatterExp.exp3();

        // minimum field width
       // FormatterExp.exp4();
        // FormatterExp.exp5();

        // precision
       // FormatterExp.exp6();

        // left justified
        // FormatterExp.exp7();

        //positive value starts with space
        // FormatterExp.exp8();

        //negative value in parentheses
        // FormatterExp.exp9();

        //comma separator for large number
       // FormatterExp.exp10();

        // Using an Argument Index
        //FormatterExp.exp11();

        //auto closing with try with resource
        // FormatterExp.exp12();

        // printf
       // FormatterExp.exp13();

        /*************End of formatter **********/

        /** Scanner*/
        //first example read a file
       // ScannerExp.exp1("/tmp/phonebook.db");
       // ScannerExp.exp2();

        // use hasNext to check input value type
       // ScannerExp.exp3();
       // ScannerExp.exp4("/tmp/test");

        // close Scanner with try with resource
        // ScannerExp.exp5();

        // Check input type with hasNext
       // ScannerExp.exp6();

        // customise delimiter
       // ScannerExp.exp7("/tmp/test");

        // find pattern
      //  ScannerExp.exp8();
        /****************** End of Scanner **************/

        //ResourceBundle
       // ResourceBundleExp.exp1();
        ResourceBundleExp.exp2();

    }
}
