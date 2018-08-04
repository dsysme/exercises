package com.dsysme.interviews;

import java.util.concurrent.ThreadLocalRandom;

public enum  Anyclip {
    INSTANCE;

    //write a function that return 0 60% of the times, 1 30% and 2 10% of the time
    public int customRandom() {
       int randomNumber = ThreadLocalRandom.current().nextInt(1, 11);
       if (randomNumber < 7)
           return 0;
       if (randomNumber < 10) {
           return 1;
       }
       return 2;
    }
}
