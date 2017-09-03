package com.example.shubhamgupta.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    EditText editText;
    TextView textView1;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setTitle("SHUBHAM'S TIC_TAC_TOE");
        editText=(EditText)findViewById(R.id.editText);
        Button startButton= (Button) findViewById(R.id.button);
        TextView textView1=(TextView)findViewById(R.id.text_view);
       sharedPreferences=getSharedPreferences("tic_tac_toe",MODE_PRIVATE);
      String name1=sharedPreferences.getString("USERNAME",null);
        Log.i(name1,"message is this");
        if(name1==null)
        {
            textView1.setText("welcome user ");
        }
        else
        {
            textView1.setText("welcome "+name1);
        }


        startButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
               String name=editText.getText().toString();

                if(name.isEmpty())
                {
                    Toast.makeText(StartActivity.this,"enter your name",Toast.LENGTH_SHORT).show();
                    return;
                }


                   SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("USERNAME",name);
                    editor.commit();
                Log.i(name,"kjjhgdghhggvgvgs");
                    Intent i=new Intent(StartActivity.this,MainActivity.class);
                   i.putExtra("USERNAME",name);
                    startActivity(i);

            }
        });


    }
}
