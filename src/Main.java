/*
 * Copyright (c) 2020. AmirMohammad Ramzani
 */

import java.util.Scanner;

public class Main {

    enum RColor {
        BLACK,
        BROWN,
        RED,
        ORANGE,
        YELLOW,
        GREEN,
        BLUE,
        VIOLET,
        GREY,
        WHITE
    }

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        printMenu();

    }

    private static void printMenu() throws Exception {
        System.out.println(" COLORS AVAILABLE:\n" +
                "\u001B[30mBLACK\u001B[0m, BROWN, \u001B[31mRED\u001B[0m, " +
                "ORANGE, \u001B[33mYELLOW\u001B[0m," +
                "\u001B[32mGREEN\u001B[0m, \u001B[34mBLUE\u001B[0m," +
                "\u001B[35mVIOLET\u001B[0m, GREY, and \u001B[37mWHITE\u001B[0m, " +
                "GOLD and SILVER only for multiplier and tolerance");
        System.out.println("Enter the number of colors in the resistor رنگ های روی مقاومت را انتخاب کنید.\n3, 4, 5, 6");


        int numberOfColors = scanner.nextInt();
        int[] colors;
        if (numberOfColors == 3) {
            colors = nameToNumber(2);
        } else if (numberOfColors == 4) {
            colors = nameToNumber(2);
        } else if (numberOfColors == 5) {
            colors = nameToNumber(3);
        } else if (numberOfColors == 6) {
            colors = nameToNumber(3);
        } else {
            throw new Exception("Enter a number between 3 and 6");
        }
        System.out.println("Enter color or None");
        System.out.println("Multiplier");
        String multiplierColor = scanner.next().toUpperCase();


        String tolerance = "None";
        if (numberOfColors != 3) {
            System.out.println("Tolerance");
            tolerance = scanner.next().toUpperCase();
        }

        String temp = "None";
        if (numberOfColors == 6) {
            System.out.println("Temperature Coefficient");
            temp = scanner.next().toUpperCase();
        }


        calculateColors(colors, multiplierColor, tolerance, temp);
    }

    private static int[] nameToNumber(int numberColors) {
        int[] colors = new int[numberColors];
        for (int i = 0; i < numberColors; i++) {
            System.out.println("Enter " + (i + 1) + " band color: ");
            int colorNumber = convertColor();
            colors[i] = colorNumber;

        }
        return colors;
    }
    private static int convertColor() {
        String temp = scanner.next();
        int res;
        try {
            RColor colorItem = RColor.valueOf(temp.toUpperCase());
            res = colorItem.ordinal();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
            res = convertColor();
        }
        return res;
    }

    private static String multiplierConverter(String colorName) {
        RColor colorItem = RColor.valueOf(colorName.toUpperCase());
        int x = colorItem.ordinal();
        return "0".repeat(x);

    }

    private static String toleranceConvertor(String tolColor) {
        switch (tolColor) {
            case "BROWN":
                return " ±1%";
            case "RED":
                return " ±2%";
            case "ORANGE":
                return " ±0.05%";
            case "YELLOW":
                return " ±0.02%";
            case "GREEN":
                return " ±0.5%";
            case "BLUE":
                return " ±0.25%";
            case "VIOLET":
                return " ±0.1%";
            case "GREY":
                return " ±0.01%";
            case "GOLD":
                return " ±5%";
            case "SILVER":
                return " ±10%";
            default:
                return "";

        }
    }

    private static String tempTable(String tempColor) {
        switch (tempColor) {
            case "BROWN":
                return " 100 ppm/K";
            case "RED":
                return " 50 ppm/K";
            case "ORANGE":
                return " 15 ppm/K";
            case "YELLOW":
                return " 25 ppm/K";
            case "GREEN":
                return " 20 ppm/K";
            case "BLUE":
                return " 10 ppm/K";
            case "VIOLET":
                return " 5 ppm/K";
            case "GREY":
                return " 1 ppm/K";
            case "BLACK":
                return " 250 ppm/K";

            default:
                return " ";

        }

    }

    private static void calculateColors(int[] colors, String multiplier, String tolerance, String tempCoefficient) {
        StringBuilder number = new StringBuilder();
        for (int i : colors) {
            number.append(i);

        }
        if (multiplier.equals("GOLD")) {
            int offset = number.length() - 1;

            number.insert(offset, ".");
        } else if (multiplier.equals("SILVER")) {
            int offset = number.length() - 2;
            number.insert(offset, ".");

        } else {
            number.append(multiplierConverter(multiplier));
        }
        number.append(toleranceConvertor(tolerance.toUpperCase()));
        System.out.println(number.toString());

    }
}

