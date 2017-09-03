package com.example.shubhamgupta.tictactoe;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Shubham Gupta on 16-06-2017.
 */

public class MyButton extends Button {
    int player;
    public MyButton(Context context)
        {
                super(context);
                player=MainActivity.NO_PLAYER;

        }
            int getplayer()
        {
            return player;
        }

}
