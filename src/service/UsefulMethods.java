package service;

import View.Main;
import data.dao.LogDao;
import data.entity.OperationLog;
import enumPackage.OperationEnum;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class UsefulMethods {

    public static boolean isNumber(String input) {
        if (input.length() == 0) return false;
        for (int i = 0; i < input.length(); i++)
            if (!Character.isDigit(input.charAt(i))) return false;
        return true;
    }

    public static void recordLog(OperationEnum operation, String userName) {
        OperationLog log = OperationLog.LogBuilder.aLog()
                .withOperation(operation)
                .withAuthority(userName)
                .withTime(Time.valueOf(LocalTime.now()))
                .withDate(Date.valueOf(LocalDate.now()))
                .build();
        LogDao logDao = Main.context.getBean("logDao", LogDao.class);
        logDao.addLog(log);
    }
}
