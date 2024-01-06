package Controllers;

import Services.Activity.Members.RemoveMembers;
import Services.Activity.Members.SearchMembers;
import java.util.Scanner;

public class MemberController {
    public static void removeMember(Scanner scanner){
        System.out.println("Enter the name of the member to be removed");
        String name = scanner.nextLine();

        System.out.println("Enter email of the user");
        String email = scanner.nextLine();

        new RemoveMembers(name,email);
    }

    public static void updateMember(){

    }

    public static void searchMember(Scanner scanner){
        System.out.println("Enter email of the user");
        String email = scanner.nextLine();

        new SearchMembers(email);
    }

}
