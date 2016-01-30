package com.teamname.goaton;

/**
 * Created by kpidding on 1/29/16.
 */
public class Message {
    public Message(String msg)
    {
        this.msg = msg;
        this.arg = null;
    }
    public Message(String msg, Object arg)
    {
        this.msg = msg;
        this.arg = arg;
    }


    public String getMessage()
    {
        return msg;
    }
    public Object getArg()
    {
        return arg;
    }

    String msg;
    Object arg;


}
