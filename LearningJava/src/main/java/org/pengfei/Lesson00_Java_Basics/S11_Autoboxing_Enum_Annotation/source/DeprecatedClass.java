package org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation.source;

@Deprecated
public class DeprecatedClass {

    private String msg;

    public DeprecatedClass(String msg){
        this.msg=msg;
    }

    @Deprecated
    public String getMsg(){
        return msg;
    }

    public void setMsg(String m){
        this.msg=m;
    }
}
