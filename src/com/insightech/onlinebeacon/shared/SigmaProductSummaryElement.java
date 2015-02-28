package com.insightech.onlinebeacon.shared;

import java.io.Serializable;

public class SigmaProductSummaryElement extends ProductSummaryElement implements
		Serializable {

	private static final long serialVersionUID = 1L;

	protected SigmaProductSummaryElement() {

	}

	private long contractors;

	public SigmaProductSummaryElement(String name, long p, long s, long c,
			long g, long o) {
		super(name, p, s, g, o);
		contractors = c;
	}

	public long getContractors() {
		return contractors;
	}

}
