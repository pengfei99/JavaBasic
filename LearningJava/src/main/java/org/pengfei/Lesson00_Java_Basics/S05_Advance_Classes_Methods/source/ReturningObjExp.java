package org.pengfei.Lesson00_Java_Basics.S05_Advance_Classes_Methods.source;

public class ReturningObjExp {
    static class ErrMsg{
        String msg;
        int severity;
        ErrMsg(String m, int s){
            msg=m;
            severity=s;
        }
    }


    public static void exp1(){
        var error=getErrorMsg(2);
        System.out.println("Error has info "+error.msg+" has severity "+error.severity);
    }

    public static ErrMsg getErrorMsg(int i){
        String msgs[]={
                "Output Error",
                "Input Error",
                "Disk Full",
                "Out-of-bounds"
        };
        int[] severityList={3,3,2,1};

        if(i>=0 && i<msgs.length) return new ErrMsg(msgs[i],severityList[i]);
        else return new ErrMsg("Invalid code",0);
    }
}
