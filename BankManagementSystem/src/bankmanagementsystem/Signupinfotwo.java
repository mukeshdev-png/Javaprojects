package bankmanagementsystem;


public class Signupinfotwo {
    private String formno;
    private String religion;
    private String category;
    private String income;
    private String education;
    private String seniorCitizen;
    private String existingAccount;
    private String occupation;
    private String pan;
    private String aadhar;


    // Constructor
    public Signupinfotwo(String formno,String religion, String category, String income,
                         String education, String seniorCitizen, String existingAccount,
                         String occupation, String pan, String aadhar) {

        this.formno = formno;
        this.religion = religion;
        this.category = category;
        this.income = income;
        this.education = education;
        this.seniorCitizen = seniorCitizen;
        this.existingAccount = existingAccount;
        this.occupation = occupation;
        this.pan = pan;
        this.aadhar = aadhar;
    }
}