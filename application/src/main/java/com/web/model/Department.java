package com.web.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum Department {

	mech(0), civil(1), eee(2), ece(3), cse(4);

	int order;

	Department(int i) {
		this.order = i;
	}

	public int getOrder() {
		return order;
	}
}
