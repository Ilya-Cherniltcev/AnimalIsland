package javaRush.module2;

import javaRush.module2.model.Island;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** Input island's size *** (optimum 7 x 7)");
        System.out.print("x= ");
        int x_size = Integer.parseInt(scanner.nextLine());
        System.out.print("y= ");
        int y_size = Integer.parseInt(scanner.nextLine());

        Island island = new Island(x_size, y_size);
        island.lifeIsStarting();
    }
}