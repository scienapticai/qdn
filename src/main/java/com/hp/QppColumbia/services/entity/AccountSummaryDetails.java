package com.hp.QppColumbia.services.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountSummaryDetails {
	private int id;

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
}
