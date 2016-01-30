package com.teamname.goaton;

import java.util.*;

/**
 * Created by Simon on 1/30/2016.
 */
public class State {
    public List<String> allowedStates;
    public String currentState;
    public State(List<String> states, String startState)
    {
        this.allowedStates = states;
        if (!this.allowedStates.contains(startState))
        {
            //error, illegal state
        }
        else
        {
            currentState = startState;
        }
    }
}
