package com.hp.QppColumbia.services.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EditASDetails {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getDba() {
		return dba;
	}

	public void setDba(String dba) {
		this.dba = dba;
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

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public String getSuppliesStatus() {
		return suppliesStatus;
	}

	public void setSuppliesStatus(String suppliesStatus) {
		this.suppliesStatus = suppliesStatus;
	}

	public String getQdnApplicationForm() {
		return qdnApplicationForm;
	}

	public void setQdnApplicationForm(String qdnApplicationForm) {
		this.qdnApplicationForm = qdnApplicationForm;
	}

	public String getHpsHardwareStatus() {
		return hpsHardwareStatus;
	}

	public void setHpsHardwareStatus(String hpsHardwareStatus) {
		this.hpsHardwareStatus = hpsHardwareStatus;
	}

	public String getOpsHardwareStatus() {
		return opsHardwareStatus;
	}

	public void setOpsHardwareStatus(String opsHardwareStatus) {
		this.opsHardwareStatus = opsHardwareStatus;
	}

	public String getBpsStatus() {
		return bpsStatus;
	}

	public void setBpsStatus(String bpsStatus) {
		this.bpsStatus = bpsStatus;
	}

	public String getHpContactName() {
		return hpContactName;
	}

	public void setHpContactName(String hpContactName) {
		this.hpContactName = hpContactName;
	}

	public String getHpContactEmail() {
		return hpContactEmail;
	}

	public void setHpContactEmail(String hpContactEmail) {
		this.hpContactEmail = hpContactEmail;
	}

	public String getResellerContactName() {
		return resellerContactName;
	}

	public void setResellerContactName(String resellerContactName) {
		this.resellerContactName = resellerContactName;
	}

	public String getResellerContactEmail() {
		return resellerContactEmail;
	}

	public void setResellerContactEmail(String resellerContactEmail) {
		this.resellerContactEmail = resellerContactEmail;
	}

	public String getResellerContactPhone() {
		return resellerContactPhone;
	}

	public void setResellerContactPhone(String resellerContactPhone) {
		this.resellerContactPhone = resellerContactPhone;
	}

	public String getResellerWebAddress() {
		return resellerWebAddress;
	}

	public void setResellerWebAddress(String resellerWebAddress) {
		this.resellerWebAddress = resellerWebAddress;
	}

	public String getResellerPhysicalAddress() {
		return resellerPhysicalAddress;
	}

	public void setResellerPhysicalAddress(String resellerPhysicalAddress) {
		this.resellerPhysicalAddress = resellerPhysicalAddress;
	}

	public String getCityOrDistrict() {
		return cityOrDistrict;
	}

	public void setCityOrDistrict(String cityOrDistrict) {
		this.cityOrDistrict = cityOrDistrict;
	}

	public String getStateOrRegion() {
		return stateOrRegion;
	}

	public void setStateOrRegion(String stateOrRegion) {
		this.stateOrRegion = stateOrRegion;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMembershipForPc() {
		return membershipForPc;
	}

	public void setMembershipForPc(String membershipForPc) {
		this.membershipForPc = membershipForPc;
	}

	public String getMembershipForPrint() {
		return membershipForPrint;
	}

	public void setMembershipForPrint(String membershipForPrint) {
		this.membershipForPrint = membershipForPrint;
	}

	public String getAmplifyMembershipForSupplies() {
		return amplifyMembershipForSupplies;
	}

	public void setAmplifyMembershipForSupplies(String amplifyMembershipForSupplies) {
		this.amplifyMembershipForSupplies = amplifyMembershipForSupplies;
	}

	public String getQdnStartDate() {
		return qdnStartDate;
	}

	public void setQdnStartDate(String qdnStartDate) {
		this.qdnStartDate = qdnStartDate;
	}

	public String getQdnEndDate() {
		return qdnEndDate;
	}

	public void setQdnEndDate(String qdnEndDate) {
		this.qdnEndDate = qdnEndDate;
	}

	public String getCommentsORNotes() {
		return commentsORNotes;
	}

	public void setCommentsORNotes(String commentsORNotes) {
		this.commentsORNotes = commentsORNotes;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getRequestedOn() {
		return requestedOn;
	}

	public void setRequestedOn(String requestedOn) {
		this.requestedOn = requestedOn;
	}

	private String homologatedName;

	private String razonSocial;

	private String dba;

	private String taxId;

	private String locationId;

	private String partnerId;

	private String agreement;

	private String tier;

	private String suppliesStatus;

	private String qdnApplicationForm;

	private String hpsHardwareStatus;

	private String opsHardwareStatus;

	private String bpsStatus;

	private String hpContactName;

	private String hpContactEmail;

	private String resellerContactName;

	private String resellerContactEmail;

	private String resellerContactPhone;

	private String resellerWebAddress;

	private String resellerPhysicalAddress;

	private String cityOrDistrict;

	private String stateOrRegion;

	private String zipCode;

	private String membershipForPc;

	private String membershipForPrint;

	private String amplifyMembershipForSupplies;

	private String qdnStartDate;

	private String qdnEndDate;

	private String commentsORNotes;
	
	private String comment;
	
	private String requestedBy;
	
	private String requestedOn;
}
