import java.util.HashMap;

/**
 * Created by pya on 1/30/16.
 */
public abstract class GameObject {
    public float x = 0;
    public float y = 0;
    public HashMap<Integer, Component> components;

    public GameObject(HashMap<Integer, Component> components) {
        this.components = new HashMap<Integer, Component>(components);
    }
}
