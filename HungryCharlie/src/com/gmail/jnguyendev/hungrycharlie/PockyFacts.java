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
    ArrayList<String> PockyList = new ArrayList<String>();


//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        popUp = new PopupWindow(this);
//        layout = new LinearLayout(this);
//        mainLayout = new LinearLayout(this);
//        tv = new TextView(this);
//        but = new Button(this);
//        but.setText("Click Me");
//        but.setOnClickListener(new OnClickListener() {
//
//            public void onClick(View v) {
//                if (click) {
//                    popUp.showAtLocation(mainLayout, Gravity.BOTTOM, 10, 10);
//                    popUp.update(50, 50, 300, 80);
//                    click = false;
//                } else {
//                    popUp.dismiss();
//                    click = true;
//                }
//            }
//
//        });
//        params = new LayoutParams(LayoutParams.WRAP_CONTENT,
//                LayoutParams.WRAP_CONTENT);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        tv.setText("Hi this is a sample text for popup window");
//        layout.addView(tv, params);
//        popUp.setContentView(layout);
//        popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
//        mainLayout.addView(but, params);
//        setContentView(mainLayout);
//        
//    }
    
    public PockyFacts() {
    	
    	PockyList.add("Fact 1");
    	PockyList.add("Fact 2");
    	PockyList.add("Fact 3");
    	PockyList.add("Fact 4");
    	PockyList.add("Fact 5");
    	PockyList.add("Fact 6");
    	PockyList.add("Fact 7");
    	PockyList.add("Fact 8");
    	PockyList.add("Fact 9");
    	PockyList.add("Fact 10");
    	PockyList.add("Fact 11");
    	PockyList.add("Fact 12");
    	PockyList.add("Fact 13");
    	PockyList.add("Fact 14");
    	PockyList.add("Fact 15");
    	PockyList.add("Fact 16");
    	PockyList.add("Fact 17");
    	PockyList.add("Fact 18");
    	PockyList.add("Fact 19");
    	PockyList.add("Fact 20");
    	
    }
    
    public static PockyFacts newInstance() {
    	PockyFacts f = new PockyFacts();
        return f;
    }
    
    public String getFact(){
        int randomNum = rand.nextInt(getCount());
        String pockyFact = PockyList.get(randomNum);
        
        return pockyFact;
    }
    
    public int getCount() {
    	return PockyList.size();
    }
    
}
