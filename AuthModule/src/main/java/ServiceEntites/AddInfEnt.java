package ServiceEntites;

import java.util.ArrayList;

/**
 * Created by kinetik on 05.03.17.
 */
public class AddInfEnt {
    private String birthdate;
    private String hometown;
    private String e_mail;
    private String phone_number;
    private String company;
    private String position;

    public ArrayList<Integer> getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(ArrayList<Integer> skillsId) {
        this.skillsId = skillsId;
    }

    private ArrayList<Integer> skillsId;

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}