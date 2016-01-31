package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.teamname.goaton.*;

import java.util.Random;

/**
 * Created by kpidding on 1/31/16.
 */
public class CameraControlComponent extends Component {
    private Camera cam;
    private float xOff;
    private float yOff;
    private Vector2 camPos;
    private float shakeTime;
    private float intensity = 50f;

    public CameraControlComponent(Camera c)
    {
        this.cam = c;
    }
    @Override
    public String getID() {
        return "CameraControlComponent";
    }

    @Override
    protected void create() {
        super.create();
        this.on("cameraShake", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                CamShakeControl ctrl = (CamShakeControl)msg.getArg();
                intensity = ctrl.intensity;
                shakeTime = ctrl.duration;
            }
        });
    }

    @Override
    protected void update(float dt) {
        super.update(dt);
        if(shakeTime > 0.0)
        {
            shakeTime -= dt;
            xOff = GoatonWorld.Random.nextFloat() * intensity - intensity/2;
            yOff = GoatonWorld.Random.nextFloat() * intensity - intensity/2;
        }
        else
        {
            xOff = 0;
            yOff = 0;
        }
        cam.position.set(new Vector3(xOff,yOff,0).add(new Vector3(camPos.x, camPos.y, 0)));
        cam.update();
    }

    @Override
    public Component cloneComponent() {
        return null;
    }

    public void setCameraPosition(Vector2 camPos)
    {
        this.camPos = camPos;
    }
}
