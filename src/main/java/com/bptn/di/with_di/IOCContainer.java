package com.bptn.di.with_di;

public class IOCContainer {

	public static void main(String[] args) {
		

		Soldier s = new Soldier();
		
		// Use Vietnam Mission
		VietnamMission vm = new VietnamMission();
		s.setMission(vm);
		s.goToMission();

		// Use Korean Mission
		Mission km = new KoreaMission();
		s.setMission(km);
		s.goToMission();
		
		
		//List<String> list = new ArrayList<>();

	}

}
