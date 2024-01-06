package Services.Activity.Members;

import Model.Book;
import Services.Database.BookRepository;
import Services.Database.Stats;
import Services.Database.UserRepository;

public class RemoveMembers {

    public RemoveMembers(String name, String email){
        try{
            long userId = UserRepository.getUserId(email);

            if(userId == -1){
                System.out.println("User "+ name +"  is not registered.");
                return;
            }
            UserRepository.removeUser(userId);
            System.out.println("User is removed.");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
