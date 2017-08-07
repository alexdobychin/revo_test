package ru.stqa.revo.appmanager;


import java.util.Random;


/**
 * Created by Alexandro on 23.01.2017.
 */
public class Star5 extends HelperBase {


    public Star5(ApplicationManager app) {
        super(app);
    }




    public String getRandRangeLowlettersrus(int min, int max) {
        Random random = new Random();
        int randomNumberInRange = getRandomNumberInRange(min, max);
        String strAllowedCharacters = "абвгдеёжзийклмнопрстуфхцчшщъыь";
        StringBuilder randomString = new StringBuilder(randomNumberInRange);
        for (int i = 0; i < randomNumberInRange; i++) {
            int randomInt = random.nextInt(strAllowedCharacters.length());
            randomString.append(strAllowedCharacters.charAt(randomInt));
        }
        return randomString.toString();
    }




    public String getRandomphone() {
        String part ="8903"; //1234567
        String part2 ="";
        String partm;
        String phone;
        String strAllowedCharacters = "0123456789";
        Random rand = new Random();

        for (int i = 0; i < 7 ; i++) {
            int  ch = strAllowedCharacters.length();
            int  n = rand.nextInt(ch);
            partm = String.valueOf(n);
            part2 = part2+ partm;
        }


        phone = part+part2;
        System.out.println(phone);
        return phone;
    }

    public String getRandompasport() {


        String partm;
        String pasport = "";
        String strAllowedCharacters = "0123456789";
        Random rand = new Random();

        for (int i = 0; i < 6 ; i++) {
            int  ch = strAllowedCharacters.length();
            int  n = rand.nextInt(ch);
            partm = String.valueOf(n);
            pasport = pasport+ partm;
        }


        System.out.println(pasport);
        return pasport;
    }



    public String getRandRangeLowletters(int min, int max) {
        Random random = new Random();
        int randomNumberInRange = getRandomNumberInRange(min, max);
        String strAllowedCharacters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder(randomNumberInRange);
        for (int i = 0; i < randomNumberInRange; i++) {
            int randomInt = random.nextInt(strAllowedCharacters.length());
            randomString.append(strAllowedCharacters.charAt(randomInt));
        }
        return randomString.toString();
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return random.nextInt((max - min) + 1) + min;
    }






}











