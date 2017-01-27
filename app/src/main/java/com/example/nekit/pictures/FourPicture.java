package com.example.nekit.pictures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourPicture {
    private int pictureOne;
    private int pictureTwo;
    private int pictureThree;
    private int pictureFour;

    private String correctAnswer;

    public FourPicture(int pictureOne, int pictureTwo, int pictureThree, int pictureFour, String correctAnswer) {
        this.pictureOne = pictureOne;
        this.pictureTwo = pictureTwo;
        this.pictureThree = pictureThree;
        this.pictureFour = pictureFour;
        this.correctAnswer = correctAnswer;
    }

    public int getPictureOne() {
        return pictureOne;
    }

    public int getPictureTwo() {
        return pictureTwo;
    }

    public int getPictureThree() {
        return pictureThree;
    }

    public int getPictureFour() {
        return pictureFour;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public static List<FourPicture> array() {
        List<FourPicture> arr = new ArrayList<>(Arrays.asList(
                new FourPicture(R.drawable.animal1, R.drawable.animal2, R.drawable.animal3, R.drawable.animal4, "животное"),
                new FourPicture(R.drawable.story1, R.drawable.story2, R.drawable.story3, R.drawable.story4, "сказка"),
                new FourPicture(R.drawable.transport1, R.drawable.transport2, R.drawable.transport3, R.drawable.transport4, "транспорт"),
                new FourPicture(R.drawable.tree1, R.drawable.tree2, R.drawable.tree3, R.drawable.tree4, "дерево"),
                new FourPicture(R.drawable.water1, R.drawable.water2, R.drawable.water3, R.drawable.water4, "вода"),
                new FourPicture(R.drawable.fire1, R.drawable.fire2, R.drawable.fire3, R.drawable.fire4, "огонь"),
                new FourPicture(R.drawable.fruit1, R.drawable.fruit2, R.drawable.fruit3, R.drawable.fruit4, "фрукт"),
                new FourPicture(R.drawable.weather1, R.drawable.weather2, R.drawable.weather3, R.drawable.weather4, "погода"),
                new FourPicture(R.drawable.green1, R.drawable.green2, R.drawable.green3, R.drawable.green4, "зеленый"),
                new FourPicture(R.drawable.travel1, R.drawable.travel2, R.drawable.travel3, R.drawable.travel4, "путешествие")
        ));
        return arr;
    }
}
