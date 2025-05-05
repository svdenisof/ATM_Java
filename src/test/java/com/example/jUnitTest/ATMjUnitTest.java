package com.example.jUnitTest;

import com.example.models.ATMEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ATMjUnitTest {

    HashMap<Integer, Integer> result, expected;
    ATMEntity atm;

    /**
     * Money unit in ATM
     */
    HashMap<Integer, Integer> revolver = new HashMap<>();

    @BeforeEach
    public void beforeEach()
    {
        uploadMoneyBlocks();

        expected = new HashMap<>();
        atm = new ATMEntity();
    }

    @Test
    public void NullValueTestCase()
    {
        Integer money = null;
        expected = null;

        result = atm.getMoney(money);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void MaxValueMoneyGetFromTestCase()
    {
        expected = revolver;

        Integer money = getMaxValueOfMoney();

        result = atm.getMoney(money);

        Assertions.assertEquals(expected, result);

    }

    @Test
    public void HalfValueMoneyGetFromTestCase()
    {
        Integer money = getMaxValueOfMoney();
        Integer hafOfMoneyValue = money/2;

        //{500=1, 5000=67, 1000=2}
        expected.put(5000, 67);
        expected.put(500, 1);
        expected.put(1000, 2);

        result = atm.getMoney(hafOfMoneyValue);

        Assertions.assertEquals(expected,result);
    }

    @Test
    public void ThreeQuartersMoneyGetFromTestCase(){
        Integer money = getMaxValueOfMoney();
        int toTake = money/4;

        //{50=1, 100=2, 5000=100, 1000=6}
        expected.put(50, 1);
        expected.put(100, 2);
        expected.put(5000, 100);
        expected.put(1000, 6);

        result = atm.getMoney(toTake*3);
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void OneQuartersMoneyGetFromTestCase(){
        Integer money = getMaxValueOfMoney();
        int toTake = money/4;

        //{50=1, 500=1, 100=2, 5000=33, 1000=3}
        expected.put(50, 1);
        expected.put(500, 1);
        expected.put(100, 2);
        expected.put(5000, 33);
        expected.put(1000, 3);

        result = atm.getMoney(toTake);

        Assertions.assertEquals(expected,result);
    }


    @Test
    public void NotValidCountOfMoneyToTakeTestCase(){
        Integer money = 104;

        result = atm.getMoney(money);

        Assertions.assertNull(result);
    }

    @Test
    public void LessMoneyToTake(){
        Integer money = 900;
        //{500=1, 100=4}
        expected.put(100, 4);
        expected.put(500, 1);

        result = atm.getMoney(money);

        Assertions.assertEquals(expected, result);
    }

    @AfterEach
    public void afterEach()
    {
        System.out.println(result);
    }

    private void uploadMoneyBlocks()
    {
        revolver.put(50, 200);
        revolver.put(100, 150);
        revolver.put(500, 100);
        revolver.put(1000, 100);
        revolver.put(5000, 100);
    }

    private Integer getMaxValueOfMoney()
    {
        return revolver
                .entrySet()
                .stream()
                .map(e -> (e.getKey() * e.getValue()))
                .toList()
                .stream()
                .reduce(0, Integer::sum);

    }
}
