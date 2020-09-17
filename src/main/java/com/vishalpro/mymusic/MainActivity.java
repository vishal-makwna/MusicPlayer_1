package com.vishalpro.mymusic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer Player;
    MediaPlayer yalgar;
    AudioManager audioManager;

    MediaPlayer mediaPlayer[] = {Player, yalgar};



    //for playing the music
    public void  play(View view){
        Player.start();
    }

    public void pause(View view){
        Player.pause();
        yalgar.pause();

    }

    public void stop (View view){
            Player.stop();
            yalgar.stop();

        Toast.makeText(this, "Warning you pressed Stop button you app is crash " +
                                   "Now you can't play song pleas exit the app and restart it", Toast.LENGTH_LONG).show();

    }

    public void next(View view){
//        yalgar.start();
        for (int i = 0 ; i<=2;i++){
            mediaPlayer[i].start();
            break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Player = MediaPlayer.create(this, R.raw.Perfect);

        Toast.makeText(this, "Tap on Next to Play Yalgar", Toast.LENGTH_SHORT).show();

        Player = MediaPlayer.create(this, R.raw.music);
        yalgar = MediaPlayer.create(this, R.raw.music2);


        audioManager =(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int carVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar seekVol = findViewById(R.id.seekVal);
        seekVol.setMax(maxVol);
        seekVol.setProgress(carVol);

        seekVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               // audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i , 0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar  seekPro = findViewById(R.id.seekPro);
        seekPro.setMax(Player.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekPro.setProgress(Player.getCurrentPosition());
            }
        } ,0 , 900);

        seekPro.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Player.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}