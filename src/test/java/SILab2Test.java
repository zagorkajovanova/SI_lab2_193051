import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    private List<Time> createList(Time...times){
        return new ArrayList<>(Arrays.asList(times));
    }

    @Test
    void multipleCondition(){
        RuntimeException ex;

        //test slucai za prviot if
        Time time1 = new Time(-20, 5,5); //T || X
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(time1)));
        assertTrue(ex.getMessage().contains("The hours are smaller than the minimum"));

        Time time2 = new Time(25, 5, 5); //F || T
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(time2)));
        assertTrue(ex.getMessage().contains("The hours are grater than the maximum"));

        Time time3 = new Time(23,5,5); //F || F
        List<Integer> result = Arrays.asList(83105);
        assertEquals(result,SILab2.function(createList(time3)));

        //test slucai za vtoriot if
        Time time4 = new Time(20, -5,5); //T || X
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(time4)));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));

        Time time5 = new Time(20, 65, 5); //F || T
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(time5)));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));

        Time time6 = new Time(20,5,5); //F || F
        List<Integer> result1 = Arrays.asList(72305);
        assertEquals(result1,SILab2.function(createList(time6)));

        //test slucai za tretiot if
        Time time7 = new Time(20, 5,-5); //F && X
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(time7)));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        Time time8 = new Time(20, 5, 65); //T && F
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(time8)));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        Time time9 = new Time(20,5,5); //T && T
        List<Integer> result2 = Arrays.asList(72305);
        assertEquals(result1,SILab2.function(createList(time6)));

        //test slucai za cetvrtiot if
        Time time10 = new Time(24, 0, 5); //T && T && F
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(time10)));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

        Time time11 = new Time(24, 5, 0); //T && F && X
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(time11)));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

        Time time12 = new Time(24, 0, 0);//T && T && T
        List<Integer> result3 = Arrays.asList(86400);
        assertEquals(result3,SILab2.function(createList(time12)));
    }

    @Test
    void everyBranch(){
        List<Integer> result = new ArrayList<>();

        Time time1 = new Time(23, 5, 5);
        Time time2 = new Time(24, 0, 0);
        result = Arrays.asList(83105,86400);
        assertEquals(result, SILab2.function(createList(time1,time2)));

        Time time3 = new Time(-20,5,5);
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(time3)));
        assertTrue(ex.getMessage().contains("The hours are smaller than the minimum"));

        Time time4 = new Time(25,5,5);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(time4)));
        assertTrue(ex.getMessage().contains("The hours are grater than the maximum"));

        Time time5 = new Time(23, -5,5);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(time5)));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));

        Time time6 = new Time(23,5,-5);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(time6)));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        Time time7 = new Time(24,5,5);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(createList(time7)));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));
    }
}