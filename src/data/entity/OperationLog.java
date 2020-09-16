package data.entity;

import enumPackage.OperationEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class OperationLog {
    @Id
    @GeneratedValue
    private int id;
    private OperationEnum operation;
    private String authority;
    private Time time;
    private Date date;
    private int productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public OperationEnum getOperation() {
        return operation;
    }

    public void setOperation(OperationEnum operation) {
        this.operation = operation;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static class LogBuilder {
        private OperationEnum operation;
        private String authority;
        private Time time;
        private Date date;
        private int productId;

        public static LogBuilder aLog() {
            LogBuilder logBuilder = new LogBuilder();
            return logBuilder;
        }

        public LogBuilder withOperation(OperationEnum operation) {
            this.operation = operation;
            return this;
        }

        public LogBuilder withAuthority(String authority) {
            this.authority = authority;
            return this;
        }

        public LogBuilder withTime(Time time) {
            this.time = time;
            return this;
        }

        public LogBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        public LogBuilder withProductId(int productId) {
            this.productId = productId;
            return this;
        }

        public OperationLog build() {
            OperationLog operationLog = new OperationLog();
            operationLog.setOperation(this.operation);
            operationLog.setAuthority(this.authority);
            operationLog.setTime(this.time);
            operationLog.setDate(this.date);
            operationLog.setProductId(this.productId);
            return operationLog;
        }
    }

}
