package com.example.valeapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.SupportActionModeWrapper;
import androidx.appcompat.widget.Toolbar;

import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Multimedia  extends AppCompatActivity{

    private EditText usuarioT;
    private String usuario;
    private String creador;
    private String nombreTarea;
    private Boolean guardarRespuesta;
    private String nombreMultimadia;
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimedia);

        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");
        creador = bundle.getString("creador");
        nombreTarea = bundle.getString("nombreTarea");
        guardarRespuesta = bundle.getBoolean("guardarRespuesta");
        nombreMultimadia = bundle.getString("nombreMultimedia");


        //esVideo

        if (guardarRespuesta){

        }

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.barra_de_tareas);

        //Modicar Barra de Tareas para esta pantalla
        final ImageButton flechaAtras = findViewById(R.id.flechaVolverMenuAnterior);
        flechaAtras.setVisibility(View.VISIBLE);
        flechaAtras.setContentDescription("Volver a la tarea");
        final TextView textoFlechaAtras = findViewById(R.id.textoVolverAMenuAnterior);
        textoFlechaAtras.setText("Volver a la tarea");
        textoFlechaAtras.setVisibility(View.VISIBLE);
        final ImageButton botonLogout = findViewById(R.id.botonLogout);
        final ImageButton botonAtras = findViewById(R.id.flechaVolverMenuAnterior);
        //Boton logout
        botonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                irALogout();
            }
        });

        //Boton Atrás
        botonAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                volverATarea();
            }
        });
        reproducirMultimedia();
    }

    private void reproducirMultimedia(){


        UniversalVideoView multimediaView = findViewById(R.id.multimediaView);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory() + File.separator + nombreMultimadia);
        multimediaView.setVideoURI(uri);
        UniversalMediaController mMediaController = findViewById(R.id.media_controller);
        multimediaView.setMediaController(mMediaController);
/*
        multimediaView.setVideoViewCallback(new UniversalVideoView.VideoViewCallback() {
            private static final String TAG = "";
            private boolean isFullscreen;
            private int cachedHeight;

            @Override
            public void onScaleChange(boolean isFullscreen) {
                this.isFullscreen = isFullscreen;
                if (isFullscreen) {
                    ViewGroup.LayoutParams layoutParams = multimediaView.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    multimediaView.setLayoutParams(layoutParams);

                } else {
                    ViewGroup.LayoutParams layoutParams = multimediaView.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = this.cachedHeight;
                    multimediaView.setLayoutParams(layoutParams);
                }
            }
            @Override
            public void onPause(MediaPlayer mediaPlayer) { // Video pause
                Log.d(TAG, "onPause UniversalVideoView callback");
            }

            @Override
            public void onStart(MediaPlayer mediaPlayer) { // Video start/resume to play
                Log.d(TAG, "onStart UniversalVideoView callback");
            }

            @Override
            public void onBufferingStart(MediaPlayer mediaPlayer) {// steam start loading
                Log.d(TAG, "onBufferingStart UniversalVideoView callback");
            }

            @Override
            public void onBufferingEnd(MediaPlayer mediaPlayer) {// steam end loading
                Log.d(TAG, "onBufferingEnd UniversalVideoView callback");
            }

        });*/
        multimediaView.start();
    }
    
    @Override
    public void onBackPressed() {
        volverATarea();
    }

    public void volverATarea(){
        Intent intent = new Intent(this, TareaDetallada.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("guardarRespuesta", guardarRespuesta);
        if (guardarRespuesta){

        }
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
           getSupportActionBar().hide();
        } else {
            getSupportActionBar().show();
        }
    }


    private void irALogout(){
        Intent intent = new Intent(this, Logout.class);
        startActivity(intent);
    }
}
