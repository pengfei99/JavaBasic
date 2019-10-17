package org.pengfei.Lesson00_Java_Basics.S02_Program_Control;

import org.pengfei.Lesson00_Java_Basics.S02_Program_Control.source.*;

import java.io.IOException;

public class S02_Program_Control {
    /******************************************** 2. Introduction *******************************************/

    /*
    * In this section, we will learn :
    * - Input characters from the keyboard
    * - Know the complete form of the if statement
    * - Learn switch statement
    * - know the complete form of the for loop
    * - Use the while loop
    * - Use the do-while loop
    * - Use break to exit loop, Use break as a form of goto
    * - Apply continue
    * - Nest loops
    *
    * We can classify the above statement into three categories:
    * - selection statement: if, switch
    * - iteration statement: for, while, do-while
    * - jump statement: break, continue, return(we will discuss in another section)
    * */

    /******************************************** 2.1. Keyboard input *******************************************/

    /* To read a character from the keyboard, we will use System.in.read(). The read() method waits until the user
    * presses a key and then returns the result. The character is returned as an integer, so it must be cast into
    * a char.
    *
    * By default, console input is line buffered. Here, the term buffer refers to a small portion of memory that
    * is used to hold the characters before they are read by your program. In this case, the buffer holds a complete
    * line of text. As a result, you must press ENTER before any character that you type will be sent to your program.
    * See the code example in KeyboardInputExp.getKeyboardInput() and KeyboardInputExp.getKeyboardInputWithScanner();
    *
    * */

    /******************************************** 2.2. If statement *******************************************/

    /*
    * The complete form of the if statement is :
    * if(con) statement1
    * else statement2
    * You can also add {} around statements. If the condition is true, statement1 will be executed, otherwise
    * statement2 will be executed.
    *
    * */

    /** 2.2.1 Nested ifs
     * A nested if is an if statement that is the target of another if or else. The main thing to remember about
     * nested ifs in Java is that an else statement always refers to the nearest if statement that is within the
     * same block as the else and not already associated with an else.
     * */

    /** 2.2.2 If-else-if ladder
     * It looks like this:
     * if(condition) statement:
     * else if(condition) statement;
     * else if(condition) statement;
     * ...
     *
     * The conditional expressions are evaluated from the top downward. As soon as a true condition is found, the
     * statement associated with it is executed, and the rest of the ladder is bypassed. If none of the conditions
     * are true, the final else statement will be executed. The final else often acts as a default condition; that is,
     * if all other conditional tests fail, the last else statement is performed. If there is no final else and all
     * other conditions are false, no action will take place.
     * */

    /**************************************************3. The Switch statement ***********************************/

    /* The switch provides for a multiway branch, which is similar to the ifelse ladder. But switch is a more efficient
    * approach. It works like this: the value of an expression is successively tested against a list of constants.
    * When a match is found, the statement sequence associated with that match is executed. The general switch form is:
    * switch(expression){
    * case constant0: statement0 break;
    * case constant1: statement1 break;
    * ...
    * default: statement break;
    * }
    *
    * Before jdk 7, the expresion in switch must resolve to type byte, short,int, char, or an enum. Beginning with JDK 7,
    * expresion can be of type String.
    * constant expression(e.g. literal value) must be unique, Duplicate values are not allowed. The data type of all the
    * constant must be compatible with the type of switch expression.
    *
    * The default statement(optional) is executed if no case matches the expression. If default statement is absent and
    * no match found, no actions will be done. The statement in each case can be a sequence of actions, they will be
    * executed until the break is encountered, in the case of default or the last case, until the end of switch is
    * reached
    *
    * Technically, the break statement is optional, although most applications of the switch will use it. When
    * encountered within the statement sequence of a case, the break statement causes program flow to exit from
    * the entire switch statement and resume at the next statement outside the switch. However, if a break statement
    * does not end the statement sequence associated with a case, then all the statements at and following the matching
    * case will be executed until a break (or the end of the switch) is encountered.
    * */

    /** 3.1 Nested Switch
     *
     * It is possible to have a switch as part of the statement sequence of an outer switch. This is called a nested
     * switch. Even if the case constants of the inner and outer switch contain common values, no conflicts will arise.
     * Check the SwitchExp.exp2(), no conflicts will arise.
     * */

    /** 3.2 Switch vs ifelse ladder
     *
     * In general, use an if­else­if ladder when the conditions controlling the selection process do not rely upon a
     * single value. Otherwise, use switch is more efficient.
     * */
    /**************************************************3. The for loop ***********************************/
    /*
    * The most traditional forms of the for is:
    * for(initialization; condition; iteration) {
    * statement
    * }
    *
    * The initialization is usually an assignment statement that sets the initial value of the loop control variable,
    * The condition is a boolean expression that determines whether or not the loop will repeat or not.
    * The iteration expression defines the amount by which the loop control variable will change each time.
    * Check ForStatementExp.exp1() for a for statement example.
    *
    * We can increment and decrement in a for loop, check ForStatementExp.exp2();
    * */

    /** 3.1 Multi variable for loop
     * We can have multiple loop control variables in the for statement, check ForStatementExp.exp3()
     * */

    /** 3.2 The for loop Stop condition expression can be any valid boolean expression. It does not need to involve
     * the control variable. Check ForStatementExp.exp4(), it will continue to print i value until user enter "S";
     * */

    /** 3.3 Missing Pieces
     * In Java, it is possible for any or all of the initialization, condition, or iteration portions of the for loop
     * to be blank. Check ForStatementExp.exp5(), we increment the control var inside the for loop.
     * */

    /** 3.4 The Infinite loop
     * You can create an infinite loop (a loop that never terminates) using the for by leaving the conditional
     * expression empty. For example, the following fragment shows the way many Java programmers create an
     * infinite loop:
     * for(;;){
     *     statement
     * }
     * */

    /** 3.5 Loops with no body
     * In Java, the body associated with a for loop (or any other loop) can be empty. This is because a null statement
     * is syntactically valid. Body­less loops are often useful. Check ForStatementExp.exp6(); which uses one to sum
     * the numbers 1 through 5:
     * */

    /** 3.6 We can also declare the loop control variable inside the for loop. For example, for(int i=0;...)*/

    /**************************************************4. The while loop ***********************************/
    /* The general form of the while loop is: while (condition) {statement}. The condition can be any valid boolean
    * expression, The loop repeats while the condition is true. When the condition becomes false, program control
    * passes to the line immediately following the loop.
    *
    * Check WhileStatementExp.exp1() which loop over the alphabetic;
    *
    * Like the for loop, the while loop checks the condition at the top of the loop, which means that the loop code
    * may not execute at all. This eliminates the need for performing a separate test before the loop.
    * Check WhileStatementExp.exp2(), you can noticed when power(first iteration of for loop)=0, the while loop is
    * skipped
    * */

    /**************************************************5. The do-while loop ***********************************/
    /*
    * The general form of the do-while loop is :
    * do{
    *  statements;
    * } while(condition);
    *
    * You can noticed, unlike for and while, the condition is tested at the end of the loop. This means that a do-while
    * loop will always execute at least once. The do-while loop executes as long as the conditional expression is true.
    *
    * */

    /** 5.1 For vs While vs do-while
     *
     * Use a for loop when performing a know number of iterations based on the value of a loop control variable.
     * Use a while loop when you need to repeat some statements until some condition becomes false.
     * Use a do-while loop when you need a while loop that will always perform at least on iteration.*/


    /************************************************ 6. Use break to exit a loop ***********************************/
    /*
    * It is possible to force an immediate exit from a loop, bypassing any remaining code in the body of the loop
    * and the loop's conditional test, by using the break statement. When a break statement is encountered inside a
    * loop, the loop is terminated and program control resumes at the next statement following the loop.
    * Check BreakStatementExp.exp1() for a simple break example.
    *
    * Break is often used to stop infinite loops. Check BreakStatementExp.exp2()
    *
    * When break used inside a set of nested loops, the break statement will break out of only the innermost loop.
    * Check BreakStatementExp.exp3();
    * */

    /********************************************* 7. Use break as a form of goto ********************************/

    /* Java does not have a goto statement, because it provides an unstructured way to alter the flow of program
    * execution. Programs that make extensive use of the goto are usually hard to understand and hard to maintain.
    * There are, however, a few places where the goto is a useful and legitimate device. For example, the goto can
    * be helpful when exiting from a deeply nested set of loops. To handle such situations, Java defines an "expanded
    * form of the break statement (labeled break)". The general form of the labeled break statement is:
    * break label;
    *
    * Typically, label is the name of a label that identifies a block of code. When this form of break executes, control
    * is transferred out of the named block of code. The labeled block of code must enclose the break statement.
    * Check BreakStatementExp.exp4() and BreakStatementExp.exp5()
    *
    *
    * */

    /*************************************** 8. Use Continue ******************************************/
    /* It's possible to force an early iteration of a loop, bypassing the loop's normal control structure. This is
    * accomplished using continue. The continue statement forces the next iteration of the loop to take place,
    * skipping any code between itself and the condition expression that controls the loop. Thus, continue is
    * essentially the complement of break. Check ContinueStatementExp.exp1();
    *
    * In while and do-while loops, a continue statement will cause control to go directly to the conditional expression
    * and then continue the looping process. In the case of the for, the iteration expression of the loop is evaluated,
    * then the conditional expression is executed, and then the loop continues.
    *
    * As with the break statement, continue may specify a label to describe which enclosing loop to continue.
    * Check ContinueStatementExp.exp2()
    *
    * Good uses of continue are rare. One reason is that Java provides a rich set of loop statements that fit most
    * applications. However, for those special circumstances in which early iteration is needed, the continue
    * statement provides a structured way to accomplish it.
    * */
    public static void main(String args[]) throws IOException {

        // keyboard input
       /* try {
            KeyboardInputExp.getKeyboardInput();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

       // KeyboardInputExp.getKeyboardInputWithScanner();

        // Nested ifs
       /* try {
            IfStatementExp.nestedIfExp();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

       // ifelse ladder
        // IfStatementExp.ifElseLadder();

        // Switch
        // SwitchExp.exp1();

        // nested switch
        // SwitchExp.exp2();

        // for statement
        // ForStatementExp.exp1();
        // ForStatementExp.exp2();
        // ForStatementExp.exp3();
        // ForStatementExp.exp4();
        // ForStatementExp.exp5();
        // ForStatementExp.exp6();

        // while statement
        //WhileStatementExp.exp1();
        //WhileStatementExp.exp2();

        // do-while statement
        // DoWhileStatementExp.exp1();

        // break statement
        // BreakStatementExp.exp1();
        // BreakStatementExp.exp2();
        // BreakStatementExp.exp3();
        // BreakStatementExp.exp4();
       // BreakStatementExp.exp5();

        // continue statement
        // ContinueStatementExp.exp1();
        ContinueStatementExp.exp2();
    }
}
