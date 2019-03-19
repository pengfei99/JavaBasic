package org.pengfei.Lesson13_Common_Data_Structure.L13_S3_Stacks;

public class StackApplicationExample {

    /*This method test if a String expression with (){}[] has delimiters matched or not*/
    public static boolean isMatched(String expression){
        final String opening="({[";
        final String closing=")}]";

        Stack<Character> buffer=new SinglyLinkedListBasedStack<Character>();

        for (char c:expression.toCharArray()){
            //if c is an opening symbol, put it in the stack buffer
            if(opening.indexOf(c)!=-1){
                buffer.push(c);
            }
            // if c is an closing symbol, and buffer is empty return false
            else if(closing.indexOf(c)!=-1){
                if(buffer.isEmpty()) return false;

                // if the buffer is not empty and closing does not match the opening in the buffer, return false
                if(closing.indexOf(c)!=opening.indexOf(buffer.pop())) return false;

            }

        }
    // when all characher is processed, and the buffer is empty, it means all matches
        return buffer.isEmpty();
    }

    /* This method test if a html opening tag has a matching closing tag
    * */

    public static boolean isHTMLMatched(String html){
        SinglyLinkedListBasedStack<String> buffer=new SinglyLinkedListBasedStack<String>();
        // get the first tag, j is the index of openning <
        int j=html.indexOf("<");
        while(j!=-1){
            //k is the index of the closing >
            int k=html.indexOf(">",j+1);
            // if k does not exist, return false
            if(k==-1) return false;

            // else get the tag
            String tag=html.substring(j+1,k);

            // if the tag is a starting tag, then push it into the stack buffer
            if(!tag.startsWith("/")) buffer.push(tag);

            else {
                //if the tag is a closing tag, and the buffer is empty, return false
                if (buffer.isEmpty()) return false;

                // if buffer is not empty, closing tag must match the openning tag in the buffer, otherwise return false
                if (!tag.substring(1).equals(buffer.pop())) return false;
            }
            // continue to next tag
            j=html.indexOf("<",k+1);
        }
        //In the end, if the buffer is empty, then all tag matches
        return buffer.isEmpty();
    }
}
