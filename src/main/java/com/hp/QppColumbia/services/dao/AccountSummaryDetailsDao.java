package com.hp.QppColumbia.services.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "account_summary")
public class AccountSummaryDetailsDao {
	@Id
	@Column(name = "unique_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getUnique_id() {
		return id;
	}

	public void setUnique_id(Integer id) {
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

	@Column(name = "homologatedName")
	private String homologatedName;

	@Column(name = "razonSocial")
	private String razonSocial;

	@Column(name = "dba")
	private String dba;

	@Column(name = "taxId")
	private String taxId;

	@Column(name = "locationId")
	private String locationId;

	@Column(name = "partnerId")
	private String partnerId;

	@Column(name = "agreement")
	private String agreement;

	@Column(name = "tier")
	private String tier;

	@Column(name = "suppliesStatus")
	private String suppliesStatus;

	@Column(name = "qdnApplicationForm")
	private String qdnApplicationForm;

	@Column(name = "hpsHardwareStatus")
	private String hpsHardwareStatus;

	@Column(name = "opsHardwareStatus")
	private String opsHardwareStatus;

	@Column(name = "bpsStatus")
	private String bpsStatus;

	@Column(name = "hpContactName")
	private String hpContactName;

	@Column(name = "hpContactEmail")
	private String hpContactEmail;

	@Column(name = "resellerContactName")
	private String resellerContactName;

	@Column(name = "resellerContactEmail")
	private String resellerContactEmail;

	@Column(name = "resellerContactPhone")
	private String resellerContactPhone;

	@Column(name = "resellerWebAddress")
	private String resellerWebAddress;

	@Column(name = "resellerPhysicalAddress")
	private String resellerPhysicalAddress;

	@Column(name = "cityOrDistrict")
	private String cityOrDistrict;

	@Column(name = "stateOrRegion")
	private String stateOrRegion;

	@Column(name = "zipCode")
	private String zipCode;

	@Column(name = "membershipForPc")
	private String membershipForPc;

	@Column(name = "membershipForPrint")
	private String membershipForPrint;

	@Column(name = "amplifyMembershipForSupplies")
	private String amplifyMembershipForSupplies;

	@Column(name = "qdnStartDate")
	private String qdnStartDate;

	@Column(name = "qdnEndDate")
	private String qdnEndDate;

	@Column(name = "commentsORNotes")
	private String commentsORNotes;
}
