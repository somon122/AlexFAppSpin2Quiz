package com.world_tech_point.visiting_earnapp.earning;

public class Quiz_Questions {

    public String mChoices[][] = {
            {"_ is a girl."   ,"She", "He", "It", "I",  "She"},
            {"_ is a girl."   ,"She", "He", "It", "I",  "She"},
            {"_ is a girl."   ,"She", "He", "It", "I",  "She"},
            {"_ is a girl."   ,"She", "He", "It", "I",  "She"},
            {"_ is a girl."   ,"She", "He", "It", "I",  "She"},
            {"_ is a girl."   ,"She", "He", "It", "I",  "She"},
            {"_ is a girl."   ,"She", "He", "It", "I",  "She"}


    };

    public String getQuestion(int a) {
        String choice = mChoices[a][0];
        return choice;
    }


    public String getChoices1(int a) {
        String choice = mChoices[a][1];
        return choice;
    }

    public String getChoices2(int a) {
        String choice = mChoices[a][2];
        return choice;
    }

    public String getChoices3(int a) {
        String choice = mChoices[a][3];
        return choice;
    }

    public String getChoices4(int a) {
        String choice = mChoices[a][4];
        return choice;
    }

    public String getAns(int a) {
        String choice = mChoices[a][5];
        return choice;
    }



}
