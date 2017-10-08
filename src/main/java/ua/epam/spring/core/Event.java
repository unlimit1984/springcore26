package ua.epam.spring.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Vladimir on 05.10.2017.
 */
public class Event {
    private int id;
    private String msg;
    private Date date;

    private DateFormat df;
    private static int counter = 0;

//    public Event(String msg, Date date) {
//        this.id = counter++;
//        setMsg(msg);
//        setDate(date);
//    }

    public Event(Date date, DateFormat df) {
        this.id = counter++;
        this.date = date;
        this.df = df;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static boolean isDay() {
        Date now = new Date();
        SimpleDateFormat ldf = new SimpleDateFormat("HH:mm:ss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime startTime = LocalTime.of(8, 0, 0);
        LocalTime endTime = LocalTime.of(17, 0, 0);
        LocalTime time = LocalTime.parse(ldf.format(now), formatter);
        return time.compareTo(startTime) >= 0 && endTime.compareTo(time) >= 0;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
