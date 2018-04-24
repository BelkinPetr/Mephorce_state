package ServiceEntities;

/**
 * Created by kinetik on 27.02.17.
 */
public class RegUser {
    private String firstName;
    private String secName;
    private String thirdName;
    private String password;
    private String confPassword;

    public String getFirstName(){
        return this.firstName;
    }
    public String getSecName(){
        return this.secName;
    }
    public String getThirdName(){
        return this.thirdName;
    }
    public String getPassword(){
        return this.password;
    }
    public String getConfPassword(){
        return this.confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }
}
