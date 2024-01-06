package Services.Activity.Transaction;

import Services.Database.Fines;
import Services.Database.Stats;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GenerateStats {
    public GenerateStats(){
        System.out.println("Generating Report on most borrowed books.");
        HashMap<String, Integer> map = Stats.generateStats();

        for(Map.Entry<String,Integer> set : map.entrySet()){
            System.out.println(set.getKey() + " " + set.getValue());
        }

        System.out.println("Total Fine Collected: "+ Fines.getTotalFine());
    }
}
