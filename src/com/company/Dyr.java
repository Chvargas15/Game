package com.company;

import java.util.Random;

public class Dyr {

    int dyrshape = 1; //det her er dyrets form paa boardet
    int currentColumn;
    int currentRow;
    int myId;


    void myPosition() {
        System.out.println("Dyr med id: " + myId + " currentrow: " + currentRow + " currentcolumn:  " + currentColumn);
    }
    void move() {

        Random random = new Random();
        int columnMove = random.nextInt(3) - 1; //formel fra hjemmeside;
        int rowMove = random.nextInt(3) - 1;
        currentColumn = currentColumn + columnMove;
        currentRow = currentRow + rowMove;



        //System.out.println("Dyr med id: "+ myId + " rowmove: " +rowMove+ " columnmove:  " +columnMove);
        //System.out.println("Dyr med id: "+ myId + " currentrow: " +currentRow+ " currentColumn:  " +currentColumn);
    }
}