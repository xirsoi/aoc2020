package com.advent.DayOne;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Problem {
    public static void main(String[] args) throws IOException {
        List<String> inputs = Files.readAllLines(Path.of("out/production/AdventOfCode/com/advent/DayOne/input.txt"));

        int product = -1;

        for (int i = 0; i < (inputs.size() - 2); i++) {
            int x = Integer.parseInt(inputs.get(i));
            for (int j = (i + 1); j < (inputs.size() - 1); j++) {
                int y = Integer.parseInt(inputs.get(j));
                for (int k = (j + 1); k < inputs.size(); k++) {
                    int z = Integer.parseInt((inputs.get(k)));
                    if (x+y+z == 2020) {
                        product = x * y * z;
                        break;
                    }
                }
            }
            if (product >= 0) break;
        }

        System.out.println(product);
    }
}
