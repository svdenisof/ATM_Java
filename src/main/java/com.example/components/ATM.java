package com.example.components;

import java.util.*;

public class ATM {

    HashMap<Integer, Integer> revolver = new HashMap<>();
    List<Integer> moneyMap = new ArrayList<>();

    public ATM()
    {
        setRevolverBox();
    }

    public HashMap<Integer, Integer> getMoney(Integer toTake)
    {
        HashMap<Integer, Integer> result = new HashMap<>();

        if(toTake == null)
            return null;

        final Integer MIN_VALUE = getMinimumMoneyItem();

        if(toTake%MIN_VALUE != 0)
        {
            return null;
        }

        Iterator<Map.Entry<Integer, Integer>> it = revolver.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer,Integer> entry = it.next();
            int key = entry.getKey();
            int value = entry.getValue();

            int sum = key * value;
            if(sum <= toTake)
            {
                toTake -= sum;
                result.put(key, value);
                it.remove();

                System.out.println("Нам нужно на выдачу из: " + key + " количестве: " + value);
            }
        }
        System.out.println("Сумма к выдаче: " + toTake);
        System.out.println("Осталось: " + revolver);
        System.out.println("На выдачу: " + result);

        return result;
    }

    private void setRevolverBox()
    {

        revolver.put(50, 200);
        revolver.put(100, 150);
        revolver.put(500, 100);
        revolver.put(1000, 100);
        revolver.put(5000, 100);

        List<Integer> moneyMap = new ArrayList<>(revolver.keySet());

        setMoneyMap(moneyMap);
    }

    private void setMoneyMap(List<Integer> moneyMap)
    {

        this.moneyMap = moneyMap;
    }

    private Integer getMinimumMoneyItem()
    {
        return moneyMap.get(0);
    }
}
