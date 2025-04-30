package com.example.components;

import java.util.*;

public class ATMCore {

    HashMap<Integer, Integer> revolver = new HashMap<>();
    List<Integer> moneyMap = new ArrayList<>();

    public ATMCore()
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

        int size = moneyMap.size();
        while(size > 0)
        {
            int key = moneyMap.get(size-1);
            int value = revolver.get(key);

            int sum = key * value;

            if(sum <= toTake)
            {
                toTake -= sum;
                result.put(key, value);
                revolver.remove(key);
            }
            else if(sum > toTake)
            {
                toTake -= sum/2;
                result.put(key, value/2);
                revolver.put(key, revolver.getOrDefault(key, 0)/2);
            }
            else{
                // TBD
            }

            size--;
        }

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
