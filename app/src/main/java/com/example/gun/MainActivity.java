package com.example.gun;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends Activity {


    private SoundPool sounds;
    private int sound_shot;
    private int sound_shot_false;
    private int sound_baraban;
    private ImageView Blood_image;
    private int on_Shot = 3;
    private int max_number = 6;
    private int random = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSoundPool();
        loadSounds();
        init();
    }

    protected void createSoundPool() {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            createNewSoundPool();
        }else {
            createOldSoundPool();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void createNewSoundPool(){
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        sounds = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();


    }

    @SuppressWarnings("deprecation")
    protected  void createOldSoundPool () {sounds = new SoundPool(5, AudioManager.STREAM_MUSIC,0);}
    protected void loadSounds()
    {
        sound_shot = sounds.load(this,R.raw.revolver_shot, 1);
        sound_shot_false = sounds.load(this,R.raw.gun_false, 1);
        sound_baraban = sounds.load(this,R.raw.revolver_baraban, 1);

    }

    public void onShot(View view) {
        if (random== on_Shot) {
            sounds.play(sound_shot, 1.0f, 1.0f, 1, 0, 1);
            Blood_image.setVisibility(View.VISIBLE);
        }
        else
            {
                sounds.play(sound_shot_false,1.0f,1.0f,1,0,1);
            }
    }

   /* public void onFalse(View view)
    {
        sounds.play(sound_shot_false,1.0f,1.0f,1,0,1);
    }
*/
    public void onBaraban(View view)
    {
        sounds.play(sound_baraban,1.0f,1.0f,1,0,1);
        Blood_image.setVisibility(View.GONE);
        random = new Random().nextInt(max_number);
        Log.d("MainActivity","Random Number"+ random);

    }
    protected void init()
    {
        Blood_image = findViewById(R.id.imageBlood);
    }
}