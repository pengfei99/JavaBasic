package org.pengfei.leetcode.Pb456;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pliu on 10/30/17.
 */


//  Problem Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.
//
//        Note: n will be less than 15,000.

    /*
    * Example 1
    *
    * Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
    * */

    /*
    * Example 2
    *
    * Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
    * */

    /*
    * Example 3
    *
    * Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
    * */

public class Main {

    public static void main(String[] args){

        List<Integer> input=new ArrayList<Integer>();
        input.add(3);
        input.add(1);
        input.add(4);
        input.add(2);
        input.add(3);


        Main runner=new Main();
        runner.brutForce(input);


    }


    public void brutForce(List<Integer> input){
        int i,j,k;

        for(i=0;i<input.size();i++){
            j=i+1;
            boolean flag=false;
            while (j<input.size()){
                //find a good j
                if(input.get(i)<input.get(j))
                {
                    //find k
                    k=j+1;
                    while(k<input.size()){
                        if (input.get(j)>input.get(k)){
                            System.out.println("Find a pattern: "+input.get(i)+" "+input.get(j)+" "+input.get(k));
                            flag=true;
                            break;
                        }
                        //k is not good, continue to next
                        else {k=k+1;}
                        if(flag=true) break;
                    }

                }
                //j is not good, continue to next
                else{j=j+1;}
                if(flag=true) break;
            }
        }
    }
}
