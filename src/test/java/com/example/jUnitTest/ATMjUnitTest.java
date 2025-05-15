package com.example.jUnitTest;

import com.example.models.ATMEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Create by @me
 */
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
        uploadMoneyBlocks(revolver);
        expected = new HashMap<>();
        atm = new ATMEntity();
    }

    @Test
    public void nullValueTestCase()
    {
        Integer money = null;
        expected = null;

        result = atm.getMoney(money);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void moreThenAllMoneyGetFromTestCase_ThenThrowUnsupportedOperationException()
    {
        Integer money = getMaxValueOfMoney() * 2;
        UnsupportedOperationException exception = Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            result = atm.getMoney(money);
        });

        Assertions.assertEquals("Вы не можете снять эту сумму.", exception.getMessage());
    }

    @Test
    public void maxValueMoneyGetFromTestCase()
    {
        expected = revolver;

        Integer money = getMaxValueOfMoney();

        result = atm.getMoney(money);

        Assertions.assertEquals(expected, result);

    }

    @Test
    public void halfValueMoneyGetFromTestCase()
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
    public void threeQuartersMoneyGetFromTestCase(){
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
    public void oneQuartersMoneyGetFromTestCase(){
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
    public void notValidCountOfMoneyToTakeTestCase(){
        Integer money = 104;

        result = atm.getMoney(money);

        Assertions.assertNull(result);
    }

    @Test
    public void lessMoneyToTake(){
        Integer money = 900;
        //{500=1, 100=4}
        expected.put(100, 4);
        expected.put(500, 1);

        result = atm.getMoney(money);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void customRevolverUpload(){

        HashMap<Integer, Integer> tmpRevolver = new HashMap<>();
        tmpRevolver.put(200, 400);
        tmpRevolver.put(500, 50);
        tmpRevolver.put(5000, 1000);

        atm = new ATMEntity(tmpRevolver);
        Integer money = getMaxValueOfMoney(tmpRevolver);

        result = atm.getMoney(money);

        expected.put(200, 400);
        expected.put(500, 50);
        expected.put(5000, 1000);

        Assertions.assertEquals(expected, result);
    }

    @AfterEach
    public void afterEach()
    {
        System.out.println(result);
    }

    private void uploadMoneyBlocks(HashMap<Integer, Integer> uploadRevolver)
    {
        if(uploadRevolver.isEmpty()){
            revolver.put(50, 200);
            revolver.put(100, 150);
            revolver.put(500, 100);
            revolver.put(1000, 100);
            revolver.put(5000, 100);
        }
        else {
            revolver = uploadRevolver;
        }
    }

    private Integer getMaxValueOfMoney(){

        return getMaxValueOfMoney(new HashMap<>());
    }

    private Integer getMaxValueOfMoney(HashMap<Integer, Integer> uploadRevolver)
    {
        if(!uploadRevolver.isEmpty())
            revolver = uploadRevolver;

        return revolver
                .entrySet()
                .stream()
                .mapToInt(e -> (e.getKey() * e.getValue()))
                .sum();

    }
}
