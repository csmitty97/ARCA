package com.aca.vet.model;



public enum Gender {

	Male,
	Female;
	
	public static Gender convertStringToGender(String value) {
		Gender genders = null;
		for(Gender gender : Gender.values()) {
			if(gender.toString().equalsIgnoreCase(value)) {
				genders = gender;
				break;
			}
		}
		return genders;
	}
}
