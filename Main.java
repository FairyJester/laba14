package com.company;

import java.io.*;
import java.util.Scanner;

class Function implements Serializable {
    double x, y;

    double getY() {
        y = this.x - Math.sin(this.x);
        return y;
    }
}

public class Main {
    public static void main(String[] args) {
        String txt;
        Scanner in = new Scanner(System.in);
        Function func = new Function();
        System.out.print("Enter value: ");
        while (true) {
            txt = in.nextLine();
            try {
                double x = Double.parseDouble(txt);
                func.x = x;
                func.getY();
                System.out.println("You can enter one of the following commands: save, upload, check, stop");
            } catch (Exception IOe) {
                if (txt.equalsIgnoreCase("save")) {
                    try (ObjectOutputStream wr = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
                        wr.writeObject(func);
                        System.out.println("Data saved to file");
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                } else if (txt.equalsIgnoreCase("upload")) {
                    try (ObjectInputStream rd = new ObjectInputStream(new FileInputStream("object.txt"))) {
                        func = (Function) rd.readObject();
                        System.out.println("Upload successful");
                    } catch (Exception ex) {
                        ex.getMessage();
                    }
                } else if (txt.equalsIgnoreCase("check")) {
                    System.out.printf("x: %s\ny: %s\n",func.x, func.y);
                } else if (txt.equalsIgnoreCase("stop")){
                    break;
                }
                else {
                    System.out.println("Such a command does not exist");
                }
                System.out.println("You can enter one of the following commands: save, upload, check, stop");
            }
        }
    }
}