package com.advent.DaySeven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Problem {
    public static void main(String[] args) throws IOException {
        String[] rules = Files.readString(Path.of("out/production/AdventOfCode/com/advent/DaySeven/input.txt")).split("\\r\\n");
        ArrayList<BagRule> bagRules = new ArrayList<>();

        for (String r: rules) {
            bagRules.add(new BagRule(r));
        }

        int count = 0;
        // gotta initialize it to something
        BagRule shinyGold = new BagRule("shiny gold bags contain no other bags.");
        for (BagRule b : bagRules) {
            if (b.BagType.equals("shiny gold")) shinyGold = b;
            if (b.Contents.containsKey("shiny gold")) count++;
        }

        System.out.println("There are " + count + " types that can hold 'shiny gold' bags directly.");

        var bagsThatCanContainMyBag = GetAllBagsThatCanContainBag("shiny gold", bagRules);

        System.out.println("There are " + bagsThatCanContainMyBag.size() + " types of bag that can ultimately hold a 'shiny gold' bag.");

        var bagsThatMustBeInMyBag = GetNumberOfBagsThatMustBeInThisBag(shinyGold, bagRules);

        System.out.println("There must ultimately be " + bagsThatMustBeInMyBag + " bags in a 'shiny gold' bag.");
    }

    public static HashMap<BagRule, Boolean> GetAllBagsThatCanContainBag(String bagType, ArrayList<BagRule> bagRules) {
        HashMap<BagRule, Boolean> bagsThatCanContainBag = new HashMap<>();

        for (BagRule br : bagRules) {
            if (br.Contents.containsKey(bagType)) {
                bagsThatCanContainBag.put(br, true);
                bagsThatCanContainBag.putAll(GetAllBagsThatCanContainBag(br.BagType, bagRules));
            }
        }

        return bagsThatCanContainBag;
    }

    public static int GetNumberOfBagsThatMustBeInThisBag(BagRule bagType, ArrayList<BagRule> bagRules) {
        int numOfBagsInThisBag = 0;

        for (BagRule br : bagRules) {
            if (bagType.Contents.containsKey(br.BagType)) {
                numOfBagsInThisBag += bagType.Contents.get(br.BagType);
                numOfBagsInThisBag += bagType.Contents.get(br.BagType) * GetNumberOfBagsThatMustBeInThisBag(br, bagRules);
            }
        }

        return numOfBagsInThisBag;
    }
}
