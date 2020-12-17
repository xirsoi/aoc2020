package com.advent.DayFive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Problem {
    public static void main(String[] args) throws IOException {
        List<String> rawPasses = Files.readAllLines(Path.of("out/production/AdventOfCode/com/advent/DayFive/input.txt"));
        ArrayList<BoardingPass> passes = new ArrayList<>();

        for (String s:
             rawPasses) {
            passes.add(new BoardingPass(s));
        }

        List<Integer> seatNumbers = passes.stream().map(BoardingPass::getSeatNumber).collect(Collectors.toList());
        Collections.sort(seatNumbers);

        int largest = seatNumbers.get(seatNumbers.size() - 1);

        System.out.println("Largest Seat Number: " + largest);

        List<BoardingPass> prunedList = passes.stream().filter(p -> p.RowNumber < 127 && p.RowNumber > 0).collect(Collectors.toList());

        seatNumbers = prunedList.stream().map(BoardingPass::getSeatNumber).collect(Collectors.toList());
        Collections.sort(seatNumbers);

        int prev, root;
        prev = root = 0;

        for (Integer seat: seatNumbers) {
            prev = root;
            root = seat;

            if (root - prev == 2) break;
        }

        System.out.println("My Seat Number is: " + (root - 1));
    }
}
