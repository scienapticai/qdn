
package com.hp.QppColumbia.services.entity;

import java.util.List;

public class ASFilterResponse {
	public List<String> getHomologatedName() {
		return homologatedName;
	}

	public void setHomologatedName(List<String> homologatedName) {
		this.homologatedName = homologatedName;
	}

	public List<String> getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(List<String> razonSocial) {
		this.razonSocial = razonSocial;
	}

	public List<String> getTaxId() {
		return taxId;
	}

	public void setTaxId(List<String> taxId) {
		this.taxId = taxId;
	}

	public List<String> getLocationId() {
		return locationId;
	}

	public void setLocationId(List<String> locationId) {
		this.locationId = locationId;
	}

	public List<String> getSuppliesStatus() {
		return suppliesStatus;
	}

	public void setSuppliesStatus(List<String> suppliesStatus) {
		this.suppliesStatus = suppliesStatus;
	}

	private List<String> homologatedName;
	private List<String> razonSocial;
	private List<String> taxId;
	private List<String> locationId;
	private List<String> suppliesStatus;
}