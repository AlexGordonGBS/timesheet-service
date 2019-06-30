package com.alex.gordon.timesheet.util;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Objects;
import java.util.UUID;

@RedisHash("Timesheet")
public class TimesheetEntity {

    @Indexed
    private String id = UUID.randomUUID().toString();
    private String date;
    @Indexed
    private String client;
    private String project;
    private String projectCode;
    private String task;
    private String hours;
    private String hoursRounded;
    private String billable;
    private String invoiced;
    private String approved;
    private String firstName;
    private String lastName;
    private String department;
    private String employee;
    private String billableRate;
    private String costRate;
    private String costAmount;
    private String currency;
    private String external0ReferenceUrl;

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimesheetEntity)) return false;
        TimesheetEntity that = (TimesheetEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TimesheetEntity{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", client='" + client + '\'' +
                ", project='" + project + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", task='" + task + '\'' +
                ", hours='" + hours + '\'' +
                ", hoursRounded='" + hoursRounded + '\'' +
                ", billable='" + billable + '\'' +
                ", invoiced='" + invoiced + '\'' +
                ", approved='" + approved + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                ", employee='" + employee + '\'' +
                ", billableRate='" + billableRate + '\'' +
                ", costRate='" + costRate + '\'' +
                ", costAmount='" + costAmount + '\'' +
                ", currency='" + currency + '\'' +
                ", external0ReferenceUrl='" + external0ReferenceUrl + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getHoursRounded() {
        return hoursRounded;
    }

    public void setHoursRounded(String hoursRounded) {
        this.hoursRounded = hoursRounded;
    }

    public String getBillable() {
        return billable;
    }

    public void setBillable(String billable) {
        this.billable = billable;
    }

    public String getInvoiced() {
        return invoiced;
    }

    public void setInvoiced(String invoiced) {
        this.invoiced = invoiced;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getBillableRate() {
        return billableRate;
    }

    public void setBillableRate(String billableRate) {
        this.billableRate = billableRate;
    }

    public String getCostRate() {
        return costRate;
    }

    public void setCostRate(String costRate) {
        this.costRate = costRate;
    }

    public String getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(String costAmount) {
        this.costAmount = costAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExternal0ReferenceUrl() {
        return external0ReferenceUrl;
    }

    public void setExternal0ReferenceUrl(String external0ReferenceUrl) {
        this.external0ReferenceUrl = external0ReferenceUrl;
    }
}
