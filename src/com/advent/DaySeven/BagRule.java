package com.advent.DaySeven;

import java.util.HashMap;

public class BagRule {
    public final String BagType;
    public final HashMap<String, Integer> Contents;

    public BagRule(String ruleDefinition) {
        String[] parts = ruleDefinition.split("bags contain ");

        BagType = parts[0].trim();
        Contents = new HashMap<>();

        if (parts[1].contains("no other bags.")) return;

        String[] contents = parts[1].split(", ");
        for (String bagDef: contents) {
            String[] b = bagDef.split(" ", 2);
            int count = Integer.parseInt(b[0]);
            String bag = b[1]
                    .replace(".", "")
                    .replace("bags", "")
                    .replace("bag", "")
                    .trim();
            Contents.put(bag, count);
        }
    }
}
