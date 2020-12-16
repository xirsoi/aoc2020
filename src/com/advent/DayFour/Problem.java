package com.advent.DayFour;

import javax.xml.transform.Result;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Problem {
    public static void main(String[] args) throws IOException {
        String[] rawPassports = Files.readString(Path.of("out/production/AdventOfCode/com/advent/DayFour/input.txt")).split("(?m)^\\s*$");

        List<Passport> passports = ParsePassports(rawPassports);

        int numValid = 0;
        HashMap<Passport.ResultCode, Integer> report = new HashMap<>();

        for (Passport p : passports) {
            Passport.ResultCode result = p.Validate(Passport.ValidationScheme.SouthPole);
            if (result == Passport.ResultCode.Passed) numValid++;
            report.put(result, report.getOrDefault(result, 0) + 1);
        }

        System.out.println(passports.size() + " passports were found, of which " + numValid + " are valid.");
        System.out.println("Full results report follows: ");
        for (Passport.ResultCode rc: Passport.ResultCode.values()) {
            System.out.println("\t" + rc + " " + report.getOrDefault(rc, 0));
        }
    }

    public static List<Passport> ParsePassports(String[] raw) {
        List<Passport> passports = new ArrayList<Passport>();

        for (String r : raw) {
            passports.add(ParsePassport(r));
        }

        return passports;
    }
    public static Passport ParsePassport(String raw) {
        raw = raw.trim().replace(' ', '\n');
        String[] properties = raw.split("\\r?\\n");

        Passport p = new Passport();

        for (String prop : properties) {
            String p1 = prop.split(":")[0];
            String p2 = prop.split(":")[1];

            switch (p1) {
                case "byr":
                    p.BYR = p2;
                    break;
                case "iyr":
                    p.IYR = p2;
                    break;
                case "eyr":
                    p.EYR = p2;
                    break;
                case "hgt":
                    p.HGT = p2;
                    break;
                case "hcl":
                    p.HCL = p2;
                    break;
                case "ecl":
                    p.ECL = p2;
                    break;
                case "pid":
                    p.PID = p2;
                    break;
                case "cid":
                    p.CID = p2;
                    break;
                default:
                    break;
            }
        }

        return p;
    }
}
