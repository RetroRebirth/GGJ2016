package com.teamname.goaton.Shaders;

/**
 * Created by kpidding on 1/29/16.
 */

public class ShaderException extends RuntimeException
{
    ShaderException(String msg)
    {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    private String msg;
}
