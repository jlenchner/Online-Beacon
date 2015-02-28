package com.insightech.onlinebeacon.shared;

import java.io.Serializable;

import com.insightech.beacon.simulator.SigmaBooks;

abstract public class SigmaCommon implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int INEXPERIENCED_CONTRACTORS_PER_CONTRACT = 9;
	public static final float ADMIN_RATE = 0.03f;

	static public long getAdminExpense(long revenue) {
		return (long) (revenue * ADMIN_RATE);
	}

	static public float getContractorsPerContract(float experience) {
		return INEXPERIENCED_CONTRACTORS_PER_CONTRACT / experience;
	}

	static public long calculateContracts(Long contractors, float cpc) {
		return (long) Math.floor(contractors / cpc);
	}

	static public long getCostOfContractors(float cpc) {
		return (long) (cpc * SigmaBooks.YEARLY_SALARY_PER_CONTRACTOR);
	}

	static public float workingContractorsFromExperience(long contracts,
			float expeerience) {
		float contractorsPerContract = getContractorsPerContract(expeerience);
		return contracts * contractorsPerContract;
	}

	public static long calculateContractsFromExperience(long contractors,
			float startingExperience) {
		float cpc = getContractorsPerContract(startingExperience);
		return calculateContracts(contractors, cpc);
	}

	public static long getContracts(long contractors, float experience) {
		return calculateContracts(contractors,
				getContractorsPerContract(experience));
	}

}
