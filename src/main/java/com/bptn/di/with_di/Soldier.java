package com.bptn.di.with_di;

public class Soldier {

	Mission m;
	
	void setMission(Mission m) {
		this.m = m;
	}
	
	void goToMission() {
		m.executeMission();
	}
	
}
