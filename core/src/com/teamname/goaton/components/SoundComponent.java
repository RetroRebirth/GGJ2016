package com.teamname.goaton.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.teamname.goaton.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Cody Kitchener on 1/31/2016.
 */
public class SoundComponent extends Component {
    Sound goatTossSFX;
    Sound goatPickupSFX;
    Sound secretPickupSFX;
    Sound secretTossSFX;
    Sound bossSpawnSFX;
    Sound demonSpawnSFX;
    Sound impactSFX;
    Sound movementSFX;
    Timer timer;

    private class WaitToExecute extends TimerTask {
        Sound toDispose;

        public WaitToExecute(Sound toDispose) {
            this.toDispose = toDispose;
        }

        public void run() {
            toDispose.play(0.8f);
            timer.cancel();
        }
    }

    private class WaitToDispose extends TimerTask {
        Sound toDispose;

        public WaitToDispose(Sound toDispose) {
            this.toDispose = toDispose;
        }

        public void run() {
            toDispose.dispose();
            timer.cancel();
        }
    }

    public SoundComponent() {
        // Goat SFX
        this.goatTossSFX = Gdx.audio.newSound(Gdx.files.internal(Assets.goat_Toss_SFX));
        this.goatPickupSFX = Gdx.audio.newSound(Gdx.files.internal(Assets.goat_Pickup_SFX));
        this.secretPickupSFX = Gdx.audio.newSound(Gdx.files.internal(Assets.secret_Pickup_SFX));
        this.secretTossSFX = Gdx.audio.newSound(Gdx.files.internal(Assets.secret_Toss_SFX));
        // Boss SFX
        this.bossSpawnSFX = Gdx.audio.newSound(Gdx.files.internal(Assets.boss_Entrance_SFX));
        // Demon SFX
        this.demonSpawnSFX = Gdx.audio.newSound(Gdx.files.internal(Assets.demon_Spawn_SFX));
        // General impact SFX
        this.impactSFX = Gdx.audio.newSound(Gdx.files.internal(Assets.impact_SFX));
        // Footsteps SFX
        this.movementSFX = Gdx.audio.newSound(Gdx.files.internal(Assets.movement_SFX));
        // Set up a timer
        timer = new Timer();
    }

    @Override
    protected void create() {
        // Handles sound for picking up goats (secret and default)
        on("pickupSound", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                if (GoatonWorld.Random.nextFloat() > 0.70f) {
                    secretPickupSFX.play(1.0f);
                }
                else {
                    goatPickupSFX.play(0.5f);
                }
            }
        });
        // Handles sound for tossing goats (secret and default)
        on("throwGoatSound", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                if (GoatonWorld.Random.nextFloat() > 0.70f) {
                    secretTossSFX.play(1.0f);
                }
                else {
                    goatTossSFX.play(0.5f);
                }
            }
        });
        // Handles sound for the boss spawn
        on("bossSpawnSound", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                bossSpawnSFX.play(1.0f);
            }
        });
        // Handles sound for a new demon spawning

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public String getID() { return "SoundComponent"; }

    @Override
    public Component cloneComponent() { return new SoundComponent(); }
}
