package com.teamname.goaton.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.teamname.goaton.Component;
import com.teamname.goaton.Assets;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;

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
    Sound titleTheme;
    Sound mainThemeIntro;
    Sound mainThemeLoop;

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
        // Title music and Main music
        this.titleTheme = Gdx.audio.newSound(Gdx.files.internal(Assets.title_Theme));
        this.mainThemeIntro = Gdx.audio.newSound(Gdx.files.internal(Assets.main_Intro));
        this.mainThemeLoop = Gdx.audio.newSound(Gdx.files.internal(Assets.main_Loop));
    }

    @Override
    protected void create() {
        // Handles sound for picking up goats (secret and default)
        on("pickup", new MsgHandler() {
            @Override
            public void handle(Message msg) {

            }
        });
        // Handles sound for tossing goats (secret and default)
        on("throwGoat", new MsgHandler() {
            @Override
            public void handle(Message msg) {

            }
        });
        // Handles sound for the boss spawn
        on("spawnBoss", new MsgHandler() {
            @Override
            public void handle(Message msg) {

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
