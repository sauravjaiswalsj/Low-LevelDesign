package Services.Activity.Members;

import Services.Database.UserRepository;

public class RemoveMembers {

    public RemoveMembers(String name, String email){
        long userId = UserRepository.getUserId(email);

        if(userId == -1){
            System.out.println("User "+ name +"  is not registered.");
            return;
        }
        UserRepository.removeUser(userId);
        System.out.println("User is removed.");
    }
}
