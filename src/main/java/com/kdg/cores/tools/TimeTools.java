package com.kdg.cores.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTools {

    public static String getDateStr(int timeDelta, long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date;

        if (timestamp != 0) {
            date = new Date(timestamp);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, timeDelta);
            date = calendar.getTime();
        }

        return dateFormat.format(date);
    }

    public static String getDateStr(int timeDelta) {
        return getDateStr(timeDelta, 0);
    }

    public static String getDateStr() {
        return getDateStr(0);
    }

    public static long dateStrParse(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            return dateFormat.parse(dateStr).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        String dateStr = getDateStr(-1);
        System.out.println(dateStr);
    }
}
