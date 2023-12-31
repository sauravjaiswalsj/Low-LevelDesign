package Model;

import Services.Database.UserRepository;

public class User {
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private String address;
    private boolean isMod;

    private String password;

    private User(String firstName, String lastName, String email, long phoneNumber, String address, boolean isMod,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.isMod = isMod;
        this.password = password;
    }

    public static User createUser(String firstName, String lastName, String email, long phoneNumber, String address, boolean isMod,String password){
        User user = new User(firstName,lastName,email,phoneNumber,address,isMod,password);
        UserRepository.addUser(user);
        long id = UserRepository.getUserId(email);
        user.setUserId(id);
        return user;
    }

    public static User setLocalUser(long userId,String firstName, String lastName, String email, long phoneNumber, boolean isMod, String address){
        User user = new User(firstName,lastName,email,phoneNumber,address,isMod,null);
        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public boolean isMod() {
        return isMod;
    }

    public long getUserId(){
        return userId;
    }

    public String getPassword() {
        return password;
    }

    private void setUserId(long userId){
        this.userId = userId;
    }
}
