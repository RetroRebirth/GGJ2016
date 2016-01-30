package com.teamname.goaton;

/**
 * Created by kpidding on 1/29/16.
 */
public abstract class MsgHandler {
    private String msg;

    public abstract void handle(Message msg);

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getMsg()
    {
        return msg;
    }

}
