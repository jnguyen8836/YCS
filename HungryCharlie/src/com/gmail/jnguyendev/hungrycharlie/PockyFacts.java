package com.gmail.jnguyendev.hungrycharlie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.*;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;

public class PockyFacts extends Activity {

    PopupWindow popUp;
    LinearLayout layout;
    TextView tv;
    LayoutParams params;
    LinearLayout mainLayout;
    Button but;
    boolean click = true;
    Random rand = new Random();
    int prevNum = 0;
    ArrayList<String> PockyList = new ArrayList<String>();
    
    public PockyFacts() {
    	PockyList.add("The correct pronunciation of Pocky is actualy poh-ki!");
    	PockyList.add("Pocky was created in 1963 by the Glico Corporation.");
    	PockyList.add("Pocky was originally called Chocoteck!");
    	PockyList.add("The name Pocky originates from the Japanese onomatopoeia pokkin'.");
    	PockyList.add("The first 3 Pocky flavors in order are: Chocolate, Almond, and Strawberry!");
    	PockyList.add("Every fall a new flavor of Pocky is released!");
    	PockyList.add("Grape flavor Pocky exists exclusively in Nagano, Japan.");
    	PockyList.add("There exists sweet potato flavored Pocky!");
    	PockyList.add("In Europe, Pocky is called Mikado!");
    	PockyList.add("International Pocky day is November 11th!");
    	PockyList.add("The American indie rock band Superchunk released an album named 'No Pocky for Kitty'.");
    	PockyList.add("Glico, the makers of Pocky, is actualy short for glycogen!");
    	PockyList.add("Pocky is currently sold in 30 countries.");
    	PockyList.add("A tribute to Kobe, Japan, there is Kobe Wine flavored Pocky!");
    	PockyList.add("A person or group of people known only as 'The Monster with 21 Faces' kidnapped the president of Glico in 1984.");
    	PockyList.add("Pretz is the savory version of Pocky. It comes in flavors like tomato, pizza, and salad.");
    	PockyList.add("There is even Men's Pocky which is made of darker chocolate and much less sweet than regular Pocky.");
    	PockyList.add("There is an official Pocky cake made with raisins, chocolate cream, orange peel, and an Italian cake batter.");
    	PockyList.add("Pocky was inspired by the Meiji product 'Yan Yan'.");
    	PockyList.add("In Malaysia, Pocky is sold as Rocky.");
    	
    }
    
    public static PockyFacts newInstance() {
    	PockyFacts f = new PockyFacts();
        return f;
    }
    
    public String getFact() {
        int num = rand.nextInt(getCount());
        if (num == prevNum) num = rand.nextInt(getCount());

        String pockyFact = PockyList.get(num);
        prevNum = num;

        return pockyFact;
    }
    
    public int getCount() {
    	return PockyList.size();
    }
    
}
