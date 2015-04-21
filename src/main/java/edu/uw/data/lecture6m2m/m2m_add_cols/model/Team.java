package edu.uw.data.lecture6m2m.m2m_add_cols.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by credmond on 4/13/15.
 */
@Entity
@Table(name = "EMPLOYEE_PROJECT")

public class Team {


    @EmbeddedId
    private Id id;

    @Column(name = "IS_PROJECT_LEAD")
    private boolean isProjectLead;

    public Team() {
    }

    public Team(Id id, boolean isProjectLead) {
        this.id = id;
        this.isProjectLead = isProjectLead;
    }

    public Team(Id id) {
        this.id = id;
        this.isProjectLead = false;
    }


    public boolean isProjectLead() {
        return isProjectLead;
    }

    public void setProjectLead(boolean isProjectLead) {
        this.isProjectLead = isProjectLead;
    }


    public Id getId() {
        return this.id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    @Transient
    public Employee getEmployee() {
        return id.getEmployee();
    }

    @Transient
    public Project getProject() {
        return id.getProject();
    }

    @Embeddable
    public static class Id implements java.io.Serializable {


        @ManyToOne(fetch = FetchType.EAGER, cascade = {})
        @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID", nullable = false)
        private Employee employee;

        @ManyToOne(fetch = FetchType.EAGER, cascade = {})
        @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID", nullable = false)
        private Project project;

        public Id() {
        }

        public Id(Project project, Employee employee) {
            this.project = project;
            this.employee = employee;


        }

        public Employee getEmployee() {
            return employee;
        }

        public void setEmployee(Employee employee) {
            this.employee = employee;
        }

        public Project getProject() {
            return project;
        }

        public void setProject(Project project) {
            this.project = project;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Id)) return false;

            Id id = (Id) o;

            if (!employee.equals(id.employee)) return false;  //TODO first and lastname instead
            return project.getProjectName().equals(id.project.getProjectName());

        }

        @Override
        public int hashCode() {
            int result = employee.hashCode();
            result = 31 * result + project.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Id{" +
                    "employee=" + employee.getId() +
                    ", project=" + project.getProjectName() +

                    '}';
        }
    }
}
