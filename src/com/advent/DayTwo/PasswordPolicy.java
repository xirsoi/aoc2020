package com.advent.DayTwo;

public class PasswordPolicy {
    private final int _min, _max;
    private final char _character;

    public PasswordPolicy(String input) {
        String[] p1 = input.split(" ")[0].split("-");
        _character = input.split(" ")[1].charAt(0);

        _min = Integer.parseInt(p1[0]);

        if (p1.length == 1) _max = _min;
        else _max = Integer.parseInt(p1[1]);
    }

    public boolean Validate(String password) {
        return (password.charAt(_min) == _character ^ password.charAt(_max) == _character);
    }
}
