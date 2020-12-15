package com.advent.DayThree;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Files;
import java.util.List;

public class Problem {
    public static void main(String args[]) throws IOException {
        int[] deltasX = { 1, 3, 5, 7, 1 };
        int[] deltasY = { 1, 1, 1, 1, 2 };
        List<String> input = Files.readAllLines(Path.of("out/production/AdventOfCode/com/advent/DayThree/input.txt"));

        char[][] map = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            map[i] = input.get(i).toCharArray();
        }

        long product = 1;
        for (int i = 0; i < deltasX.length; i++) {
            product *= CountTrees(map, deltasX[i], deltasY[i]);
        }

        System.out.println(product);
    }

    public static int CountTrees(char[][] map, int dX, int dY) {
        int x, y, trees;
        x = y = trees = 0;

        while (y < map.length) {
            x %= map[y].length;

            if (map[y][x] == '#') trees++;

            x += dX;
            y += dY;
        }

        return trees;
    }
}
