package com.kishore.learning.string;

import java.util.HashMap;
import java.util.Set;

public class SetCheck {
	
	public static void main(String[] args) {
		        HashMap<String, String> hm = new HashMap<String, String>();
		        //add key-value pair to hashmap
		        hm.put("first", "FIRST INSERTED");
		        hm.put("second", "SECOND INSERTED");
		        hm.put("third","THIRD INSERTED");
		        System.out.println(hm);
		        Set<String> keys = hm.keySet();
		        if (keys.contains("first1")) {
		        	System.out.println("Contains is true");
		        } else {
		        	System.out.println("Contains is false");
		        }
		        for(String key: keys){
		            System.out.println(key);
		        }
	}

}
