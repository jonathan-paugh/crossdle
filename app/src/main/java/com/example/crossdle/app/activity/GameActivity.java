package com.example.crossdle.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.crossdle.app.fragment.BoardFragment;
import com.example.crossdle.R;
import com.example.crossdle.app.fragment.KeyboardFragment;
import com.example.crossdle.app.popup.FinishedGamePopup;
import com.example.crossdle.game.Board;
import com.example.crossdle.game.BoardView;

public class GameActivity extends AppCompatActivity {
    private Board board;
    private BoardFragment boardFragment;
    private KeyboardFragment keyboardFragment;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        BoardView view = new BoardView();

        board = new Board(view, Board.TEST_LAYOUT);

        keyboardFragment = KeyboardFragment.frame(getSupportFragmentManager(), R.id.game_fragmentView_keyboard, board);
        boardFragment = BoardFragment.frame(getSupportFragmentManager(), R.id.game_fragmentView_board, board);

        board.setOnWin(this::win);

        view.setViewHandler(boardFragment::getView);

        mediaPlayer = MediaPlayer.create(this, R.raw.game_song);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void win() {
        int duration = 2000;

        View view = boardFragment.getView();
        animateWin(view, duration);

        view.postDelayed(() -> {
            Intent intent = new Intent(this, FinishedGamePopup.class);
            startActivity(intent);
        }, (long)(duration * 0.7));
    }

    private void animateWin(View view, int duration) {
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.mixed_anim);
        animation.setDuration(duration);
        view.startAnimation(animation);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
         if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                board.clickBack();
            } else if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                board.clickEnter();
                return true;
            } else {
                char character = (char)event.getUnicodeChar(event.getMetaState());
                if (Character.isLetter(character)) {
                    board.clickKey((Character.toUpperCase(character)));
                }
            }
        }

        return super.dispatchKeyEvent(event);
    }
}