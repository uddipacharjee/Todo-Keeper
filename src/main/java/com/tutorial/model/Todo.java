package com.tutorial.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

public class Todo {
    private int id;
    private String user;
    @NotNull
    @Size(min=10,message = "Enter at least 10 characters.")
    private String desc;

    private Date targetDate;
    private boolean isDone;

    public Todo() {
    }

    public Todo(int id, String user, String desc, Date targetDate, boolean isDone) {
        this.id = id;
        this.user = user;
        this.desc = desc;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id &&
                isDone == todo.isDone &&
                Objects.equals(user, todo.user) &&
                Objects.equals(desc, todo.desc) &&
                Objects.equals(targetDate, todo.targetDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, desc, targetDate, isDone);
    }
*/
   // @Override
   /* public String toString() {
        return "Todo{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", desc='" + desc + '\'' +
                ", targetDate=" + targetDate +
                ", isDone=" + isDone +
                '}';
    }*/

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Todo{");
        sb.append("id=").append(id);
        sb.append(", user='").append(user).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", targetDate=").append(targetDate);
        sb.append(", isDone=").append(isDone);
        sb.append('}');
        return sb.toString();
    }
}
