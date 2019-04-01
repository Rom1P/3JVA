package entities;

public class User {
    private String username;
    private String password;
    private String phoneNumber;
    private String lastName;
    private String firstName;
    private String postalAddress;
    private String email;
    private int level; //1 = user, 2 = admin

    public User(String username, String password, String phoneNumber, String lastName, String firstName, String postalAddress, String email, int level) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.postalAddress = postalAddress;
        this.email = email;
        this.level = level;

        boolean validInputs = checkInputsValidity();

        if (validInputs){
            addToDB();
            loginAfterRegister();
        }
    }

    private boolean checkInputsValidity(){
        //TODO check if already in DB
        return true;
    }

    private void addToDB(){

    }

    private void loginAfterRegister(){

    }


    /* Getters and setters */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
