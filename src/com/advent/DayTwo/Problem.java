package com.advent.DayTwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Problem {
    public static String[] Passwords;
    public static PasswordPolicy[] Policies;

    public static void main(String[] args) throws IOException {
        ParseInput(Path.of("out/production/AdventOfCode/com/advent/DayTwo/input.txt"));

        int count = 0;
        for (int i = 0; i < Passwords.length; i++) {
            if (Policies[i].Validate(Passwords[i]))
                count++;
        }

        System.out.println(count);
    }

    public static void ParseInput(Path p) throws IOException {
        List<String> inputs = Files.readAllLines(p);

        Passwords = new String[inputs.size()];
        Policies = new PasswordPolicy[inputs.size()];

        for (int i = 0; i < inputs.size(); i++) {
            String[] parts = inputs.get(i).split(":");

            Passwords[i] = parts[1];
            Policies[i] = new PasswordPolicy(parts[0]);
        }
    }
}
