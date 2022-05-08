package iplsim;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IPLSim {

	static String teamToCheck;
	static Integer totalCombi=0;
	static Integer clearQuali=0;
	static Integer nrrQuali=0;
	static BufferedWriter fw;
	static boolean log = false;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Map<String,int[]> ptMap = getPtMap();
		System.out.println("intially: ");
		printMap(ptMap);
		
		teamToCheck="CSK";   		//change team name here to check
		
		System.out.println("-----");
		
		if(log)fw =  new BufferedWriter(new FileWriter("out.txt"));
		if(log)fw.write("test");

		helper(0, ptMap,new LinkedList<String>());

		if(log)fw.flush();
		if(log)fw.close();
		System.out.println("Total Combinations: "+totalCombi);
		System.out.println("Possible Clear Cut Combinations: "+clearQuali+" for TEAM: "+teamToCheck);
		System.out.println("Possible NRR based Quali Combinations: "+nrrQuali+" for TEAM: "+teamToCheck);
	}

	
	private static void helper(int i,Map<String,int[]> pts,LinkedList<String> path) throws IOException {
		if(i>=matches.size()) {
			totalCombi++;
			ptMapStr(pts,path);
			
			return;
		}
		
		String[] teams = matches.get(i).split(",");
		String game = matches.get(i).replace(",", "vs");
		pts.get(teams[0])[1]+=1;
		pts.get(teams[1])[1]+=1;
		
		//first team wins
		String winner = teams[0];
		pts.get(winner)[0]+=2;
		path.addLast(game+"-"+winner);
		helper(i+1, pts,path);
		path.removeLast();
		pts.get(winner)[0]-=2;
		
		//second team wins
		winner =teams[1];
		pts.get(winner)[0]+=2;
		path.addLast(game+"-"+winner);
		helper(i+1, pts,path);
		path.removeLast();
		pts.get(winner)[0]-=2;
		
		pts.get(teams[0])[1]-=1;
		pts.get(teams[1])[1]-=1;
		
	}
	
	
	private static Map<String,int[]> getPtMap() {
		Map<String,int[]> map = new HashMap<>();
		map.put("LSG", new int[] {16,11});
		map.put("GT", new int[] {16,11});
		map.put("RR", new int[] {14,11});
		map.put("RCB", new int[] {14,12});
		map.put("DC", new int[] {10,11});
		map.put("SRH", new int[] {10,11});
		map.put("PBSK", new int[] {10,11});
		map.put("KKR", new int[] {8,11});
		map.put("CSK", new int[] {8,11});
		map.put("MI", new int[] {4,10});
		return map;
	}

	private static void ptMapStr(Map<String,int[]> bla,LinkedList<String> path) throws IOException {
//		System.out.println("-----------------");
		Map<String,int[]> topTen =
			    bla.entrySet().stream()
			       .sorted((e1,e2)->e2.getValue()[0]-e1.getValue()[0])
//			       .limit(4)
			       .collect(Collectors.toMap(
			          Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		int i=1;
//		printMap(topTen);
		int pointsForTeamtoCheck = topTen.get(teamToCheck)[0];
		int fourthTeamPOints =-1;
		for(Map.Entry<String,int[]> e:topTen.entrySet()) {
			if(i==4) {
				fourthTeamPOints = e.getValue()[0];
			}
			if(i<=4&&e.getKey().equals(teamToCheck)) {
				clearQuali++;
				if(log)printMap(topTen);
				if(log)fw.write(path.toString()+"\n");
				
			}else if(i>4 && pointsForTeamtoCheck == fourthTeamPOints) {
				if(log)printMap(topTen);
				if(log)fw.write("NRR: |"+path.toString()+"\n");
				nrrQuali++;
				if(log)System.out.println("NRR case");
			}
			i++;
		}
	}
	
	
	private static void printMap(Map<String,int[]> bla) {
		for(Map.Entry<String,int[]> e:bla.entrySet()) {
			System.out.print(e.getKey()+":"+e.getValue()[0]+","+e.getValue()[1]+"  ");
		}
		System.out.println("");
	}
	
//	static List<String> matches=Arrays.asList(new String[] {
//			"CSK,DC",
//			"MI,KKR",
//			"LSG,GT",
//			"RR,DC",
//			"CSK,MI",
//			"RCB,PBSK"
//	});
	
	static List<String> matches=Arrays.asList(new String[] {
//			"RCB,SRH", //8th may
//			"CSK,DC", //8th may
			"MI,KKR",
			"LSG,GT",
			"RR,DC",
			"CSK,MI",
			"RCB,PBSK",
			"KKR,SRH",
			"CSK,GT",
			"LSG,RR",
			"PBSK,DC",
			"MI,SRH",
			"KKR,LSG",
			"RCB,GT",
			"RR,CSK",
			"MI,DC",
			"SRH,PBSK"
	});


}
