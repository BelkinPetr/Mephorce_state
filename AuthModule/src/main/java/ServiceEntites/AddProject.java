package ServiceEntites;

import HibernateEntities.ProjectsEntity;

import java.sql.Date;

/**
 * Created by postgres on 23.03.2017.
 */
public class AddProject {
    private int clprId;
    private String cltitle;
    private String cldescription;
    private String clstatus;
    private Date cldateOfReady;
    private Date cldateOfCreation;
    private int clsum;
    private String jsondata;
    private Integer clclId;
    private Integer clmdId;
    private Integer clmoneyDivision;
    private Integer clpersonNumber;
    private String clworktypes;

    public AddProject(ProjectsEntity project){
        this.cldateOfCreation = project.getDateOfCreation();
        this.clprId = project.getPrId();
        this.cltitle = project.getTitle();
        this.cldescription = project.getDescription();
        this.clstatus = project.getStatus();
        this.cldateOfReady = project.getDateOfReady();
        this.clsum = project.getSum();
        this.jsondata = project.getJsondata();
        this.clclId = project.getClId();
        this.clmdId = project.getMdId();
        this.clmoneyDivision=project.getMoneyDivision();
        this.clpersonNumber=project.getPersonNumber();
        this.clworktypes=project.getWorktypes();

    }

    public AddProject(){

    }

    public Date getCldateOfCreation() {
        return cldateOfCreation;
    }
    public void setCldateOfCreation(Date cldateOfCreation) {
        this.cldateOfCreation=cldateOfCreation;
    }
    public int getClprId() {
        return clprId;
    }

    public void setClprId(int clprId) {
        this.clprId = clprId;
    }

    public String getCltitle() {
        return cltitle;
    }

    public void setCltitle(String cltitle) {
        this.cltitle = cltitle;
    }

    public String getCldescription() {
        return cldescription;
    }

    public void setCldescription(String cldescription) {
        this.cldescription = cldescription;
    }

    public String getClstatus() {
        return clstatus;
    }

    public void setClstatus(String clstatus) {
        this.clstatus = clstatus;
    }

    public Date getCldateOfReady() {
        return cldateOfReady;
    }

    public void setCldateOfReady(Date cldateOfReady) {
        this.cldateOfReady = cldateOfReady;
    }

    public Integer getClsum() {
        return clsum;
    }

    public void setClsum(Integer clsum) {
        this.clsum = clsum;
    }

    public String getJsondata() {
        return jsondata;
    }

    public void setJsondata(String jsondata) {
        this.jsondata = jsondata;
    }

    public Integer getClclId() {
        return clclId;
    }

    public void setClclId(Integer clclId) {
        this.clclId = clclId;
    }
    public String getClworktypes(){ return clworktypes;}
    public void  setClworktypes(String clworktypes){this.clworktypes = clworktypes;}

}



