package com.hp.QppColumbia.services.entity;

public class AccountSummaryFilterDetails {
	public String getHomologatedName() {
		return homologatedName;
	}

	public void setHomologatedName(String homologatedName) {
		this.homologatedName = homologatedName;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getSuppliesStatus() {
		return suppliesStatus;
	}

	public void setSuppliesStatus(String suppliesStatus) {
		this.suppliesStatus = suppliesStatus;
	}

	private String homologatedName;

	private String razonSocial;

	private String taxId;

	private String locationId;

	private String suppliesStatus;
}
