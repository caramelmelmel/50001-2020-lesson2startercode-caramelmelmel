package com.example.norman_lee.myapplication;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //TODO 5.4 Write unit tests to check the ExchangeRate class
    @Test
    public void exchangeRateDefaultRate() {
        String defaultExchangeRate = "2.95000";
        //always remmeber to convert to string for the love of god over here
        assertEquals(defaultExchangeRate, new ExchangeRate().getExchangeRate().toString());
    }

    @Test
    public void exchangeRateOneArg(){
        String exchangeRate = "0.12345";
        assertEquals( exchangeRate,
                new ExchangeRate(exchangeRate)
                        .getExchangeRate().toString());
    }

    //failed the unit test
    /*(@Test
    public void exchangeRateTwoArg(){
        String local = "1.0";
        String foreign = "3.0";
        String res = "0.3333";
        assertEquals(res,new ExchangeRate(local,foreign).getExchangeRate().toString());
    }*/
    //getExchangeRate shall return a BigDecimal object
    @Test
    public void exchangeRateTwoArgBigDecimal(){
        String home = "1.0";
        String foreign = "3.0";
        String result = "0.33333";

        assertEquals( new BigDecimal(result),
                new ExchangeRate(home, foreign)
                        .getExchangeRate() );
    }

    //calculateAmount() return a BigDecimal object
    //check if big decimal by comparing bigdecimal
    /*@Test
    public void calculateAmountBigDecimal(){
        String home = "1.0";
        String foreign = "3.0";
        String amount = "100";
        String result = "33.333";

        assertEquals( new BigDecimal(result),
                new ExchangeRate(home, foreign)
                        .calculateAmount(amount));
    }*/


    //in writing tests, think of ways to "break" your methods
    //for infinite inputs, divide them in logical ways
    // eg. positive vs negative
    @Test
    public void calculateAmountBigDecimalNegative(){
        String home = "1.0";
        String foreign = "3.0";
        String amount = "-100"; //negative
        String result = "-33.33300"; //negative

        assertEquals( new BigDecimal(result),
                new ExchangeRate(home, foreign)
                        .calculateAmount(amount));
    }
    //an empty string passed results in the Number format exception
    @Test(expected = NumberFormatException.class)
    public void whenExceptionisthrownexceptionissatisfied(){
        new ExchangeRate("");
    }

    /*@Test(expected = NumberFormatException.class)
    public void exchangeRateDoubleArgexception{
        new ExchangeRate("abcde","100");
    }*/
    //note about the other part


}