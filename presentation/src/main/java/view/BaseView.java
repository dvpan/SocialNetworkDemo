package view;

import tool.exception.InputCanceledException;

import java.util.Scanner;


public abstract class BaseView {
    private Scanner scanner = new Scanner(System.in);

    abstract void render();

    BaseView(){}

    void printTitle(String string){
        System.out.println("\n### " + string + " ###");
    }

    void println(String string){
        System.out.println(string);
    }

    void print(String string){
        System.out.print(string);
    }

    void printHelper() {
        println("For escape press '\\' button.");
    }

    void alert(){
        System.out.print("--==ALERT==--");
    }

    String getLine() throws InputCanceledException {
        String line = scanner.nextLine().trim();
        if(line.equals("\\")) throw new InputCanceledException();
        if(line.equals("")) {
            print("Error! Please try again!");
            line = getLine();
        }
        return line;
    }

    public int getInt() {
        String line = scanner.nextLine();
        if(line.trim().equals("")) return -1;
        return Integer.parseInt(line);
    }

    int printMenuAndWait(String... items) {
        for(int i = 0; i < items.length-1; i++)
            println((i+1) + " - " + items[i]);

        println("0 - " + items[items.length-1]+"\n");

        print("Your choice: ");
        return inputMenuListener(items.length);
    }

    private int inputMenuListener(int length) {
        int pos = getInt();
        if(pos < 0 || pos > length) {
            println("Error! Please try again!");
            pos = inputMenuListener(length);
        }
        return pos;
    }
}
