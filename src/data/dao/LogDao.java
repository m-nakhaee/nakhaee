package data.dao;

import data.entity.OperationLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;

public class LogDao {
    @Autowired
    SessionFactory sessionFactory;

    public void addLog(OperationLog operationLog) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(operationLog);
        transaction.commit();
        session.close();
    }

    public List<OperationLog> getLogs(Date startDate, Date endDate, String userName) {
        Session session = sessionFactory.openSession();
        Query query = session
                .createQuery("from OperationLog WHERE authority =:authority and :startDate<=date and date<=:endDate");
        query.setParameter("authority", userName);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List<OperationLog> operationLogs = query.list();
        session.close();
        return operationLogs;
    }

//    private List<OperationLog> getLogsFromResultSet() throws SQLException {
//        List<OperationLog> operationLogs = new ArrayList<>();
//        while (resultSet.next()) {
//            String string = resultSet.getString(1);
//            OperationEnum operation = getOperation(string);
//            String userName = resultSet.getString(2);
//            Time time = resultSet.getTime(3);
//            Date date = resultSet.getDate(4);
//            int productId = resultSet.getInt(5);
//            OperationLog log = OperationLog.LogBuilder.aLog()
//                    .withOperation(operation)
//                    .withAuthority(userName)
//                    .withTime(time)
//                    .withDate(date)
//                    .withProductId(productId).build();
//            operationLogs.add(log);
//        }
//        return operationLogs;
//    }
//
//    private OperationEnum getOperation(String string) {
//        OperationEnum operationEnum = null;
//        if (string.equals(OperationEnum.addToCart.toString()))
//            operationEnum = OperationEnum.addToCart;
//        if (string.equals(OperationEnum.logIn.toString()))
//            operationEnum = OperationEnum.logIn;
//        if (string.equals(OperationEnum.purchase.toString()))
//            operationEnum = OperationEnum.purchase;
//        if (string.equals(OperationEnum.removeFromCart.toString()))
//            operationEnum = OperationEnum.removeFromCart;
//        if (string.equals(OperationEnum.signUp.toString()))
//            operationEnum = OperationEnum.signUp;
//        return operationEnum;
//    }
}
