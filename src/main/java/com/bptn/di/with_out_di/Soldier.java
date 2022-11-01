package com.bptn.di.with_out_di;

public class Soldier {
	

	//Technical Debt
	
	VietnamMission vm = new VietnamMission();
	
	void goToVietnamMission() {
		vm.executeVietnamMission();
	}
	
	
	KoreaMission km = new KoreaMission();
	
	void goToKoreaMission() {
		km.executeKoreaMission();
	}




	
	public static void main(String[] args) {
		Soldier s = new Soldier();
		
		s.goToVietnamMission();
		s.goToKoreaMission();
	}
	
}
