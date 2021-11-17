package com.company;

public class Main {

    public static void main(String[] args) {
    Gameboard g = new Gameboard();//g indeholder nu classen gameboard
        g.rows = 6;//set game size
        g.columns = 6;//set game size
        g.obstacles = 1;//hvor mange obstacles skal fremstaa paa boardet
        g.animals = 5;//hvor mange dyr skal der vaere
        g.setup();
        g.draw();
        while (g.liveAnimals>=1){
            System.out.println(g.liveAnimals);
            g.moveAnimals();
            if(g.liveAnimals==1 ){
                System.out.println("GAME OVER");
                break;
            }
        }
        System.out.println("GAME OVER");
        }
    }

