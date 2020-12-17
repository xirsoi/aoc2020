package com.advent.DayFive;

public class BoardingPass {
    public final String PassCode;
    public int getSeatNumber() {
        return (RowNumber * 8) + ColumnNumber;
    }
    public final int RowNumber;
    public final int ColumnNumber;

    public BoardingPass(String passCode) {
        PassCode = passCode;

        RowNumber = _calcRowNumber(PassCode.toCharArray(), 0, 0, 127);
        ColumnNumber = _calcColNumber(PassCode.toCharArray(), 7, 0, 7);
    }

    private int _calcRowNumber(char[] seq, int index, int min, int max) {
        if (min == max) return min;

        if (seq[index] == 'F') {
            int delta = Math.round(((float)max - (float)min) / 2);
            max -= delta;
        } else if (seq[index] == 'B') {
            int delta = Math.round(((float)max - (float)min) / 2);
            min += delta;
        }
        index++;
        return _calcRowNumber(seq, index, min, max);
    }

    private int _calcColNumber(char[] seq, int index, int min, int max) {
        if (min == max) return min;

        if (seq[index] == 'L') {
            int delta = Math.round(((float)max - (float)min) / 2);
            max -= delta;
        } else if (seq[index] == 'R') {
            int delta = Math.round(((float)max - (float)min) / 2);
            min += delta;
        }
        index++;
        return _calcColNumber(seq, index, min, max);

    }
}
