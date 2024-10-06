package javaRush.module2;

import javaRush.module2.model.Island;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;


@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Application started");
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