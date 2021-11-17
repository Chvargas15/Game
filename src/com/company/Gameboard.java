package com.company;
import java.util.Objects;
import java.util.Random;

public class Gameboard {

    int rows;
    int columns;
    int obstacles;
    int animals;
    int[][] gameBoardArray;
    Boolean isTaken;
    int liveAnimals;
    Dyr[] dyrArray;

    //spillets setup
    void setup() {
        gameBoardArray = new int[rows][columns]; //nye variable af typen int som er array som er i 2 dimensioner = dimensionerne rows og columss (x og y)
        liveAnimals = animals; //liveanimals er = det antal dyr som der er blevet defineret i main
        for (int row = 0; row < gameBoardArray.length; row++) //for hver raekke 0-5 bygger vi 0-5 kolonner
        {
            for (int col = 0; col < gameBoardArray[row].length; col++) //danner kolonner pr raekker ved næste række op til linje 10 igen (excel)
            {
                gameBoardArray[row][col] = 0; // i position row,col (lige som x,y) 0 valgt på baggrund af dens betydning (0) der er intet her
            }
        }
        placeObstacle(); //efter vi har generet initiale gameboard placerer vi obstacles
        placeAnimals();

    }
    //placering af obstacles
    void placeObstacle() {
        //her skal der placeres obstacles paa gameboardet
        //brugeren har bestemt hvor mange obstacles som skal placeres i variablen obstacles
        //der laves et loop til hver obstacle
        Random r = new Random();
        for (int o = 0; o < obstacles; o++) {
            //obstacles skal placeres random i vores boardgame
            //altid teste om der ikke er en obstacle i forvejen
            int obstacleRow = r.nextInt(rows); //formel/ her findes random placering mellem 1 og rows
            int obstacleColumn = r.nextInt(columns); //her findes random kolonne mellem 1 og columns
            //while losning find ud af om row colom er taget / hvis open place vaerdi ellers kor 38 og 39 igen
            gameBoardArray[obstacleRow][obstacleColumn] = 4; //formen paa obstacle

        }
    }
    //placering af dyr
    void placeAnimals() {
        Random r = new Random();
        dyrArray = new Dyr[animals];

        for (int o = 0; o < dyrArray.length; o++) {
            //animals skal placeres random i vores boardgame
            int animalRow = r.nextInt(rows); //formel/ her findes random placering mellem 1 og rows
            int animalColumn = r.nextInt(columns); //her findes random kolonne mellem 1 og columns
            isOccupied(animalRow, animalColumn);
            if (isTaken == false) { //hvis feltet ikke er taget sa kan der godt placeres et dyr
                gameBoardArray[animalRow][animalColumn] = 1; //vi kan kun placere et dyr hvis der ikke var noget i feltet//definerer dyr som 1

                //Nyt dyr
                Dyr nytDyr = new Dyr();
                nytDyr.myId = o;
                nytDyr.currentColumn = animalColumn;
                nytDyr.currentRow = animalRow;
                nytDyr.myPosition();
                dyrArray[o] = nytDyr;

            } else {
                liveAnimals -= 1;
                dyrArray[o] = null;
            }
        }

    }
    void moveAnimals() {
        for (int a = 0; a < dyrArray.length; a++) {

            try {
                Dyr d = new Dyr();
                d = dyrArray[a];
                if (d.currentColumn < 0 || d.currentColumn >= columns) { //angiver hvornår noget er udenfor brættet
                    liveAnimals -= 1;
                    dyrArray[a] = null;
                    System.out.println("Dyret med id: " + d.myId + " er rykket ud af braettet og er dead ");
                } else if (d.currentRow < 0 || d.currentRow >= rows) {
                    liveAnimals -= 1;
                    dyrArray[a] = null;
                    System.out.println("Dyret med id: " + d.myId + " er rykket ud af braettet og er dead ");
                } else if (Objects.isNull(d)) { //sikrer at der er et dyr
                    System.out.println("Dyret med id: " + d.myId + " findes ikke");
                } else {
                    gameBoardArray[d.currentRow][d.currentColumn] = 0; //saettes lig med 0 da man skal vaek fra denne celle
                    d.move();
                    this.isOccupied(d.currentRow, d.currentColumn);
                    if (isTaken == false) {
                        gameBoardArray[d.currentRow][d.currentColumn] = 1; //saettes lig med 1 grundet ny placering
                    } else {
                        liveAnimals -= 1;
                        dyrArray[a] = null;
                        System.out.println("Dyret med id: " + d.myId + " ramte et andet dyr eller obstacle og er dead "); //vises hvis et dyr rammer et obstacle eller andet dyr
                    }

                }
                this.draw();
                Thread.sleep(2000); //pauser tråden 2 sekunder


            } catch (Exception e) {
               // e.printStackTrace(); //tag kun fra hvis nødvendigt

            }
        }
    }
    //printer game boarded
    void draw() {
        System.out.println("Tegner nyt board");
        for (int row = 0; row < gameBoardArray.length; row++) //for hver raekke 0-5 bygger vi 0-5 kolonner
        {
            for (int col = 0; col < gameBoardArray[row].length; col++) //danner kolonner pr raekker ved næste række op til linje 10 igen (excel)
            {
                System.out.print(gameBoardArray[row][col]); //printer forloop, det vi ser
            }
            System.out.println(); //linje mellemrum
        }
        System.out.println("Antal af levende dyr: " + liveAnimals);
    }


    void isOccupied(int r, int c) { //viser om der er plads på row and/or columns
        if (gameBoardArray[r][c] == 0) { //hvis der står 4 så er der optaget
            isTaken = false; //false pladsen er fri
        } else {
            isTaken = true; //true pladsen er optaget

        }
    }
}