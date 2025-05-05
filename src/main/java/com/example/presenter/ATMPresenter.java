package com.example.presenter;

import java.util.HashMap;
import java.util.InputMismatchException;

public class ATMPresenter {

    public static String convertMapToMoneyString(HashMap<Integer, Integer> toTake){

        StringBuffer sb = new StringBuffer("К выдаче:\n");
        if(toTake == null)
            throw new InputMismatchException("Не корректная сумма к выдаче");

        for(var entity: toTake.entrySet())
        {
            sb.append(entity.getKey()).append(" в количестве ").append(entity.getValue()).append("\n");
        }
        return sb.toString();
    }
}
