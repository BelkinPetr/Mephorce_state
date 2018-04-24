package ServiceEntites;

/**
 * Created by ilysko on 28.06.17.
 */
public class Rating {
    private int firstParam;
    private int secondParam;
    private int thirdParam;
    private int fourthParam;
    private int studentId;
    private int skilCatId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSkilCatId() {
        return skilCatId;
    }

    public void setSkilCatId(int skilCatId) {
        this.skilCatId = skilCatId;
    }

    public Rating(int studentId, int skilCatId) {
        this.studentId = studentId;
        this.skilCatId = skilCatId;
    }

    public Rating(){}

    public int getFirstParam() {
        return firstParam;
    }

    public void setFirstParam(int firstParam) {
        this.firstParam = firstParam;
    }

    public int getSecondParam() {
        return secondParam;
    }

    public void setSecondParam(int secondParam) {
        this.secondParam = secondParam;
    }

    public int getThirdParam() {
        return thirdParam;
    }

    public void setThirdParam(int thirdParam) {
        this.thirdParam = thirdParam;
    }

    public int getFourthParam() {
        return fourthParam;
    }

    public void setFourthParam(int fourthParam) {
        this.fourthParam = fourthParam;
    }
}
