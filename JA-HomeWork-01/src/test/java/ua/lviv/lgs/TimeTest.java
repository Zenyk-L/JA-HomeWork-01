package ua.lviv.lgs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class TimeTest {

    private Time time;

    @Rule
    public TestWatcher testwatcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("FAILED --> " + description.getMethodName());
        }

        @Override
        protected void succeeded(Description description) {
            System.out.println("SUCCEED --> " + description.getMethodName());
        }
    };

    @Test
    public void negativeTime(){
        time = new Time(0, -1);
        System.out.println(time);
        time = new Time(- 1, 0);
        System.out.println(time);
        time = new Time(- 1, -1);
        System.out.println(time);
    }

    @Test
    public void timeShouldConvert(){
        Time time = new Time(01, 61);
        Time timeExpected = new Time(02, 01);

        Assert.assertEquals(time, timeExpected);
    }


    @Test
    public void addTimeTest() {
        Time time = new Time(14,30);
        Time addTime = new Time(1,35);
        Time resultOfAddingTimes = time.addTime(addTime);

        Time timeExpected = new Time(16, 05);

        Assert.assertEquals(timeExpected, resultOfAddingTimes);
    }

    @Test
    public void timeShouldBeInRange(){
        Time startTime = new Time(02, 30);
        Time endTime = new Time(04, 00);

        Time timeInRage = new Time(3, 20);

        Assert.assertTrue(timeInRage.inRange(startTime,endTime));
    }


}
