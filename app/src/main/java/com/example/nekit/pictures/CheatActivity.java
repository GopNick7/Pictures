package com.example.nekit.pictures;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

    TextView txtAnswer;
    Button btnAnswer, btnBack;

    String extraFromPlayFragment;
    boolean isLookedAnswer;

    static final String EXTRA_FROM_PLAY_FRAGMENT = "obtain key additions";
    static final String EXTRA_TO_PLAY_FRAGMENT = "key additions returns";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        txtAnswer = (TextView) findViewById(R.id.txt_answer);

        // Переменная получает дополнение от PlayFragment
        extraFromPlayFragment = getIntent().getStringExtra(EXTRA_FROM_PLAY_FRAGMENT);
        clickCheck(false);
        btnAnswer = (Button) findViewById(R.id.btn_answer);
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCheck(true);
                isLookedAnswer = true;
                txtAnswer.setText(extraFromPlayFragment);
            }
        });

        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Если пользователь посмотрел ответ, то виджет edtEnter класса PlayFragment,
                //становится неактивным и в него записывается ответ.
                if (isLookedAnswer) {
                    PlayFragment.edtEnter.setEnabled(false);
                    PlayFragment.edtEnter.setText(FourPicture.array().get(PlayFragment.count).getCorrectAnswer());
                }
                finish();
            }
        });
        isLookedAnswer = false;
    }

    /**
     * Метод передает результат, посмотрел пользователь ответ или нет в фрагмент PlayFragment
     */
    private void clickCheck(boolean b) {
        Intent data = new Intent();
        data.putExtra(EXTRA_TO_PLAY_FRAGMENT, b);
        setResult(RESULT_OK, data);
    }
}
