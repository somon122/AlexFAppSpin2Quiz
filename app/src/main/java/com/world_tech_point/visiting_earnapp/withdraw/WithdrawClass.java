package com.world_tech_point.visiting_earnapp.withdraw;

public class WithdrawClass {

    private String number, amount,status,pMethod;

    public WithdrawClass() {}

    public WithdrawClass(String number, String amount, String status , String pMethod) {
        this.number = number;
        this.amount = amount;
        this.status = status;
        this.pMethod = pMethod;
    }

    public String getNumber() {
        return number;
    }

    public String getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getpMethod() {
        return pMethod;
    }
}
