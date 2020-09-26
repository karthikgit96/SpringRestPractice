package com.infyrail.util;

public enum InfyRailConstants {
	ROUTE_NOT_FOUND("route.not.found"),
	GENERAL_EXCEPTION_MESSAGE("general.exception");
	
	private final String type;
	
	private InfyRailConstants(String type) {
		this.type=type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
	

}
