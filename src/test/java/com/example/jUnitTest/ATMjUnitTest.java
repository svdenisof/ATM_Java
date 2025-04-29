package com.example.jUnitTest;

import com.example.components.ATM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ATMjUnitTest {

    HashMap<Integer, Integer> result, expected;
    ATM atm;

    /**
     * Money unit in ATM
     */
    HashMap<Integer, Integer> revolver = new HashMap<>();

    @BeforeEach
    public void beforeEach()
    {
        uploadMoneyBlocks();

        expected = new HashMap<>();
        atm = new ATM();
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

        Integer money = revolver
                .entrySet()
                .stream()
                .map(e -> (e.getKey() * e.getValue()))
                .toList()
                .stream()
                .reduce(0, Integer::sum);

        result = atm.getMoney(money);

        Assertions.assertEquals(expected, result);

    }

    @Test
    public void NotValidCountOfMoneyToTakeTestCase(){
        Integer money = 104;

        result = atm.getMoney(money);

        Assertions.assertNull(result);
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
}
