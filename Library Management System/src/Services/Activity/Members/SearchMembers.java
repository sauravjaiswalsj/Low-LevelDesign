package Services.Activity.Members;

import Model.Book;
import Model.User;
import Services.Database.UserRepository;

public class SearchMembers {
    public SearchMembers(String email){
        long userId = UserRepository.getUserId(email);
        User user = UserRepository.getUserInfo(userId);

        System.out.println("------------------------------------------------");
        System.out.println("User Id: "+ user.getUserId());
        System.out.println("Name: "+ user.getFirstName() + " "+ user.getLastName());
        System.out.println("Email = " + user.getEmail());
        System.out.println("Phone No. = " + user.getPhoneNumber());
        System.out.println("Address = " + user.getAddress());
        System.out.println("Are you mod? = " + user.isMod());
        System.out.println("------------------------------------------------");
    }
}
