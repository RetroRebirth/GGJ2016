import com.badlogic.gdx.graphics.Color;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by pya on 1/30/16.
 */
public class Goat extends GameObject {
    /* Goat color */
    public Color color;

    public Goat(HashMap<Integer, Component> comps, Color color) {
        super(comps);

        this.color = color;
    }

    @Override
    public void Update() {

    }

    public void Wander() {

    }

    public void RunAway() {

    }
}
