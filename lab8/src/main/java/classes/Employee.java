package main.java.classes;
import java.util.Date;

public class Employee extends Person{

    private String jobTitle;
    private Date hireDate;

    //constructor
    public Employee(int id, String fn, String ln, Date hr, String jt){
        setId(id);
        setFirstName(fn);
        setLastName(ln);
        setJobTitle(jt);
        setHireDate(hr);
    }


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }




}



