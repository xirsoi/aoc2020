package com.advent.DaySix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Problem {
    public static void main(String[] args) throws IOException {
        String[] groups = Files.readString(Path.of("out/production/AdventOfCode/com/advent/DaySix/input.txt")).split("(?m)^\\s*$");

        ArrayList<Integer> answerCounts = new ArrayList<>();

        for (String group : groups) {
            String[] forms = Stream.of(group.split("\\r\\n")).filter(w -> !w.isEmpty()).toArray(String[]::new);
            HashMap<Character, Integer> answers = new HashMap<>();

            for (String form : forms) {
                for (char ans : form.toCharArray())
                    answers.put(ans, answers.getOrDefault(ans, 0) + 1);
            }

            int sumOfUnanimousAnswers = 0;
            for (Character c : answers.keySet())
                if (answers.get(c) == forms.length) sumOfUnanimousAnswers++;

            answerCounts.add(sumOfUnanimousAnswers);
        }

        int sum = answerCounts.stream().mapToInt(ac -> ac).sum();

        System.out.println("Total sum of unanimous answers is " + sum);
    }
}
