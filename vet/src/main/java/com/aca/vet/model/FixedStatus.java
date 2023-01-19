package com.aca.vet.model;

public enum FixedStatus {

	Neutered,
	Spayed,
	Neither;
	
	public static FixedStatus convertStringToStatus(String value) {
		FixedStatus petStatuses = null;
		for(FixedStatus petStatus : FixedStatus.values()) {
			if(petStatus.toString().equalsIgnoreCase(value)) {
				petStatuses = petStatus;
				break;
			}
		}
		return petStatuses;
	}
}
