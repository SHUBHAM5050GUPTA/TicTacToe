package com.example.shubhamgupta.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout mainLayout;
    MyButton buttons[][];
    public static int n=3;

    public final static int NO_PLAYER=0;
    public final static int PLAYER_1=1;
    public final static int PLAYER_2=2;
    public final static int INCOMPLETE=0;
    public final static int PLAYER1_WINS=1;
    public final static int PLAYER2_WINS=2;
    public final static int DRAW=3;
    public static boolean gameover=false;

    public  static boolean PLAYER1_TURN=true;

    LinearLayout rowLayout[];
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout=(LinearLayout)findViewById(R.id.mainLayout);

        //showing user name
        Intent i2=getIntent();
        String username1=i2.getStringExtra("USERNAME");
        Toast.makeText(this,"user "+username1,Toast.LENGTH_SHORT).show();
        setBoard();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.newgame){
            resetBoard();
        }else if(id == R.id.setting){
            //   Math.random()

        }
        return true;
    }

    public void resetBoard()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                buttons[i][j].player=NO_PLAYER;
                buttons[i][j].setText("");
            }
        }
        textView.setText("PLAYER1_TURN");
        PLAYER1_TURN=true;
        gameover=false;
    }





    public void setBoard()
    {
        buttons=new MyButton[n][n];
        rowLayout=new LinearLayout[n];
        mainLayout.removeAllViews();

        for(int i=0;i<n;i++)
        {
            rowLayout[i]=new LinearLayout(this);
            LinearLayout.LayoutParams parameters= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1f);
            parameters.setMargins(1,1,1,1);
            rowLayout[i].setLayoutParams(parameters);
            rowLayout[i].setOrientation(LinearLayout.HORIZONTAL);
            rowLayout[i].setBackgroundColor(ContextCompat.getColor(this,R.color.white));
            mainLayout.addView(rowLayout[i]);
        }

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++) {

                buttons[i][j] = new MyButton(this);
                LinearLayout.LayoutParams parameters=new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1f);
                parameters.setMargins(5,5,5,5);
                buttons[i][j].setLayoutParams(parameters);
                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setTextSize(50);
                buttons[i][j].setTextColor(ContextCompat.getColor(this,R.color.lightgrey1));
                buttons[i][j].setBackground(ContextCompat.getDrawable(this,R.drawable.boundry));
                rowLayout[i].addView(buttons[i][j]);

            }

        }

    }

    @Override
    public void onClick(View v) {
         textView=(TextView) findViewById(R.id.textview_1);
            if(gameover)
            {
                return;


            }
        MyButton button=(MyButton)v;

        if(button.player!=NO_PLAYER)
        {

            Toast.makeText(this,"INVALID MOVE",Toast.LENGTH_SHORT).show();
            return;
        }


        if(PLAYER1_TURN)
        {
            button.player=PLAYER_1;
            textView.setText("PLAYER2_TURN");
            button.setText("0");
        }
        else
        {
            button.player=PLAYER_2;
            textView.setText("PLAYER1_TURN");
            button.setText("X");
        }


        //TO CHECK GAME STATUS
        int status=gameStatus();
        if(status==PLAYER1_WINS)
        {
           textView.setText("PLAYER1_WINS");
            gameover=true;
        }
        else if(status==PLAYER2_WINS)
        {
            textView.setText("PLAYER2_WINS");
            gameover=true;
        }

        else if(status==DRAW)
        {
            textView.setText("DRAW");
            gameover=true;
        }

        PLAYER1_TURN=!PLAYER1_TURN;

    }

    private int gameStatus() {
    // row
        for(int i=0;i<n;i++)
        {
            boolean flag =true;
            for(int j=0;j<n;j++) {
                if(buttons[i][j].getplayer()==NO_PLAYER ||buttons[i][j].getplayer()!=buttons[i][0].getplayer())
                {
                  flag=false;
                    break;
                }
            }


            if(flag)
            {
                if(buttons[i][0].getplayer()==PLAYER_1)
                {
                return PLAYER1_WINS;
            }
                else
                {
                    return PLAYER2_WINS;
                }
            }
        }

        //column
        for(int j=0;j<n;j++)
        {
            boolean flag =true;
            for(int i=0;i<n;i++) {
                if(buttons[i][j].getplayer()==NO_PLAYER||buttons[i][j].getplayer()!=buttons[0][j].getplayer())
                {
                    flag=false;
                    break;
                }
            }


            if(flag)
            {
                if(buttons[0][j].getplayer()==PLAYER_1)
                {
                    return PLAYER1_WINS;
                }
                else
                {
                    return PLAYER2_WINS;
                }
            }
        }

        // diagnal 1
    boolean flag=true;
        for(int i=0;i<n;i++)
        {
            if(buttons[i][i].getplayer()==NO_PLAYER||buttons[i][i].getplayer()!=buttons[0][0].getplayer())
            {
                flag=false;
                break;
            }
        }
        if(flag)
        {
            if(buttons[0][0].getplayer()==PLAYER_1)
            {
                return PLAYER1_WINS;
            }
            else
            {
                return PLAYER2_WINS;
            }
        }

        // diagnal 2
         flag=true;
        for(int i=0;i<n;i++) {
            if (buttons[i][n - 1 - i].getplayer() == NO_PLAYER || buttons[i][n - 1 - i].getplayer() != buttons[0][n - 1].getplayer()) {
                flag = false;
                break;
            }
        }

            if (flag) {
                if (buttons[0][n - 1].getplayer() == PLAYER_1) {
                    return PLAYER1_WINS;
                } else {
                    return PLAYER2_WINS;
                }
            }

        //Incomplte

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(buttons[i][j].getplayer()==NO_PLAYER)
                {
                    return INCOMPLETE;
                }
            }
        }

        //DRAW
        return DRAW;

    }



}
