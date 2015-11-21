package com.mystudy.study.java.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class StudyMap {

	@Test
	public void testHashMapAndLinkedHashMap(){
		Map<String,String> map1 = new HashMap<String,String>();
		Map<String,String> map2 = new LinkedHashMap<String,String>();
		map1.put("1", "1");
		map1.put("2", "2");
		map1.put("3", "3");
		map1.put("4", "4");
		map1.put("5", "5");
		
		map2.put("1", "1");
		map2.put("2", "2");
		map2.put("3", "3");
		map2.put("4", "4");
		map2.put("5", "5");
		map2.get("3");
		for(String key : map1.keySet()){
			System.out.println(map1.get(key));
		}
		
		for(String key : map2.keySet()){
			System.out.println(map2.get(key));
		}
	}
}
