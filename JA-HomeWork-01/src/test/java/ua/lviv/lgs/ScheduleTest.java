package ua.lviv.lgs;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class ScheduleTest {

    private static Schedule scheduleManual = new Schedule();
    private static Schedule schedule = new Schedule();
//    static Seance s2 = new Seance(new Movie("Third test", new Time(0, 30)), new Time(13, 0));;

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("FAILED -- >" + description.getMethodName());
        }

        @Override
        protected void succeeded(Description description) {
            System.out.println("SUCCEED -->" + description.getMethodName());
        }
    };

    @Before
    public  void createSchedule(){
        schedule = new Schedule();
        scheduleManual = new Schedule();
        Seance s = new Seance(new Movie("First test", new Time(0, 30)), new Time(10, 00));
        Seance s1 = new Seance(new Movie("Second test", new Time(0, 30)), new Time(12, 0));


        scheduleManual.getSeances().add(s);
        scheduleManual.getSeances().add(s1);
        schedule.getSeances().add(s);
        schedule.getSeances().add(s1);

    }

//    @After
//    public  void printSchedule(){
////        System.out.println("Result");
////        System.out.println("Schedule by manual operations");
////        System.out.println(scheduleManual);
////
////        System.out.println("Schedule by tested void operations");
////        System.out.println(schedule);
//
//
//    }

    @Test
    public void seanceShouldBeAdded(){
        Seance seanceToAdd = new Seance(new Movie("Third test", new Time(0, 30)), new Time(13, 0));
        scheduleManual.getSeances().add(seanceToAdd);

        schedule.addSeance(seanceToAdd);

                Assert.assertEquals(scheduleManual,schedule);
    }


    @Test ()
    public void shoulNotAddSeanceStartBeforeEndInsideEngagedTime(){
        Seance seanceToAdd = new Seance(new Movie("Third test", new Time(0, 30)), new Time(9, 50));
        scheduleManual.getSeances().add(seanceToAdd);
//        System.out.println(schedule);
        schedule.addSeance(seanceToAdd);

//        System.out.println(schedule1);
        Assert.assertNotEquals(scheduleManual,schedule);

    }

    @Test ()
    public void shoulNotAddSeanceStartInsideEndInsideEngagedTime(){
        Seance seanceToAdd = new Seance(new Movie("Third test", new Time(0, 15)), new Time(10, 05));
        scheduleManual.getSeances().add(seanceToAdd);
//        System.out.println(schedule);
        schedule.addSeance(seanceToAdd);

//        System.out.println(schedule1);
        Assert.assertNotEquals(scheduleManual,schedule);

    }

    @Test ()
    public void shoulNotAddSeanceStartInsideEndAfterEngagedTime(){
        Seance seanceToAdd = new Seance(new Movie("Third test", new Time(0, 40)), new Time(10, 15));
        scheduleManual.getSeances().add(seanceToAdd);
//        System.out.println(schedule);
        schedule.addSeance(seanceToAdd);

//        System.out.println(schedule1);
        Assert.assertNotEquals(scheduleManual,schedule);

    }

    @Test ()
    public void shoulNotAddSeanceStartBeforeEndAfterEngagedTime(){
        Seance seanceToAdd = new Seance(new Movie("Third test", new Time(0, 50)), new Time(9, 50));
        scheduleManual.getSeances().add(seanceToAdd);
//        System.out.println(schedule);
        schedule.addSeance(seanceToAdd);

//        System.out.println(schedule1);
        Assert.assertNotEquals(scheduleManual,schedule);

    }

    @Test
    public void CheckIsSeanceSeanceStartBeforeEndInsideEngagedTime(){

        Seance seanceToCheck = new Seance(new Movie("Third test", new Time(0, 15)), new Time(9, 50));

        Assert.assertTrue( schedule.isSeance(seanceToCheck));
    }

    @Test
    public void CheckIsSeanceSeanceStartInsideEndAfterEngagedTime(){

        Seance seanceToCheck = new Seance(new Movie("Third test", new Time(0, 40)), new Time(10, 10));

        Assert.assertTrue( schedule.isSeance(seanceToCheck));
    }

    @Test
    public void CheckIsSeanceSeanceStartBeforeEndAfterEngagedTime(){

        Seance seanceToCheck = new Seance(new Movie("Third test", new Time(0, 45)), new Time(9, 50));

        Assert.assertTrue( schedule.isSeance(seanceToCheck));
    }

    @Test
    public void CheckIsSeanceSeanceStartInsideEndInsideEngagedTime(){

        Seance seanceToCheck = new Seance(new Movie("Third test", new Time(0, 15)), new Time(10, 05));

        Assert.assertTrue( schedule.isSeance(seanceToCheck));
    }

    @Test
    public void shouldRemoveSeanceByStartTime(){

        schedule.removeSeance(new Time(12,00));

        Seance s1 = new Seance(new Movie("Second test", new Time(0, 30)), new Time(12, 0));
        scheduleManual.getSeances().remove(s1);

        Assert.assertEquals(scheduleManual,schedule);

    }

    @Test
    public void shouldRemoveSeanceByMove(){

        schedule.removeSeance(new Movie("Second test", new Time(0, 30)));

        Seance s1 = new Seance(new Movie("Second test", new Time(0, 30)), new Time(12, 0));
        scheduleManual.getSeances().remove(s1);

        Assert.assertEquals(scheduleManual,schedule);

    }








}
