package com.example.models;

import java.util.*;

/**
 * Create by @me
 */
public class ATMEntity {

    private HashMap<Integer, Integer> revolver = new HashMap<>();
    List<Integer> moneyMap = new ArrayList<>();

    public ATMEntity()
    {
        setRevolverBox();
        setMoneyMap();
    }

    public ATMEntity(HashMap<Integer, Integer> uploadRevolver)
    {
        if(uploadRevolver.isEmpty()) {
            setRevolverBox();
        }
        else {
            revolver = uploadRevolver;
        }
        setMoneyMap();
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
            int key = moneyMap.get(size - 1);
            int value = revolver.get(key);

            int sum = key * value;

            if(toTake >= sum && toTake%key == 0 || toTake > sum)
            {
                toTake -= sum;
                result.put(key, value);
                revolver.remove(key);
            }
            else if(toTake < sum && toTake%key == 0){

                result.put(key, toTake/key);
                revolver.put(key, revolver.getOrDefault(key, 0) - toTake/key);
                toTake = 0;
            }
            else {
               int tmp = toTake/key;
               if(tmp > 0)
               {
                   sum = key*tmp;
                   toTake -= sum;
                   result.put(key, tmp);
                   revolver.put(key, revolver.getOrDefault(key, 0) - tmp);
               }
            }

            if(toTake == 0){
                break;
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
        setMoneyMap();
    }

    private void setMoneyMap()
    {
        this.moneyMap = new ArrayList<>(revolver.keySet());
    }

    private Integer getMinimumMoneyItem()
    {
        return moneyMap.get(0);
    }
}
