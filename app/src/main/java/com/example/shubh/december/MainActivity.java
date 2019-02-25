package com.example.shubh.december;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private Button b;
    private EditText e;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts=new TextToSpeech(this, (TextToSpeech.OnInitListener) this);
        b=(Button)findViewById(R.id.button);
        e=(EditText)findViewById(R.id.editText);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voiceOutput();
            }
        });
    }
    private void voiceOutput()
    {
        CharSequence txt=e.getText();
        tts.speak(txt,TextToSpeech.QUEUE_FLUSH,null,"id1");
    }


    public void onInit(int status)
    {
        if(status==TextToSpeech.SUCCESS) {
            int reslt = tts.setLanguage(Locale.US);
            float i = 50;
            if (reslt == TextToSpeech.LANG_MISSING_DATA || reslt == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(MainActivity.this, "not", Toast.LENGTH_SHORT).show();
            } else {
                b.setEnabled(true);
                voiceOutput();
            }
        }
            else
            {
                Toast.makeText(MainActivity.this,"fal",Toast.LENGTH_SHORT).show();
            }

    }

    public void onDestroy()
    {
        if(tts!=null)
        {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
