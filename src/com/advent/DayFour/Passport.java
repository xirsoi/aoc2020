package com.advent.DayFour;

import java.util.Arrays;
import java.util.List;

public class Passport {
    public String BYR;
    public String IYR;
    public String EYR;
    public String HGT;
    public String HCL;
    public String ECL;
    public String PID;
    public String CID;

    public Passport() {
        BYR = IYR = EYR = HGT = HCL = ECL = PID = CID = "";
    }
    public Passport(String byr, String iyr, String eyr, String hgt, String hcl, String ecl, String pid, String cid) {
        BYR = byr;
        IYR = iyr;
        EYR = eyr;
        HGT = hgt;
        HCL = hcl;
        ECL = ecl;
        PID = pid;
        CID = cid;
    }

    public enum ValidationScheme {
        NorthPole, SouthPole
    }

    public enum ResultCode {
        Passed, MissingData, BadBirthYear, BadIssueYear, BadExpiry,
        BadHeight, BadHairColor, BadEyeColor, BadPassportID, Exception
    }

    public ResultCode Validate(ValidationScheme scheme) {
        ResultCode result = ResultCode.Passed;

        switch (scheme) {
            case NorthPole:
                result = _ValidateNorthPole();
                break;
            case SouthPole:
                result = _ValidateSouthPole();
                break;
        }

        return result;
    }

    private ResultCode _ValidateNorthPole() {
        if (BYR.isBlank()
                || IYR.isBlank()
                || EYR.isBlank()
                || HGT.isBlank()
                || HCL.isBlank()
                || ECL.isBlank()
                || PID.isBlank())
            return ResultCode.MissingData;
        else return ResultCode.Passed;
    }

    private ResultCode _ValidateSouthPole() {
        if (_ValidateNorthPole() == ResultCode.MissingData) return ResultCode.MissingData;

        // we know we have all the required fields at this point, time to validate the contents!
        try {
            int bYear = Integer.parseInt(BYR);
            if (bYear < 1920 || bYear > 2002) return ResultCode.BadBirthYear;

            int iYear = Integer.parseInt(IYR);
            if (iYear < 2010 || iYear > 2020) return ResultCode.BadIssueYear;

            int eYear = Integer.parseInt(EYR);
            if (eYear < 2020 || eYear > 2030) return ResultCode.BadExpiry;

            String h = HGT.replaceAll("[^\\d.]", "");
            int height = Integer.parseInt(h);
            if (HGT.contains("in") && (height < 59 || height > 76)) return ResultCode.BadHeight;
            else if (HGT.contains("cm") && (height < 150 || height > 193)) return ResultCode.BadHeight;
            else if (!HGT.contains("cm") && !HGT.contains("in")) return ResultCode.BadHeight;

            if (!HCL.matches("#[0-9a-f]{6}")) return ResultCode.BadHairColor;

            List<String> eyeColors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
            if (!eyeColors.contains(ECL)) return ResultCode.BadEyeColor;

            if (!PID.matches("^[0-9]{9}$")) return ResultCode.BadPassportID;
        } catch (Exception e) {
            // don't care what kind of exception we get, it means the passport is invalid!
            return ResultCode.Exception;
        }

        return ResultCode.Passed;
    }
}
