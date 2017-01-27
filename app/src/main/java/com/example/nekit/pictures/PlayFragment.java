package com.example.nekit.pictures;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.nekit.pictures.FourPicture.array;

public class PlayFragment extends Fragment {

    ImageView imgOne, imgTwo, imgThree, imgFour;
    Button btnCheck, btnCheat;
    static EditText edtEnter;

    Toast toast;
    static int count;
    boolean isTrueOrFalse;
    boolean isExtraForCheat;

    static final String INSTANCE_STATE = "instance state";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_play, container, false);

        imgOne = (ImageView) v.findViewById(R.id.img_one);
        imgTwo = (ImageView) v.findViewById(R.id.img_two);
        imgThree = (ImageView) v.findViewById(R.id.img_three);
        imgFour = (ImageView) v.findViewById(R.id.img_four);

        edtEnter = (EditText) v.findViewById(R.id.edt_enter);

        btnCheck = (Button) v.findViewById(R.id.btn_check);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtEnter.setEnabled(true);
                checkAnswer();
                if (isTrueOrFalse) {
                    count = (count + 1) % array().size();
                    edtEnter.setText("");
                } else {
                    edtEnter.setText("");
                }
                updatePictures();
                isExtraForCheat = false;
            }
        });

        btnCheat = (Button) v.findViewById(R.id.btn_cheat);
        btnCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CheatActivity.class);
                String putForCheat = array().get(count).getCorrectAnswer();
                i.putExtra(CheatActivity.EXTRA_FROM_PLAY_FRAGMENT, putForCheat);
                startActivityForResult(i, 0);
            }
        });

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(INSTANCE_STATE, 0);
        }

        updatePictures();
        return v;
    }

    /**
     * В методе сохраняется состояние экземпляра
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(INSTANCE_STATE, count);
    }

    /**
     * Метод получает результат, посмотрел пользователь ответ или нет, от активности CheatActivity
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        isExtraForCheat = data.getBooleanExtra(CheatActivity.EXTRA_TO_PLAY_FRAGMENT, false);
    }

    /**
     * Логика метода обновляет набор картинок в фрагменте.
     */
    private void updatePictures() {
        int currentOnePictures = array().get(count).getPictureOne();
        int currentTwoPictures = array().get(count).getPictureTwo();
        int currentThreePictures = array().get(count).getPictureThree();
        int currentFourPictures = array().get(count).getPictureFour();
        imgOne.setImageResource(currentOnePictures);
        imgTwo.setImageResource(currentTwoPictures);
        imgThree.setImageResource(currentThreePictures);
        imgFour.setImageResource(currentFourPictures);
    }

    /**
     * Метод проверяет подсмотрел ли пользователь ответ, если да, то Toast выводит CHEATER!!!,
     * если нет, и ответ правильный, то выводит CORRECT!, если ответ не правильный выводит INCORRECT.
     * Так же, логическая переменная isTrueOrFalse получает значение.
     * Это значение влияет на то, пройдет пользователь на следущий уровень или нет.
     */
    private void checkAnswer() {
        String answerTrue = array().get(count).getCorrectAnswer();
        int message;
        if (isExtraForCheat) {
            message = R.string.cheater;
            isTrueOrFalse = true;
        } else {
            if (answerTrue.equals(edtEnter.getText().toString())) {
                message = R.string.correct_toast;
                isTrueOrFalse = true;
            } else {
                message = R.string.incorrect_toast;
                isTrueOrFalse = false;
            }
        }
        toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, -50);
        toast.show();
    }
}
