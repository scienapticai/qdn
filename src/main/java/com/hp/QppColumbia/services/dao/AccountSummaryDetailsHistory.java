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
@Data
@Entity
@Table(name = "account_summary_history")
public class AccountSummaryDetailsHistory {
	
		public String getUnique_id() {
		return id;
	}

	public void setUnique_id(String id) {
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

	public String getHomologatedNameFlag() {
		return homologatedNameFlag;
	}

	public void setHomologatedNameFlag(String homologatedNameFlag) {
		this.homologatedNameFlag = homologatedNameFlag;
	}

	public String getRazonSocialFlag() {
		return razonSocialFlag;
	}

	public void setRazonSocialFlag(String razonSocialFlag) {
		this.razonSocialFlag = razonSocialFlag;
	}

	public String getDbaFlag() {
		return dbaFlag;
	}

	public void setDbaFlag(String dbaFlag) {
		this.dbaFlag = dbaFlag;
	}

	public String getTaxIdFlag() {
		return taxIdFlag;
	}

	public void setTaxIdFlag(String taxIdFlag) {
		this.taxIdFlag = taxIdFlag;
	}

	public String getLocationIdFlag() {
		return locationIdFlag;
	}

	public void setLocationIdFlag(String locationIdFlag) {
		this.locationIdFlag = locationIdFlag;
	}

	public String getPartnerIdFlag() {
		return partnerIdFlag;
	}

	public void setPartnerIdFlag(String partnerIdFlag) {
		this.partnerIdFlag = partnerIdFlag;
	}

	public String getAgreementFlag() {
		return agreementFlag;
	}

	public void setAgreementFlag(String agreementFlag) {
		this.agreementFlag = agreementFlag;
	}

	public String getTierFlag() {
		return tierFlag;
	}

	public void setTierFlag(String tierFlag) {
		this.tierFlag = tierFlag;
	}

	public String getSuppliesStatusFlag() {
		return suppliesStatusFlag;
	}

	public void setSuppliesStatusFlag(String suppliesStatusFlag) {
		this.suppliesStatusFlag = suppliesStatusFlag;
	}

	public String getQdnApplicationFormFlag() {
		return qdnApplicationFormFlag;
	}

	public void setQdnApplicationFormFlag(String qdnApplicationFormFlag) {
		this.qdnApplicationFormFlag = qdnApplicationFormFlag;
	}

	public String getHpsHardwareStatusFlag() {
		return hpsHardwareStatusFlag;
	}

	public void setHpsHardwareStatusFlag(String hpsHardwareStatusFlag) {
		this.hpsHardwareStatusFlag = hpsHardwareStatusFlag;
	}

	public String getOpsHardwareStatusFlag() {
		return opsHardwareStatusFlag;
	}

	public void setOpsHardwareStatusFlag(String opsHardwareStatusFlag) {
		this.opsHardwareStatusFlag = opsHardwareStatusFlag;
	}

	public String getBpsStatusFlag() {
		return bpsStatusFlag;
	}

	public void setBpsStatusFlag(String bpsStatusFlag) {
		this.bpsStatusFlag = bpsStatusFlag;
	}

	public String getHpContactNameFlag() {
		return hpContactNameFlag;
	}

	public void setHpContactNameFlag(String hpContactNameFlag) {
		this.hpContactNameFlag = hpContactNameFlag;
	}

	public String getHpContactEmailFlag() {
		return hpContactEmailFlag;
	}

	public void setHpContactEmailFlag(String hpContactEmailFlag) {
		this.hpContactEmailFlag = hpContactEmailFlag;
	}

	public String getResellerContactNameFlag() {
		return resellerContactNameFlag;
	}

	public void setResellerContactNameFlag(String resellerContactNameFlag) {
		this.resellerContactNameFlag = resellerContactNameFlag;
	}

	public String getResellerContactEmailFlag() {
		return resellerContactEmailFlag;
	}

	public void setResellerContactEmailFlag(String resellerContactEmailFlag) {
		this.resellerContactEmailFlag = resellerContactEmailFlag;
	}

	public String getResellerContactPhoneFlag() {
		return resellerContactPhoneFlag;
	}

	public void setResellerContactPhoneFlag(String resellerContactPhoneFlag) {
		this.resellerContactPhoneFlag = resellerContactPhoneFlag;
	}

	public String getResellerWebAddressFlag() {
		return resellerWebAddressFlag;
	}

	public void setResellerWebAddressFlag(String resellerWebAddressFlag) {
		this.resellerWebAddressFlag = resellerWebAddressFlag;
	}

	public String getResellerPhysicalAddressFlag() {
		return resellerPhysicalAddressFlag;
	}

	public void setResellerPhysicalAddressFlag(String resellerPhysicalAddressFlag) {
		this.resellerPhysicalAddressFlag = resellerPhysicalAddressFlag;
	}

	public String getCityOrDistrictFlag() {
		return cityOrDistrictFlag;
	}

	public void setCityOrDistrictFlag(String cityOrDistrictFlag) {
		this.cityOrDistrictFlag = cityOrDistrictFlag;
	}

	public String getStateOrRegionFlag() {
		return stateOrRegionFlag;
	}

	public void setStateOrRegionFlag(String stateOrRegionFlag) {
		this.stateOrRegionFlag = stateOrRegionFlag;
	}

	public String getZipCodeFlag() {
		return zipCodeFlag;
	}

	public void setZipCodeFlag(String zipCodeFlag) {
		this.zipCodeFlag = zipCodeFlag;
	}

	public String getMembershipForPcFlag() {
		return membershipForPcFlag;
	}

	public void setMembershipForPcFlag(String membershipForPcFlag) {
		this.membershipForPcFlag = membershipForPcFlag;
	}

	public String getMembershipForPrintFlag() {
		return membershipForPrintFlag;
	}

	public void setMembershipForPrintFlag(String membershipForPrintFlag) {
		this.membershipForPrintFlag = membershipForPrintFlag;
	}

	public String getAmplifyMembershipForSuppliesFlag() {
		return amplifyMembershipForSuppliesFlag;
	}

	public void setAmplifyMembershipForSuppliesFlag(String amplifyMembershipForSuppliesFlag) {
		this.amplifyMembershipForSuppliesFlag = amplifyMembershipForSuppliesFlag;
	}

	public String getQdnStartDateFlag() {
		return qdnStartDateFlag;
	}

	public void setQdnStartDateFlag(String qdnStartDateFlag) {
		this.qdnStartDateFlag = qdnStartDateFlag;
	}

	public String getQdnEndDateFlag() {
		return qdnEndDateFlag;
	}

	public void setQdnEndDateFlag(String qdnEndDateFlag) {
		this.qdnEndDateFlag = qdnEndDateFlag;
	}

	public String getCommentsORNotesFlag() {
		return commentsORNotesFlag;
	}

	public void setCommentsORNotesFlag(String commentsORNotesFlag) {
		this.commentsORNotesFlag = commentsORNotesFlag;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

		@Id
		@Column(name = "unique_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private String id;

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
		
		
		@Column(name = "homologatedNameFlag")
		private String homologatedNameFlag;

		@Column(name = "razonSocialFlag")
		private String razonSocialFlag;

		@Column(name = "dbaFlag")
		private String dbaFlag;

		@Column(name = "taxIdFlag")
		private String taxIdFlag;

		@Column(name = "locationIdFlag")
		private String locationIdFlag;

		@Column(name = "partnerIdFlag")
		private String partnerIdFlag;

		@Column(name = "agreementFlag")
		private String agreementFlag;

		@Column(name = "tierFlag")
		private String tierFlag;

		@Column(name = "suppliesStatusFlag")
		private String suppliesStatusFlag;

		@Column(name = "qdnApplicationFormFlag")
		private String qdnApplicationFormFlag;

		@Column(name = "hpsHardwareStatusFlag")
		private String hpsHardwareStatusFlag;

		@Column(name = "opsHardwareStatusFlag")
		private String opsHardwareStatusFlag;

		@Column(name = "bpsStatusFlag")
		private String bpsStatusFlag;

		@Column(name = "hpContactNameFlag")
		private String hpContactNameFlag;

		@Column(name = "hpContactEmailFlag")
		private String hpContactEmailFlag;

		@Column(name = "resellerContactNameFlag")
		private String resellerContactNameFlag;

		@Column(name = "resellerContactEmailFlag")
		private String resellerContactEmailFlag;

		@Column(name = "resellerContactPhoneFlag")
		private String resellerContactPhoneFlag;

		@Column(name = "resellerWebAddressFlag")
		private String resellerWebAddressFlag;

		@Column(name = "resellerPhysicalAddressFlag")
		private String resellerPhysicalAddressFlag;

		@Column(name = "cityOrDistrictFlag")
		private String cityOrDistrictFlag;

		@Column(name = "stateOrRegionFlag")
		private String stateOrRegionFlag;

		@Column(name = "zipCodeFlag")
		private String zipCodeFlag;

		@Column(name = "membershipForPcFlag")
		private String membershipForPcFlag;

		@Column(name = "membershipForPrintFlag")
		private String membershipForPrintFlag;

		@Column(name = "amplifyMembershipForSuppliesFlag")
		private String amplifyMembershipForSuppliesFlag;

		@Column(name = "qdnStartDateFlag")
		private String qdnStartDateFlag;

		@Column(name = "qdnEndDateFlag")
		private String qdnEndDateFlag;

		@Column(name = "commentsORNotesFlag")
		private String commentsORNotesFlag;

		@Column(name = "comment")
		private String comment;
		
		public String getRequestedOn() {
			return requestedOn;
		}

		public void setRequestedOn(String requestedOn) {
			this.requestedOn = requestedOn;
		}

		public String getRequestedBy() {
			return requestedBy;
		}

		public void setRequestedBy(String requestedBy) {
			this.requestedBy = requestedBy;
		}

		@Column(name = "requestedOn")
		private String requestedOn;
		
		@Column(name = "requestedBy")
		private String requestedBy;

}
