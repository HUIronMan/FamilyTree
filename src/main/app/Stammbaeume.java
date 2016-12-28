package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Created by kevintrogant on 15.12.16.
 */
public class Stammbaeume {

    public static void main(String[] args) {
        System.out.println("Stammbäume");
        System.out.println("Exit with \"exit\" or \"quit\"");


        UI ui = new UI();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = "";
            boolean run = true;
            while (run) {
                System.out.print("> ");
                in = reader.readLine();

                run = !in.toUpperCase().equals("EXIT") && !in.toUpperCase().equals("QUIT");
                if (run) {
                    ui.parseCommand(in);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
