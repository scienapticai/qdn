package com.hp.QppColumbia.services.service.impl;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao;
import com.hp.QppColumbia.services.dao.AccountSummaryDetailsHistory;
import com.hp.QppColumbia.services.entity.ASFilterResponse;
import com.hp.QppColumbia.services.entity.AccountSummaryFilterDetails;
import com.hp.QppColumbia.services.entity.EditASDetails;
import com.hp.QppColumbia.services.entity.UserDetails;
import com.hp.QppColumbia.services.repository.AccountSummaryHistoryDetails;
import com.hp.QppColumbia.services.repository.AccountSummaryRepo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Comparator;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Entity;

import static java.util.stream.Collectors.toCollection;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

/**
 * @author kovoor.prajwal
 * @Date : January, 2021
 *
 */
@Service
public class DataServiceImpl implements com.hp.QppColumbia.services.service.DataService {

	@Autowired
	AccountSummaryRepo accountSummaryRepo;

	@Autowired
	AccountSummaryHistoryDetails accountSummaryHistoryDetails;

	@Autowired
	com.hp.QppColumbia.services.repository.UserDetailsRepo userDetailsRepo;

	@Override
	public com.hp.QppColumbia.services.entity.UserDetails getUserService(String authUsername, String email) {
		// TODO Auto-generated method stub
		UserDetails user = new UserDetails();
		user.setUsername(authUsername);
		user.setUserEmail(email);
		user.setAuthenticated("true");

		user.setUserRole(userDetailsRepo.findRoleByEmail(email));
		return user;

	}

	@Override
	public List<com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao> getAllRecords() {
		// TODO Auto-generated method stub
		return accountSummaryRepo.findAllRecords();
	}

	@Override
	public com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao getRecordDetails(String homologatedName) {
		// TODO Auto-generated method stub
		return accountSummaryRepo.findRecordDetails(homologatedName);
	}

	@Override
	public boolean editRecord(EditASDetails editRecord) {
		// TODO Auto-generated method stub
		Optional<com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao> as = accountSummaryRepo
				.findRecordDetailsNew(editRecord.getHomologatedName());
		if (!as.isPresent()) {
			return false;
		} else {
			AccountSummaryDetailsDao asddao = as.get();
			AccountSummaryDetailsHistory asdh = new AccountSummaryDetailsHistory();

			String agreement = editRecord.getAgreement();
			if (agreement!=null && !agreement.trim().equals(asddao.getAgreement())) {
				asddao.setAgreement(agreement);
				asdh.setAgreement(agreement);
				asdh.setAgreementFlag("true");
			} else {
				asdh.setAgreement(agreement);
				asdh.setAgreementFlag("false");
			}

			String amplifyMembershipForSupplies = editRecord.getAmplifyMembershipForSupplies();
			if (amplifyMembershipForSupplies!=null && !amplifyMembershipForSupplies.trim().equals(asddao.getAmplifyMembershipForSupplies())) {
				asddao.setAmplifyMembershipForSupplies(amplifyMembershipForSupplies);
				asdh.setAmplifyMembershipForSupplies(amplifyMembershipForSupplies);
				asdh.setAmplifyMembershipForSuppliesFlag("true");
			} else {
				asdh.setAmplifyMembershipForSupplies(amplifyMembershipForSupplies);
				asdh.setAmplifyMembershipForSuppliesFlag("false");
			}

			String bpsStatus = editRecord.getBpsStatus();
			if (bpsStatus!= null && !bpsStatus.trim().equals(asddao.getBpsStatus())) {
				asddao.setBpsStatus(bpsStatus);
				asdh.setBpsStatus(bpsStatus);
				asdh.setBpsStatusFlag("true");
			} else {
				asdh.setBpsStatus(bpsStatus);
				asdh.setBpsStatusFlag("false");
			}

			String cityOrDistrict = editRecord.getCityOrDistrict();
			if (cityOrDistrict !=null && !cityOrDistrict.trim().equals(asddao.getCityOrDistrict())) {
				asddao.setCityOrDistrict(cityOrDistrict);
				asdh.setCityOrDistrict(cityOrDistrict);
				asdh.setCityOrDistrictFlag("true");
			} else {
				asdh.setCityOrDistrict(cityOrDistrict);
				asdh.setCityOrDistrictFlag("false");
			}

			String comment = editRecord.getComment();
			asdh.setComment(comment);

			String commentsORNotes = editRecord.getCommentsORNotes();
			if (commentsORNotes != null && !commentsORNotes.trim().equals(asddao.getCommentsORNotes())) {
				asddao.setCommentsORNotes(commentsORNotes);
				asdh.setCommentsORNotes(commentsORNotes);
				asdh.setCommentsORNotesFlag("true");
			} else {
				asdh.setCommentsORNotes(commentsORNotes);
				asdh.setCommentsORNotesFlag("false");
			}

			String dba = editRecord.getDba();
			if (dba!=null && !dba.trim().equals(asddao.getDba())) {
				asddao.setDba(dba);
				asdh.setDba(dba);
				asdh.setDbaFlag("true");
			} else {
				asdh.setDba(dba);
				asdh.setDbaFlag("false");
			}

			String homologatedName = editRecord.getHomologatedName();
			if (homologatedName !=null && !homologatedName.trim().equals(asddao.getHomologatedName())) {
				asddao.setHomologatedName(homologatedName);
				asdh.setHomologatedName(homologatedName);
				asdh.setHomologatedNameFlag("true");
			} else {
				asdh.setHomologatedName(homologatedName);
				asdh.setHomologatedNameFlag("false");
			}

			String hpContactEmail = editRecord.getHpContactEmail();
			if (hpContactEmail!= null && !hpContactEmail.trim().equals(asddao.getHpContactEmail())) {
				asddao.setHpContactEmail(hpContactEmail);
				asdh.setHpContactEmail(hpContactEmail);
				asdh.setHpContactEmailFlag("true");
			} else {
				asdh.setHpContactEmail(hpContactEmail);
				asdh.setHpContactEmailFlag("false");
			}

			String hpContactName = editRecord.getHpContactName();
			if (hpContactName!=null && !hpContactName.trim().equals(asddao.getHpContactName())) {
				asddao.setHpContactName(hpContactName);
				asdh.setHpContactName(hpContactName);
				asdh.setHpContactNameFlag("true");
			} else {
				asdh.setHpContactName(hpContactName);
				asdh.setHpContactNameFlag("false");
			}

			String hpsHardwareStatus = editRecord.getHpsHardwareStatus();
			if (hpsHardwareStatus!=null && !hpsHardwareStatus.trim().equals(asddao.getHpsHardwareStatus())) {
				asddao.setHpsHardwareStatus(hpsHardwareStatus);
				asdh.setHpsHardwareStatus(hpsHardwareStatus);
				asdh.setHpsHardwareStatusFlag("true");
			} else {
				asdh.setHpsHardwareStatus(hpsHardwareStatus);
				asdh.setHpsHardwareStatusFlag("false");
			}

			String locationId = editRecord.getLocationId();
			if (locationId !=null && !locationId.trim().equals(asddao.getLocationId())) {
				asddao.setLocationId(locationId);
				asdh.setLocationId(locationId);
				asdh.setLocationIdFlag("true");
			} else {
				asdh.setLocationId(locationId);
				asdh.setLocationIdFlag("false");
			}

			String membershipForPc = editRecord.getMembershipForPc();
			if (membershipForPc !=null && !membershipForPc.trim().equals(asddao.getMembershipForPc())) {
				asddao.setMembershipForPc(membershipForPc);
				asdh.setMembershipForPc(membershipForPc);
				asdh.setMembershipForPcFlag("true");
			} else {
				asdh.setMembershipForPc(membershipForPc);
				asdh.setMembershipForPcFlag("false");
			}

			String membershipForPrint = editRecord.getMembershipForPrint();
			if (membershipForPrint!=null && !membershipForPrint.trim().equals(asddao.getMembershipForPrint())) {
				asddao.setMembershipForPrint(membershipForPrint);
				asdh.setMembershipForPrint(membershipForPrint);
				asdh.setMembershipForPrintFlag("true");
			} else {
				asdh.setMembershipForPrint(membershipForPrint);
				asdh.setMembershipForPrintFlag("false");
			}

			String opsHardwareStatus = editRecord.getOpsHardwareStatus();
			if (opsHardwareStatus!=null && !opsHardwareStatus.trim().equals(asddao.getOpsHardwareStatus())) {
				asddao.setOpsHardwareStatus(opsHardwareStatus);
				asdh.setOpsHardwareStatus(opsHardwareStatus);
				asdh.setOpsHardwareStatusFlag("true");
			} else {
				asdh.setOpsHardwareStatus(opsHardwareStatus);
				asdh.setOpsHardwareStatusFlag("false");
			}

			String partnerId = editRecord.getPartnerId();
			if (partnerId!=null && !partnerId.trim().equals(asddao.getPartnerId())) {
				asddao.setPartnerId(partnerId);
				asdh.setPartnerId(partnerId);
				asdh.setPartnerIdFlag("true");
			} else {
				asdh.setPartnerId(partnerId);
				asdh.setPartnerIdFlag("false");
			}

			String qdnApplicationForm = editRecord.getQdnApplicationForm();
			if (qdnApplicationForm!=null && !qdnApplicationForm.trim().equals(asddao.getQdnApplicationForm())) {
				asddao.setQdnApplicationForm(qdnApplicationForm);
				asdh.setQdnApplicationForm(qdnApplicationForm);
				asdh.setQdnApplicationFormFlag("true");
			} else {
				asdh.setQdnApplicationForm(qdnApplicationForm);
				asdh.setQdnApplicationFormFlag("false");
			}

			String qdnEndDate = editRecord.getQdnEndDate();
			if (qdnEndDate!=null && !qdnEndDate.trim().equals(asddao.getQdnEndDate())) {
				asddao.setQdnEndDate(qdnEndDate);
				asdh.setQdnEndDate(qdnEndDate);
				asdh.setQdnEndDateFlag("true");
			} else {
				asdh.setQdnEndDate(qdnEndDate);
				asdh.setQdnEndDateFlag("false");
			}

			String qdnStartDate = editRecord.getQdnStartDate();
			if (qdnStartDate!=null && !qdnStartDate.trim().equals(asddao.getQdnStartDate())) {
				asddao.setQdnStartDate(qdnStartDate);
				asdh.setQdnStartDate(qdnStartDate);
				asdh.setQdnStartDateFlag("true");
			} else {
				asdh.setQdnStartDate(qdnStartDate);
				asdh.setQdnStartDateFlag("false");
			}

			String razonSocial = editRecord.getRazonSocial();
			if (razonSocial!=null && !razonSocial.trim().equals(asddao.getRazonSocial())) {
				asddao.setRazonSocial(razonSocial);
				asdh.setRazonSocial(razonSocial);
				asdh.setRazonSocialFlag("true");
			} else {
				asdh.setRazonSocial(razonSocial);
				asdh.setRazonSocialFlag("false");
			}

			String resellerContactEmail = editRecord.getResellerContactEmail();
			if (resellerContactEmail!=null && !resellerContactEmail.trim().equals(asddao.getResellerContactEmail())) {
				asddao.setResellerContactEmail(resellerContactEmail);
				asdh.setResellerContactEmail(resellerContactEmail);
				asdh.setResellerContactEmailFlag("true");
			} else {
				asdh.setResellerContactEmail(resellerContactEmail);
				asdh.setResellerContactEmailFlag("false");
			}

			String resellerContactName = editRecord.getResellerContactName();
			if (resellerContactName!=null && !resellerContactName.trim().equals(asddao.getResellerContactName())) {
				asddao.setResellerContactName(resellerContactName);
				asdh.setResellerContactName(resellerContactName);
				asdh.setResellerContactNameFlag("true");
			} else {
				asdh.setResellerContactName(resellerContactName);
				asdh.setResellerContactNameFlag("false");
			}

			String resellerContactPhone = editRecord.getResellerContactPhone();
			if (resellerContactPhone!=null && !resellerContactPhone.trim().equals(asddao.getResellerContactPhone())) {
				asddao.setResellerContactPhone(resellerContactPhone);
				asdh.setResellerContactPhone(resellerContactPhone);
				asdh.setResellerContactPhoneFlag("true");
			} else {
				asdh.setResellerContactPhone(resellerContactPhone);
				asdh.setResellerContactPhoneFlag("false");
			}

			String resellerPhysicalAddress = editRecord.getResellerPhysicalAddress();
			if (resellerPhysicalAddress!=null && !resellerPhysicalAddress.trim().equals(asddao.getResellerPhysicalAddress())) {
				asddao.setResellerPhysicalAddress(resellerPhysicalAddress);
				asdh.setResellerPhysicalAddress(resellerPhysicalAddress);
				asdh.setResellerPhysicalAddressFlag("true");
			} else {
				asdh.setResellerPhysicalAddress(resellerPhysicalAddress);
				asdh.setResellerPhysicalAddressFlag("false");
			}

			String resellerWebAddress = editRecord.getResellerWebAddress();
			if (resellerWebAddress!=null && !resellerWebAddress.trim().equals(asddao.getResellerWebAddress())) {
				asddao.setResellerWebAddress(resellerWebAddress);
				asdh.setResellerWebAddress(resellerWebAddress);
				asdh.setResellerWebAddressFlag("true");
			} else {
				asdh.setResellerWebAddress(resellerWebAddress);
				asdh.setResellerWebAddressFlag("false");
			}

			String stateOrRegion = editRecord.getStateOrRegion();
			if (stateOrRegion!=null && !stateOrRegion.trim().equals(asddao.getStateOrRegion())) {
				asddao.setStateOrRegion(stateOrRegion);
				asdh.setStateOrRegion(stateOrRegion);
				asdh.setStateOrRegionFlag("true");
			} else {
				asdh.setStateOrRegion(stateOrRegion);
				asdh.setStateOrRegionFlag("false");
			}

			String suppliesStatus = editRecord.getSuppliesStatus();
			if (suppliesStatus!=null && !suppliesStatus.trim().equals(asddao.getSuppliesStatus())) {
				asddao.setSuppliesStatus(suppliesStatus);
				asdh.setSuppliesStatus(suppliesStatus);
				asdh.setSuppliesStatusFlag("true");
			} else {
				asdh.setSuppliesStatus(suppliesStatus);
				asdh.setSuppliesStatusFlag("false");
			}

			String taxId = editRecord.getTaxId();
			if (taxId!=null && !taxId.trim().equals(asddao.getTaxId())) {
				asddao.setTaxId(taxId);
				asdh.setTaxId(taxId);
				asdh.setTaxIdFlag("true");
			} else {
				asdh.setTaxId(taxId);
				asdh.setTaxIdFlag("false");
			}

			String tier = editRecord.getTier();
			if (tier!=null && !tier.trim().equals(asddao.getTier())) {
				asddao.setTier(tier);
				asdh.setTier(tier);
				asdh.setTierFlag("true");
			} else {
				asdh.setTier(tier);
				asdh.setTierFlag("false");
			}

			String zipCode = editRecord.getZipCode();
			if (zipCode!=null && !zipCode.trim().equals(asddao.getZipCode())) {
				asddao.setZipCode(zipCode);
				asdh.setZipCode(zipCode);
				asdh.setZipCodeFlag("true");
			} else {
				asdh.setZipCode(zipCode);
				asdh.setZipCodeFlag("false");
			}
			asdh.setRequestedBy(editRecord.getRequestedBy());
			asdh.setRequestedOn(editRecord.getRequestedOn());

			accountSummaryRepo.save(asddao);
			accountSummaryHistoryDetails.save(asdh);
			return true;
		}

	}

	@Override
	public List<AccountSummaryDetailsHistory> getAllHistoryRecords(String homologatedName) {
		// TODO Auto-generated method stub
		return accountSummaryHistoryDetails.findAllRecords(homologatedName);
	}

	@Override
	public List<AccountSummaryDetailsHistory> getAllTheHistoryRecords() {
		// TODO Auto-generated method stub
		return accountSummaryHistoryDetails.findAllTheRecords();
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<AccountSummaryDetailsDao> getAllFilteredRecords(AccountSummaryFilterDetails searchByFields) {
		// TODO Auto-generated method stub
		boolean checkSearchStatus = false;
		List<AccountSummaryDetailsDao> allRevenue = new ArrayList<>();
		boolean andflag = false;
		if (!StringUtils.isEmpty(searchByFields.getHomologatedName())) {
			checkSearchStatus = true;
			List<AccountSummaryDetailsDao> udd = accountSummaryRepo
					.findAllRecordsByHN(searchByFields.getHomologatedName());
			if (allRevenue.size() != 0) {
				andflag = true;
				allRevenue.retainAll(udd);
			} else if (!andflag) {
				allRevenue.addAll(udd);
			}
		}
		if (!StringUtils.isEmpty(searchByFields.getLocationId())) {
			checkSearchStatus = true;
			List<AccountSummaryDetailsDao> udd = accountSummaryRepo
					.findAllRecordsByLocationId(searchByFields.getLocationId());
			if (allRevenue.size() != 0) {
				andflag = true;
				allRevenue.retainAll(udd);
			} else if (!andflag) {
				allRevenue.addAll(udd);
			}
		}
		if (!StringUtils.isEmpty(searchByFields.getRazonSocial())) {
			checkSearchStatus = true;
			List<AccountSummaryDetailsDao> udd = accountSummaryRepo
					.findAllRecordsByRazonSocial(searchByFields.getRazonSocial());
			if (allRevenue.size() != 0) {
				andflag = true;
				allRevenue.retainAll(udd);
			} else if (!andflag) {
				allRevenue.addAll(udd);
			}
		}
		if (!StringUtils.isEmpty(searchByFields.getSuppliesStatus())) {
			checkSearchStatus = true;
			List<AccountSummaryDetailsDao> udd = accountSummaryRepo
					.findAllRecordsBySuppliesStatus(searchByFields.getSuppliesStatus());
			if (allRevenue.size() != 0) {
				andflag = true;
				allRevenue.retainAll(udd);
			} else if (!andflag) {
				allRevenue.addAll(udd);
			}
		}
		if (!StringUtils.isEmpty(searchByFields.getTaxId())) {
			checkSearchStatus = true;
			List<AccountSummaryDetailsDao> udd = accountSummaryRepo.findAllRecordsByTaxId(searchByFields.getTaxId());
			if (allRevenue.size() != 0) {
				andflag = true;
				allRevenue.retainAll(udd);
			} else if (!andflag) {
				allRevenue.addAll(udd);
			}
		}
		if (!checkSearchStatus) {
			return getAllRecords();
		} else {
			return allRevenue.stream()
					.collect(Collectors.collectingAndThen(toCollection(
							() -> new TreeSet<>(Comparator.comparingInt(AccountSummaryDetailsDao::getUnique_id))),
							ArrayList::new));
		}
	}

	public ASFilterResponse getFilterDetails() {

		ASFilterResponse ufdr = new ASFilterResponse();

		List<String> markets = accountSummaryRepo.findallHM();
		if (markets != null && !markets.contains(null))
			markets.sort(Comparator.naturalOrder());
		ufdr.setHomologatedName(markets);

		List<String> pNames = accountSummaryRepo.findallRS();
		if (pNames != null && !pNames.contains(null))
			pNames.sort(Comparator.naturalOrder());
		ufdr.setRazonSocial(pNames);

		List<String> siteNames = accountSummaryRepo.findallTI();
		if (siteNames != null && !siteNames.contains(null))
			siteNames.sort(Comparator.naturalOrder());
		ufdr.setTaxId(siteNames);

		List<String> allTypes = accountSummaryRepo.findallLocationId();
		if (allTypes != null && !allTypes.contains(null))
			allTypes.sort(Comparator.naturalOrder());
		ufdr.setLocationId(allTypes);

		List<String> mPId = accountSummaryRepo.findallSuppliesStatus();
		if (mPId != null && !mPId.contains(null))
			mPId.sort(Comparator.naturalOrder());
		ufdr.setSuppliesStatus(mPId);

		return ufdr;
	}

	@Override
	public String uploadRecords(MultipartFile attachment, String submittedBy, String submittedOn, String comment) throws IOException {

		InputStream inputStream = new BufferedInputStream(attachment.getInputStream());
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);

		Map<Integer, List<String>> data = new HashMap<>();
		int k = 0;
		for (Row row : sheet) {
			/* data.put(i, new ArrayList<String>()); */
			int i = 0;
			AccountSummaryDetailsDao asddao = new AccountSummaryDetailsDao();
			boolean repeat = false;
			AccountSummaryDetailsHistory asdh = new AccountSummaryDetailsHistory();
			if (k == 0) {
				boolean isValid = true;
			for (Cell cell : row) {

				
					
					if (i == 0 && !getValueOf(cell).equals("homologatedName")) {
						isValid = false;
					} else if (i == 1 && !getValueOf(cell).equals("razonSocial")) {
						isValid = false;
					} else if (i == 2 && !getValueOf(cell).equals("dba")) {
						isValid = false;
					} else if (i == 3 && !getValueOf(cell).equals("taxId")) {
						isValid = false;
					} else if (i == 4 && !getValueOf(cell).equals("locationId")) {
						isValid = false;
					} else if (i == 5 && !getValueOf(cell).equals("partnerId")) {
						isValid = false;
					} else if (i == 6 && !getValueOf(cell).equals("agreement")) {
						isValid = false;
					} else if (i == 7 && !getValueOf(cell).equals("tier")) {
						isValid = false;
					} else if (i == 8 && !getValueOf(cell).equals("suppliesStatus")) {
						isValid = false;
					} else if (i == 9 && !getValueOf(cell).equals("qdnApplicationForm")) {
						isValid = false;
					} else if (i == 10 && !getValueOf(cell).equals("hpsHardwareStatus")) {
						isValid = false;
					} else if (i == 11 && !getValueOf(cell).equals("opsHardwareStatus")) {
						isValid = false;
					} else if (i == 12 && !getValueOf(cell).equals("bpsStatus")) {
						isValid = false;
					} else if (i == 13 && !getValueOf(cell).equals("hpContactName")) {
						isValid = false;
					} else if (i == 14 && !getValueOf(cell).equals("hpContactEmail")) {
						isValid = false;
					} else if (i == 15 && !getValueOf(cell).equals("resellerContactName")) {
						isValid = false;
					} else if (i == 16 && !getValueOf(cell).equals("resellerContactEmail")) {
						isValid = false;
					} else if (i == 17 && !getValueOf(cell).equals("resellerContactPhone")) {
						isValid = false;
					} else if (i == 18 && !getValueOf(cell).equals("resellerWebAddress")) {
						isValid = false;
					} else if (i == 19 && !getValueOf(cell).equals("resellerPhysicalAddress")) {
						isValid = false;
					} else if (i == 20 && !getValueOf(cell).equals("cityOrDistrict")) {
						isValid = false;
					} else if (i == 21 && !getValueOf(cell).equals("stateOrRegion")) {
						isValid = false;
					} else if (i == 22 && !getValueOf(cell).equals("zipCode")) {
						isValid = false;
					} else if (i == 23 && !getValueOf(cell).equals("membershipForPc")) {
						isValid = false;
					} else if (i == 24 && !getValueOf(cell).equals("membershipForPrint")) {
						isValid = false;
					} else if (i == 25 && !getValueOf(cell).equals("amplifyMembershipForSupplies")) {
						isValid = false;
					} else if (i == 26 && !getValueOf(cell).equals("qdnStartDate")) {
						isValid = false;
					} else if (i == 27 && !getValueOf(cell).equals("qdnEndDate")) {
						isValid = false;
					} else if (i == 28 && !getValueOf(cell).equals("commentsORNotes")) {
						isValid = false;
					}
					if (!isValid) {
						k++;
						return "Invalid Column Names";
					}
					i++;
					
					
				}
				k++;
			}

			else {
				for (Cell cell : row) {

				if (cell.getColumnIndex() == 0) {
					if (accountSummaryRepo.findAllRecordsByHN(getValueOf(cell)).size() != 0) {
                        repeat = true;  
                        k++;
						break;
						
					}
					
					asddao.setHomologatedName(getValueOf(cell));
					asdh.setHomologatedName(getValueOf(cell));
i++;
continue;

				}
				if (cell.getColumnIndex()== 1) {
					asddao.setRazonSocial(getValueOf(cell));
					asdh.setRazonSocial(getValueOf(cell));
					i++;
					k++;
					continue;
				}
				if (cell.getColumnIndex() == 2) {
					asddao.setDba(getValueOf(cell));
					asdh.setDba(getValueOf(cell));
					i++;k++;	
					continue;
				}
				if (cell.getColumnIndex() == 3) {
					asddao.setTaxId(getValueOf(cell));
					asdh.setTaxId(getValueOf(cell));	
					i++;
					k++;
					continue;
				}
				
				if (cell.getColumnIndex() == 4) {
					
					asddao.setLocationId(getValueOf(cell));
					asdh.setLocationId(getValueOf(cell));
					i++;
					k++;
					continue;
				}
				if (cell.getColumnIndex() == 5) {
					asddao.setPartnerId(getValueOf(cell));
					asdh.setPartnerId(getValueOf(cell));
					i++;
					k++;
					continue;
				}
				if (cell.getColumnIndex() == 6) {
					asddao.setAgreement(getValueOf(cell));
					asdh.setAgreement(getValueOf(cell));
					i++;
					k++;
					continue;
				}if (cell.getColumnIndex() == 7) {
					asddao.setTier(getValueOf(cell));
					asdh.setTier(getValueOf(cell));
					i++;
					k++;
					continue;
				}if (cell.getColumnIndex() == 8) {
					asddao.setSuppliesStatus(getValueOf(cell));
					asdh.setSuppliesStatus(getValueOf(cell));
					i++;
					k++;
					continue;
				}if (cell.getColumnIndex() == 9) {
					asddao.setQdnApplicationForm(getValueOf(cell));
					asdh.setQdnApplicationForm(getValueOf(cell));
					i++;
					k++;
					continue;
				}if (cell.getColumnIndex() == 10) {
					asddao.setHpsHardwareStatus(getValueOf(cell));
					asdh.setHpsHardwareStatus(getValueOf(cell));
					i++;	
					k++;
					continue;
				}if (cell.getColumnIndex() == 11) {
					asddao.setOpsHardwareStatus(getValueOf(cell));
					asdh.setOpsHardwareStatus(getValueOf(cell));
					i++;	
					k++;
					continue;
				}if (cell.getColumnIndex() == 12) {
					asddao.setBpsStatus(getValueOf(cell));
					asdh.setBpsStatus(getValueOf(cell));
					i++;	
					k++;
					continue;
				}if (cell.getColumnIndex() == 13) {
					asddao.setHpContactName(getValueOf(cell));
					asdh.setHpContactName(getValueOf(cell));
					i++;	
					k++;
					continue;
				}if (cell.getColumnIndex() == 14) {
					asddao.setHpContactEmail(getValueOf(cell));
					asdh.setHpContactEmail(getValueOf(cell));
					i++;
					k++;
					continue;
				}if (cell.getColumnIndex()== 15) {
					asddao.setResellerContactName(getValueOf(cell));
					asdh.setResellerContactName(getValueOf(cell));
					i++;	
					k++;
					continue;
					
				}if (cell.getColumnIndex() == 16) {
					asddao.setResellerContactEmail(getValueOf(cell));
					asdh.setResellerContactEmail(getValueOf(cell));
					i++;	
					k++;
					continue;
				}if (cell.getColumnIndex() == 17) {
					asddao.setResellerContactPhone(getValueOf(cell));
					asdh.setResellerContactPhone(getValueOf(cell));
					i++;	
					k++;
					continue;
				}if (cell.getColumnIndex() == 18) {
					asddao.setResellerWebAddress(getValueOf(cell));
					asdh.setResellerWebAddress(getValueOf(cell)); 
					i++;	
					k++;
					continue;
				}if (cell.getColumnIndex() == 19) {
					asddao.setResellerPhysicalAddress(getValueOf(cell));
					asdh.setResellerPhysicalAddress(getValueOf(cell));
					i++;	
					k++;
					continue;
				}if (cell.getColumnIndex() == 20) {
					asddao.setCityOrDistrict(getValueOf(cell));
					asdh.setCityOrDistrict(getValueOf(cell));
					i++;	
					k++;
					continue;
				}if (cell.getColumnIndex() == 21) {
					asddao.setStateOrRegion(getValueOf(cell));
					asdh.setStateOrRegion(getValueOf(cell));
					i++;	
					k++;
					continue;
				}if (cell.getColumnIndex() == 22) {
					asddao.setZipCode(getValueOf(cell));
					asdh.setZipCode(getValueOf(cell));
					i++;
					k++;
					continue;
				}if (cell.getColumnIndex() == 23) {
					asddao.setMembershipForPc(getValueOf(cell));
					asdh.setMembershipForPc(getValueOf(cell));
					i++;
					k++;
					continue;
				}if (cell.getColumnIndex() == 24) {
					asddao.setMembershipForPrint(getValueOf(cell));
					asdh.setMembershipForPrint(getValueOf(cell));
					i++;
					k++;
					continue;
				}if (cell.getColumnIndex() == 25) {
					asddao.setAmplifyMembershipForSupplies(getValueOf(cell));
					asdh.setAmplifyMembershipForSupplies(getValueOf(cell));
					i++;
					k++;
					continue;
				}if (cell.getColumnIndex() == 26) {
					asddao.setQdnStartDate(getValueOf(cell));
					asdh.setQdnStartDate(getValueOf(cell));
					i++;	
					k++;
					continue;
				}if (cell.getColumnIndex() == 27) {
					asddao.setQdnEndDate(getValueOf(cell));
					asdh.setQdnEndDate(getValueOf(cell));
					i++;	
					k++;
					continue;
				}if (cell.getColumnIndex() == 28) {
					asddao.setCommentsORNotes(getValueOf(cell));
					asdh.setCommentsORNotes(getValueOf(cell));

					i++;	
					k++;
					continue;
					
					
				}
				
			
				
				}
			if(k!=1 && !repeat) {
			asdh.setAgreementFlag("true");
			asdh.setAmplifyMembershipForSuppliesFlag("true");
			asdh.setBpsStatusFlag("true");
			asdh.setCityOrDistrictFlag("true");
			asdh.setTierFlag("true");
			asdh.setCommentsORNotesFlag("true");
			asdh.setDbaFlag("true");
			asdh.setHomologatedNameFlag("true");
			asdh.setHpContactEmailFlag("true");
			asdh.setHpContactNameFlag("true");
			asdh.setHpsHardwareStatusFlag("true");
			asdh.setLocationIdFlag("true");
			asdh.setMembershipForPcFlag("true");
			asdh.setMembershipForPrintFlag("true");
			asdh.setOpsHardwareStatusFlag("true");
			asdh.setPartnerIdFlag("true");
			asdh.setQdnApplicationFormFlag("true");
			asdh.setQdnEndDateFlag("true");
			asdh.setQdnStartDateFlag("true");
			asdh.setRazonSocialFlag("true");
			asdh.setResellerContactEmailFlag("true");
			asdh.setResellerContactNameFlag("true");
			asdh.setResellerContactPhoneFlag("true");
			asdh.setResellerPhysicalAddressFlag("true");
			asdh.setResellerWebAddressFlag("true");
			asdh.setStateOrRegionFlag("true");
			asdh.setSuppliesStatusFlag("true");
			asdh.setTaxIdFlag("true");
			asdh.setZipCodeFlag("true");
			
			
			asdh.setRequestedBy(submittedBy==null ? "": submittedBy );
			asdh.setRequestedOn(submittedOn==null ? "": submittedOn);
			asdh.setComment(comment==null ? "": comment);
			
			if(!(asddao.getHomologatedName()== null ||asddao.getHomologatedName().trim().equals(""))) {
			
			accountSummaryHistoryDetails.save(asdh);
			accountSummaryRepo.save(asddao);
			}
		
			}
		}

		}		


		return "true";

	}

	/*
	 * @Autowired DataRepository dataRepository;
	 * 
	 * @Autowired UrlRepo urlRepo;
	 * 
	 * @Autowired KeywordRepo keywordRepo;
	 * 
	 * @Autowired ModifyUrlRepo modifyUrlRepo;
	 * 
	 * @Autowired ModifyKeywordRepo modifyKeywordRepo;
	 * 
	 * @Autowired DeleteKeywordRepo deleteKeywordRepo;
	 * 
	 * @Autowired DeleteUrlRepo deleteurlRepo;
	 * 
	 * @Autowired AddUrlRepo addUrlRepo;
	 * 
	 * @Autowired AddKeywordRepo addKeywordRepo;
	 * 
	 * @Autowired com.hp.BMT.services.repository.KeywordHistoryRepo
	 * keywordHistoryRepo;
	 * 
	 * @Autowired SpringsRepo springsRepo;
	 * 
	 * @Autowired com.hp.BMT.services.repository.UrlHistoryRepo urlHistoryRepo;
	 * 
	 * @Autowired MCLRepo mclRepo;
	 * 
	 * @Autowired CSRepo csRepo;
	 * 
	 * @Autowired UserDetailsRepo userRepo;
	 * 
	 * @Autowired KwSerialIdRepo kwSerialIdRepo;
	 * 
	 * @SuppressWarnings("unchecked") public UrlFiltterDetailsResponse
	 * getUrlFilterDetails() {
	 * 
	 * UrlFiltterDetailsResponse ufdr = new UrlFiltterDetailsResponse();
	 * 
	 * List<String> markets = urlRepo.findallMarkets(); if (markets != null &&
	 * !markets.contains(null)) markets.sort(Comparator.naturalOrder());
	 * ufdr.setMarkets(markets);
	 * 
	 * List<String> pNames = urlRepo.findallPartnerNames(); if (pNames != null &&
	 * !pNames.contains(null)) pNames.sort(Comparator.naturalOrder());
	 * ufdr.setPartnerNames(pNames);
	 * 
	 * List<String> siteNames = urlRepo.findallSiteNames(); if (siteNames != null &&
	 * !siteNames.contains(null)) siteNames.sort(Comparator.naturalOrder());
	 * ufdr.setSiteNames(siteNames);
	 * 
	 * List<String> allTypes = urlRepo.findallTypes(); if (allTypes != null &&
	 * !allTypes.contains(null)) allTypes.sort(Comparator.naturalOrder());
	 * ufdr.setTypes(allTypes);
	 * 
	 * List<String> mPId = urlRepo.findall﻿MasterPartnerID(); if (mPId != null &&
	 * !mPId.contains(null)) mPId.sort(Comparator.naturalOrder());
	 * ufdr.setMasterPartnerIDs(mPId);
	 * 
	 * List<String> allCountry = urlRepo.findallCOUNTRYs(); if (allCountry != null
	 * && !allCountry.contains(null)) allCountry.sort(Comparator.naturalOrder());
	 * ufdr.setCOUNTRYs(allCountry);
	 * 
	 * List<String> allPA = urlRepo.findallPrimaryActives(); if (allPA != null &&
	 * !allPA.contains(null)) allPA.sort(Comparator.naturalOrder());
	 * ufdr.setPrimaryActives(allPA);
	 * 
	 * List<String> allAmp = urlRepo.findallAmplifys(); if (allAmp != null &&
	 * !allAmp.contains(null)) allAmp.sort(Comparator.naturalOrder());
	 * ufdr.setAmplifys(allAmp);
	 * 
	 * List<String> allPL = urlRepo.findallPrimaryLanguage(); if (allPL != null &&
	 * !allPL.contains(null)) allPL.sort(Comparator.naturalOrder());
	 * ufdr.setPrimaryLanguage(allPL); return ufdr; }
	 * 
	 * public KeywordDetailsResponse getKeywordFilterDetails() {
	 * 
	 * KeywordDetailsResponse ufdr = new KeywordDetailsResponse();
	 * 
	 * List<String> kwIdSerials = keywordRepo.findallKWIdSerials();
	 * kwIdSerials.sort(Comparator.naturalOrder()); //
	 * ufdr.setKWIdSerials(keywordRepo.findallKWIdSerials());
	 * 
	 * ufdr.setPartnerMarkets(keywordRepo.findallPartnerMarkets());
	 * ufdr.setCountries(keywordRepo.findallCountries());
	 * ufdr.setKeywordTypes(keywordRepo.findallKeywordTypes());
	 * ufdr.setMasterKeywordTexts(keywordRepo.findall﻿KeywordText()); //
	 * ufdr.setMasterKeywordTexts(new ArrayList<String>(Arrays.asList("")));
	 * ufdr.setKWLanguages(keywordRepo.findallKWLanguages());
	 * ufdr.setCategories(keywordRepo.findallCategories());
	 * ufdr.setSubCategories(keywordRepo.findallSubCategories());
	 * ufdr.setKeywordType3(keywordRepo.findallKeywordType3());
	 * 
	 * return ufdr; }
	 * 
	 * public void ModifyUrlDetailsService(MultipartFile attachment,
	 * ModifyUrlDetails urlDetails) throws IOException {
	 * com.hp.BMT.services.dao.ModifyUrlDetails mud = new
	 * com.hp.BMT.services.dao.ModifyUrlDetails();
	 * mud.setActual_id(urlDetails.getId()); mud.setActive(urlDetails.getActive());
	 * mud.setAmplify(urlDetails.getAmplify());
	 * mud.setComment(urlDetails.getComment());
	 * mud.setCountry(urlDetails.getCountry());
	 * mud.setDetailedPartnerId(urlDetails.getDetailedPartnerId());
	 * mud.setManager(urlDetails.getManager());
	 * mud.setMarket(urlDetails.getMarket());
	 * mud.setMasterPartnerID(urlDetails.getMasterPartnerID());
	 * mud.setOfficialPartnerName(urlDetails.getOfficialPartnerName());
	 * mud.setPrimaryLanguage(urlDetails.getPrimaryLanguage());
	 * mud.setRequestedDate(urlDetails.getRequestedDate());
	 * mud.setRequesterEmail(urlDetails.getRequesterEmail());
	 * mud.setRequesterName(urlDetails.getRequesterName());
	 * mud.setSiteName(urlDetails.getSiteName()); mud.setType(urlDetails.getType());
	 * mud.setAttachment(attachment.getBytes()); modifyUrlRepo.save(mud);
	 * 
	 * com.hp.BMT.services.dao.UrlDetailsHistory udh = new
	 * com.hp.BMT.services.dao.UrlDetailsHistory();
	 * 
	 * udh.setActualId(urlDetails.getId()); udh.setActive(urlDetails.getActive());
	 * udh.setAmplify(urlDetails.getAmplify());
	 * udh.setComment(urlDetails.getComment());
	 * udh.setCountry(urlDetails.getCountry());
	 * udh.setDetailedPartnerId(urlDetails.getDetailedPartnerId());
	 * udh.setManager(urlDetails.getManager());
	 * udh.setMarket(urlDetails.getMarket());
	 * udh.setMasterPartnerID(urlDetails.getMasterPartnerID());
	 * udh.setOfficialPartnerName(urlDetails.getOfficialPartnerName());
	 * udh.setPrimaryLanguage(urlDetails.getPrimaryLanguage());
	 * udh.setRequestedOn(urlDetails.getRequestedDate());
	 * udh.setRequesterEmail(urlDetails.getRequesterEmail());
	 * udh.setRequestedBy(urlDetails.getRequesterName());
	 * udh.setSiteName(urlDetails.getSiteName()); udh.setType(urlDetails.getType());
	 * udh.setAttachment(attachment.getBytes()); udh.setApprovernm("");
	 * 
	 * 
	 * udh.setAttachment(attachment.getBytes());
	 * udh.setActive(urlDetails.getActive());
	 * udh.setAmplify(urlDetails.getAmplify());
	 * //udh.setComment(urlDetails.getComment());
	 * udh.setCountry(urlDetails.getCountry());
	 * udh.setDetailedPartnerId(urlDetails.getDetailedPartnerId());
	 * udh.setMarket(urlDetails.getMarket());
	 * udh.setMasterPartnerID(urlDetails.getMasterPartnerID());
	 * udh.setOfficialPartnerName(urlDetails.getOfficialPartnerName());
	 * udh.setPrimaryLanguage(urlDetails.getPrimaryLanguage());
	 * udh.setType(urlDetails.getType());
	 * udh.setRequestedBy(urlDetails.getRequesterName());
	 * udh.setRequestedOn(urlDetails.getRequestedDate());
	 * udh.setSiteName(urlDetails.getSiteName()); udh.setStatus("Pending");
	 * 
	 * udh.setStatus("Pending"); udh.setRequestType("Modify");
	 * springsRepo.insertWithEntityManager(udh); }
	 * 
	 * public void ModifyKeywordDetailsService( MultipartFile attachment,
	 * com.hp.BMT.services.entity.ModifyKeywordDetails keywordDetails) throws
	 * IOException { com.hp.BMT.services.dao.ModifyKeywordDetails mud = new
	 * com.hp.BMT.services.dao.ModifyKeywordDetails();
	 * mud.setActual_id(keywordDetails.getId()); // mud.setAttachment(/*
	 * attachment.getBytes()
	 */

	/*
	 * );mud.setCategory(keywordDetails.getCategory());mud.setComment(keywordDetails
	 * .getComment());mud.setCountry(keywordDetails.getCountry());mud.
	 * setHpOptionsScoring(keywordDetails.getHpOptionsScoring());mud.
	 * setHpTotalOptions(keywordDetails.getHpTotalOptions());mud.setKeywordText(
	 * keywordDetails.getKeywordText());mud.setKeywordType3(keywordDetails.
	 * getKeywordType3());mud.setLanguage(keywordDetails.getLanguage());mud.
	 * setManager(keywordDetails.getManager());mud.setMarket(keywordDetails.
	 * getMarket());mud.setRequestedDate(keywordDetails.getRequestedDate());mud.
	 * setRequesterEmail(keywordDetails.getRequesterEmail());mud.setRequesterName(
	 * keywordDetails.getRequesterName());mud.setSubCategory(keywordDetails.
	 * getSubCategory());mud.setKeywordType(keywordDetails.getKeywordType());mud.
	 * setKeywordId(keywordDetails.getKeywordId());modifyKeywordRepo.save(mud);
	 * com.hp.BMT.services.dao.KeywordDetailsHistory kdh = new
	 * com.hp.BMT.services.dao.KeywordDetailsHistory();kdh.setActualId(
	 * keywordDetails.getId()); // kdh.setAttachment(attachment.getBytes());
	 * kdh.setCategory(keywordDetails.getCategory());kdh.setComment(keywordDetails.
	 * getComment());kdh.setCountry(keywordDetails.getCountry());kdh.
	 * setHpOptionsScoring(keywordDetails.getHpOptionsScoring());kdh.
	 * setHpTotalOptions(keywordDetails.getHpTotalOptions());kdh.setKeywordText(
	 * keywordDetails.getKeywordText());kdh.setKeywordType3(keywordDetails.
	 * getKeywordType3());kdh.setLanguage(keywordDetails.getLanguage());kdh.
	 * setManager(keywordDetails.getManager());kdh.setMarket(keywordDetails.
	 * getMarket());kdh.setRequestedOn(keywordDetails.getRequestedDate());kdh.
	 * setRequesterEmail(keywordDetails.getRequesterEmail());kdh.setRequestedBy(
	 * keywordDetails.getRequesterName());kdh.setSubCategory(keywordDetails.
	 * getSubCategory());kdh.setKeywordType(keywordDetails.getKeywordType());kdh.
	 * setKeywordId(keywordDetails.getKeywordId());kdh.setRequestType("Modify");kdh.
	 * setStatus("Pending");keywordHistoryRepo.save(kdh); }
	 * 
	 * public void DeleteKeywordDetailsService(MultipartFile attachment,
	 * com.hp.BMT.services.entity.DeleteKeywordDetails keywordDetails) throws
	 * IOException { com.hp.BMT.services.dao.DeleteKeywordDetails mud = new
	 * com.hp.BMT.services.dao.DeleteKeywordDetails();
	 * mud.setActual_id(keywordDetails.getId());
	 * mud.setAttachment(attachment.getBytes());
	 * mud.setCategory(keywordDetails.getCategory());
	 * mud.setComments(keywordDetails.getComment());
	 * mud.setCountry(keywordDetails.getCountry());
	 * mud.setHpOptionsScoring(keywordDetails.getHpOptionsScoring());
	 * mud.setHpTotalOptions(keywordDetails.getHpTotalOptions());
	 * mud.setKeywordId(keywordDetails.getKeywordId());
	 * mud.setKeywordText(keywordDetails.getKeywordText());
	 * mud.setKeywordType3(keywordDetails.getKeywordType3());
	 * mud.setLanguage(keywordDetails.getLanguage());
	 * mud.setManager(keywordDetails.getManager());
	 * mud.setMarket(keywordDetails.getMarket());
	 * mud.setRequestedDate(keywordDetails.getRequestedDate());
	 * mud.setRequesterEmail(keywordDetails.getRequesterEmail());
	 * mud.setRequesterName(keywordDetails.getRequesterName());
	 * mud.setSubCategory(keywordDetails.getSubCategory());
	 * mud.setKeywordType(keywordDetails.getKeywordType());
	 * mud.setKeywordId(keywordDetails.getKeywordId()); deleteKeywordRepo.save(mud);
	 * com.hp.BMT.services.dao.KeywordDetailsHistory kdh = new
	 * com.hp.BMT.services.dao.KeywordDetailsHistory();
	 * kdh.setActualId(keywordDetails.getId());
	 * kdh.setAttachment(attachment.getBytes());
	 * kdh.setCategory(keywordDetails.getCategory());
	 * kdh.setComment(keywordDetails.getComment());
	 * kdh.setCountry(keywordDetails.getCountry());
	 * kdh.setHpOptionsScoring(keywordDetails.getHpOptionsScoring());
	 * kdh.setHpTotalOptions(keywordDetails.getHpTotalOptions());
	 * kdh.setKeywordId(keywordDetails.getKeywordId());
	 * kdh.setKeywordText(keywordDetails.getKeywordText());
	 * kdh.setKeywordType3(keywordDetails.getKeywordType3());
	 * kdh.setLanguage(keywordDetails.getLanguage());
	 * kdh.setManager(keywordDetails.getManager());
	 * kdh.setMarket(keywordDetails.getMarket());
	 * kdh.setRequestedOn(keywordDetails.getRequestedDate());
	 * kdh.setRequesterEmail(keywordDetails.getRequesterEmail());
	 * kdh.setRequestedBy(keywordDetails.getRequesterName());
	 * kdh.setSubCategory(keywordDetails.getSubCategory());
	 * kdh.setRequestType("Delete"); kdh.setStatus("Pending");
	 * kdh.setKeywordType(keywordDetails.getKeywordType());
	 * kdh.setKeywordId(keywordDetails.getKeywordId());
	 * keywordHistoryRepo.save(kdh);
	 * 
	 * }
	 * 
	 * public void AddKeywordDetailsService( MultipartFile attachment,
	 * com.hp.BMT.services.entity.AddKeywordDetails keywordDetails) throws
	 * IOException { com.hp.BMT.services.dao.AddKeywordDetails mud = new
	 * com.hp.BMT.services.dao.AddKeywordDetails(); //
	 * mud.setActual_id(keywordDetails.getId()); //
	 * mud.setAttachment(attachment.getBytes());
	 * mud.setCategory(keywordDetails.getCategory());
	 * mud.setComment(keywordDetails.getComment());
	 * mud.setCountry(keywordDetails.getCountry()); //
	 * mud.setHpOptionsScoring(keywordDetails.getHpOptionsScoring()); //
	 * mud.setHpTotalOptions(keywordDetails.getHpTotalOptions()); // mud.set("N/A");
	 * mud.setKeywordText(keywordDetails.getKeywordText());
	 * mud.setKeywordType(null);
	 * mud.setKeywordType3(keywordDetails.getKeywordType3());
	 * mud.setLanguage(keywordDetails.getLanguage());
	 * mud.setManager(keywordDetails.getManager());
	 * mud.setMarket(keywordDetails.getMarket());
	 * mud.setRequestedDate(keywordDetails.getRequestedDate());
	 * mud.setRequesterEmail(keywordDetails.getRequesterEmail());
	 * mud.setRequesterName(keywordDetails.getRequesterName());
	 * mud.setSubCategory(keywordDetails.getSubCategory());
	 * addKeywordRepo.save(mud); com.hp.BMT.services.dao.KeywordDetailsHistory kdh =
	 * new com.hp.BMT.services.dao.KeywordDetailsHistory(); //
	 * kdh.setAttachment(attachment.getBytes());
	 * kdh.setCategory(keywordDetails.getCategory());
	 * kdh.setComment(keywordDetails.getComment());
	 * kdh.setCountry(keywordDetails.getCountry());
	 * 
	 * kdh.setHpOptionsScoring(keywordDetails.getHpOptionsScoring());
	 * kdh.setHpTotalOptions(keywordDetails.getHpTotalOptions());
	 * 
	 * 
	 * kdh.setKeywordId("N/A"); kdh.setKeywordText(keywordDetails.getKeywordText());
	 * kdh.setKeywordType3(keywordDetails.getKeywordType3());
	 * kdh.setLanguage(keywordDetails.getLanguage());
	 * kdh.setManager(keywordDetails.getManager());
	 * kdh.setMarket(keywordDetails.getMarket());
	 * kdh.setRequestedOn(keywordDetails.getRequestedDate());
	 * kdh.setRequesterEmail(keywordDetails.getRequesterEmail());
	 * kdh.setRequestedBy(keywordDetails.getRequesterName());
	 * kdh.setSubCategory(keywordDetails.getSubCategory());
	 * kdh.setRequestType("Add"); kdh.setStatus("Pending");
	 * keywordHistoryRepo.save(kdh); }
	 * 
	 * public void DeleteUrlDetailsService(MultipartFile attachment,
	 * com.hp.BMT.services.entity.DeleteUrlDetails urlDetails) throws IOException {
	 * com.hp.BMT.services.dao.DeleteUrlDetails mud = new
	 * com.hp.BMT.services.dao.DeleteUrlDetails();
	 * mud.setActual_id(urlDetails.getId());
	 * mud.setAttachment(attachment.getBytes());
	 * 
	 * mud.setComment(urlDetails.getComment());
	 * mud.setCountry(urlDetails.getCountry());
	 * 
	 * mud.setManager(urlDetails.getManager());
	 * mud.setMarket(urlDetails.getMarket());
	 * mud.setRequestedDate(urlDetails.getRequestedDate());
	 * mud.setRequesterEmail(urlDetails.getRequesterEmail());
	 * mud.setRequesterName(urlDetails.getRequesterName());
	 * mud.setActive(urlDetails.getActive());
	 * mud.setAmplify(urlDetails.getAmplify());
	 * mud.setDetailedPartnerId(urlDetails.getDetailedPartnerId());
	 * mud.setMasterPartnerID(urlDetails.getMasterPartnerID());
	 * mud.setOfficialPartnerName(urlDetails.getOfficialPartnerName());
	 * mud.setPrimaryLanguage(urlDetails.getPrimaryLanguage());
	 * mud.setSiteName(urlDetails.getSiteName()); mud.setType(urlDetails.getType());
	 * deleteurlRepo.save(mud);
	 * 
	 * com.hp.BMT.services.dao.UrlDetailsHistory udh = new
	 * com.hp.BMT.services.dao.UrlDetailsHistory();
	 * 
	 * udh.setActualId(urlDetails.getId());
	 * udh.setAttachment(attachment.getBytes());
	 * 
	 * udh.setComment(urlDetails.getComment());
	 * udh.setCountry(urlDetails.getCountry());
	 * 
	 * udh.setManager(urlDetails.getManager());
	 * udh.setMarket(urlDetails.getMarket());
	 * udh.setRequestedOn(urlDetails.getRequestedDate());
	 * udh.setRequesterEmail(urlDetails.getRequesterEmail());
	 * udh.setRequestedBy(urlDetails.getRequesterName());
	 * udh.setActive(urlDetails.getActive());
	 * udh.setAmplify(urlDetails.getAmplify());
	 * udh.setDetailedPartnerId(urlDetails.getDetailedPartnerId());
	 * udh.setMasterPartnerID(urlDetails.getMasterPartnerID());
	 * udh.setOfficialPartnerName(urlDetails.getOfficialPartnerName());
	 * udh.setPrimaryLanguage(urlDetails.getPrimaryLanguage());
	 * udh.setSiteName(urlDetails.getSiteName()); udh.setType(urlDetails.getType());
	 * udh.setApprovernm("");
	 * 
	 * udh.setAttachment(attachment.getBytes());
	 * udh.setCountry(urlDetails.getCountry());
	 * udh.setDetailedPartnerId(urlDetails.getDetailedPartnerId());
	 * udh.setMarket(urlDetails.getMarket());
	 * udh.setMasterPartnerID(urlDetails.getMasterPartnerID());
	 * udh.setOfficialPartnerName(urlDetails.getOfficialPartnerName());
	 * udh.setPrimaryLanguage(urlDetails.getPrimaryLanguage());
	 * udh.setType(urlDetails.getPartnerType());
	 * udh.setRequestedBy(urlDetails.getRequesterName());
	 * udh.setRequestedOn(urlDetails.getRequestedDate());
	 * udh.setSiteName(urlDetails.getSiteName());
	 * udh.setActive(urlDetails.getActive());
	 * udh.setAmplify(urlDetails.getAmplify());
	 * udh.setStatus("Pending");udh.setStatus("Pending");
	 * 
	 * udh.setStatus("Pending"); udh.setRequestType("Delete");
	 * springsRepo.insertWithEntityManager(udh); // urlHistoryRepo.save(udh);
	 * 
	 * }
	 * 
	 * public void AddUrlDetailsService(MultipartFile attachment,
	 * com.hp.BMT.services.entity.AddUrlDetails urlDetails) throws IOException {
	 * com.hp.BMT.services.dao.AddUrlDetails mud = new
	 * com.hp.BMT.services.dao.AddUrlDetails();
	 * mud.setAttachment(attachment.getBytes());
	 * mud.setComment(urlDetails.getComment());
	 * mud.setCountry(urlDetails.getCountry());
	 * mud.setDetailedPartnerId(urlDetails.getDetailedPartnerId());
	 * mud.setManager(urlDetails.getManager());
	 * mud.setMarket(urlDetails.getMarket());
	 * mud.setMasterPartnerID(urlDetails.getMasterPartnerID());
	 * mud.setOfficialPartnerName(urlDetails.getOfficialPartnerName());
	 * mud.setPartnerType(urlDetails.getPartnerType());
	 * mud.setPrimaryLanguage(urlDetails.getPrimaryLanguage());
	 * mud.setRequestedDate(urlDetails.getRequestedDate());
	 * mud.setRequesterEmail(urlDetails.getRequesterEmail());
	 * mud.setRequesterName(urlDetails.getRequesterName());
	 * mud.setSiteName(urlDetails.getSiteName()); addUrlRepo.save(mud);
	 * com.hp.BMT.services.dao.UrlDetailsHistory udh = new
	 * com.hp.BMT.services.dao.UrlDetailsHistory();
	 * udh.setAttachment(attachment.getBytes());
	 * udh.setComment(urlDetails.getComment());
	 * udh.setCountry(urlDetails.getCountry());
	 * udh.setDetailedPartnerId(urlDetails.getDetailedPartnerId());
	 * udh.setManager(urlDetails.getManager());
	 * udh.setMarket(urlDetails.getMarket());
	 * udh.setMasterPartnerID(urlDetails.getMasterPartnerID());
	 * udh.setOfficialPartnerName(urlDetails.getOfficialPartnerName());
	 * udh.setType(urlDetails.getPartnerType());
	 * udh.setPrimaryLanguage(urlDetails.getPrimaryLanguage());
	 * udh.setRequestedOn(urlDetails.getRequestedDate());
	 * udh.setRequesterEmail(urlDetails.getRequesterEmail());
	 * udh.setRequestedBy(urlDetails.getRequesterName());
	 * udh.setSiteName(urlDetails.getSiteName()); udh.setRequestType("Add");
	 * udh.setApprovernm("");
	 * 
	 * 
	 * com.hp.BMT.services.dao.UrlDetailsHistory udh = new
	 * com.hp.BMT.services.dao.UrlDetailsHistory();
	 * udh.setAttachment(attachment.getBytes());
	 * udh.setCountry(urlDetails.getCountry());
	 * udh.setDetailedPartnerId(urlDetails.getDetailedPartnerId());
	 * udh.setMarket(urlDetails.getMarket());
	 * udh.setMasterPartnerID(urlDetails.getMasterPartnerID());
	 * udh.setOfficialPartnerName(urlDetails.getOfficialPartnerName());
	 * udh.setPrimaryLanguage(urlDetails.getPrimaryLanguage());
	 * udh.setType(urlDetails.getPartnerType());
	 * udh.setRequestedBy(urlDetails.getRequesterName());
	 * udh.setRequestedOn(urlDetails.getRequestedDate());
	 * udh.setSiteName(urlDetails.getSiteName());
	 * 
	 * udh.setStatus("Pending"); urlHistoryRepo.save(udh);
	 * springsRepo.insertWithEntityManager(udh); }
	 * 
	 * public List<PersonEntity> readStatusForTablesCheck() { List<PersonEntity>
	 * result = dataRepository.count(); return result; };
	 * 
	 * @SuppressWarnings("unused") private List<UrlDetails>
	 * convertDomainToJavaObject(List<Object> allQpp) { List<UrlDetails>
	 * qualifiedPartnerResList = new ArrayList<>(); for (Object o : allQpp) {
	 * Object[] object = (Object[]) o; UrlDetails qualifiedPartnerRes = new
	 * UrlDetails(); qualifiedPartnerRes.setId((Integer) object[0]);
	 * qualifiedPartnerRes.setOfficialPartnerName((String) object[1]);
	 * qualifiedPartnerRes.setMasterPartnerID((String) object[2]);
	 * qualifiedPartnerRes.setDetailedPartnerId((String) object[3]);
	 * qualifiedPartnerRes.setMarket((String) object[4]);
	 * qualifiedPartnerRes.setSiteName((String) object[5]);
	 * qualifiedPartnerRes.setType((String) object[6]);
	 * qualifiedPartnerRes.setCountry((String) object[7]);
	 * qualifiedPartnerRes.setPrimaryLanguage((String) object[8]);
	 * qualifiedPartnerRes.setAmplify((String) object[9]);
	 * qualifiedPartnerRes.setActive((String) object[10]);
	 * qualifiedPartnerResList.add(qualifiedPartnerRes); } return
	 * qualifiedPartnerResList; }
	 * 
	 * @SuppressWarnings("unused") private List<KeywordDetails>
	 * convertDomainToJavaObjectKeywords(List<Object> allQpp) { List<KeywordDetails>
	 * qualifiedPartnerResList = new ArrayList<>(); for (Object o : allQpp) {
	 * Object[] object = (Object[]) o; KeywordDetails qualifiedPartnerRes = new
	 * KeywordDetails(); qualifiedPartnerRes.setId((Integer) object[0]);
	 * qualifiedPartnerRes.setKeywordId((String) object[1]);
	 * qualifiedPartnerRes.setCountry((String) object[2]); // 3 k ty
	 * qualifiedPartnerRes.setKeywordType((String) object[3]); // 13 k te
	 * qualifiedPartnerRes.setKeywordText((String) object[13]); // 4 language
	 * qualifiedPartnerRes.setLanguage((String) object[4]); // 5 category
	 * qualifiedPartnerRes.setCategory((String) object[5]); // 6 sub cat
	 * qualifiedPartnerRes.setSubCategory((String) object[6]); // 7 active
	 * qualifiedPartnerRes.setKeywordType3((String) object[8]); // 8 type3
	 * qualifiedPartnerRes.setMarket((String) object[12]); // 12 la
	 * qualifiedPartnerRes.setHpOptionsScoring((String) object[10] == null ? "N/A" :
	 * ((String) object[10]).trim().equals("") ? "N/A" : (String) object[10]);
	 * qualifiedPartnerRes.setHpTotalOptions((String) object[11] == null ? "N/A" :
	 * ((String) object[11]).trim().equals("") ? "N/A" : (String) object[11]);
	 * qualifiedPartnerResList.add(qualifiedPartnerRes); } // 7 , 9 10 ,11 return
	 * qualifiedPartnerResList; }
	 * 
	 * public List<UrlDetails> getUrlByMarket(List<String> partnerName) throws
	 * WebappException { return
	 * convertDomainToJavaObject(urlRepo.findAllByMarketField(partnerName)); }
	 * 
	 * public List<UrlDetails> getUrlByPartnerName(List<String> partnerName) throws
	 * WebappException { return
	 * convertDomainToJavaObject(urlRepo.findAllByPartnerNameField(partnerName)); }
	 * 
	 * public List<UrlDetails> getUrlBySiteName(List<String> partnerName) throws
	 * WebappException { return
	 * convertDomainToJavaObject(urlRepo.findAllBySiteNamesField(partnerName)); }
	 * 
	 * public List<UrlDetails> getUrlByType(List<String> type) throws
	 * WebappException { return
	 * convertDomainToJavaObject(urlRepo.findAllByTypeField(type)); }
	 * 
	 * public List<UrlDetails> getUrlByMasterPartnerById(List<String> partnerName)
	 * throws WebappException { return
	 * convertDomainToJavaObject(urlRepo.findAllByMasterParnerField(partnerName)); }
	 * 
	 * public List<UrlDetails> getUrlByCountry(List<String> partnerName) throws
	 * WebappException { return
	 * convertDomainToJavaObject(urlRepo.findAllByCountryField(partnerName)); }
	 * 
	 * public List<UrlDetails> getUrlByAmplify(List<String> partnerName) throws
	 * WebappException { return
	 * convertDomainToJavaObject(urlRepo.findAllByAmplifyField(partnerName)); }
	 * 
	 * public List<UrlDetails> getUrlByActive(String partnerName) throws
	 * WebappException { return
	 * convertDomainToJavaObject(urlRepo.findAllByActiveField(partnerName)); }
	 * 
	 * public List<UrlDetails> getAllUrls() throws WebappException { return
	 * convertDomainToJavaObject(urlRepo.findAllUrls()); }
	 * 
	 * public List<KeywordDetails> getAllkeywords() throws WebappException { return
	 * convertDomainToJavaObjectKeywords(keywordRepo.findAllKeywords()); }
	 * 
	 * public List<KeywordDetails> getKeywordByKeywordId(String partnerName) throws
	 * WebappException { return
	 * convertDomainToJavaObjectKeywords(keywordRepo.findAllByKeywordIdSerial(
	 * partnerName)); }
	 * 
	 * public List<KeywordDetails> getKeywordByMarket(List<String> partnerName)
	 * throws WebappException { return
	 * convertDomainToJavaObjectKeywords(keywordRepo.findAllByMarketField(
	 * partnerName)); }
	 * 
	 * public List<KeywordDetails> getKeywordByCountry(List<String> partnerName)
	 * throws WebappException { return
	 * convertDomainToJavaObjectKeywords(keywordRepo.findAllByCountryField(
	 * partnerName)); }
	 * 
	 * public List<KeywordDetails> getKeywordByKeywordText(List<String> partnerName)
	 * throws WebappException { return
	 * convertDomainToJavaObjectKeywords(keywordRepo.findAllByKeywordTextField(
	 * partnerName)); }
	 * 
	 * public List<KeywordDetails> getKeywordByType(List<String> partnerName) throws
	 * WebappException { return
	 * convertDomainToJavaObjectKeywords(keywordRepo.findAllByKeywordTypeField(
	 * partnerName)); }
	 * 
	 * public List<KeywordDetails> getKeywordByLanguage(List<String> partnerName)
	 * throws WebappException { return
	 * convertDomainToJavaObjectKeywords(keywordRepo.findAllByKeywordLanguageField(
	 * partnerName)); }
	 * 
	 * public List<KeywordDetails> getKeywordByCategory(List<String> partnerName)
	 * throws WebappException { return
	 * convertDomainToJavaObjectKeywords(keywordRepo.findAllByCategoryField(
	 * partnerName)); }
	 * 
	 * public List<KeywordDetails> getKeywordBySubCategory(List<String> partnerName)
	 * throws WebappException { return
	 * convertDomainToJavaObjectKeywords(keywordRepo.findAllBySubCategoryField(
	 * partnerName)); }
	 * 
	 * public List<KeywordDetails> getKeywordByKeywordType3(List<String>
	 * partnerName) throws WebappException { return
	 * convertDomainToJavaObjectKeywords(keywordRepo.findAllByKeywordType3Field(
	 * partnerName)); }
	 * 
	 * @SuppressWarnings("deprecation") public List<KeywordDetails>
	 * findByFields(KeywordDetailsFilter searchByFields) throws WebappException {
	 * boolean checkSearchStatus = false; List<KeywordDetails> allRevenue = new
	 * ArrayList<>(); boolean andflag = false; if
	 * (!StringUtils.isEmpty(searchByFields.getKeywordId())) { checkSearchStatus =
	 * true; if (allRevenue.size() != 0) { List<KeywordDetails> udd =
	 * getKeywordByKeywordId(searchByFields.getKeywordId()); allRevenue =
	 * allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList()); andflag = true; } else if
	 * (!andflag) {
	 * allRevenue.addAll(getKeywordByKeywordId(searchByFields.getKeywordId())); } }
	 * if ( !StringUtils.isEmpty(searchByFields. getMarket())
	 * searchByFields.getMarket().size() != 0) { checkSearchStatus = true; if
	 * (allRevenue.size() != 0) { List<KeywordDetails> udd =
	 * getKeywordByMarket(searchByFields.getMarket()); allRevenue =
	 * allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList()); andflag = true; } //
	 * allRevenue.addAll(getKeywordByMarket(searchByFields.getMarket()));
	 * 
	 * else if (!andflag) {
	 * allRevenue.addAll(getKeywordByMarket(searchByFields.getMarket())); } } if
	 * (searchByFields.getCountry().size() != 0) { checkSearchStatus = true; if
	 * (allRevenue.size() != 0) { List<KeywordDetails> udd =
	 * getKeywordByCountry(searchByFields.getCountry()); allRevenue =
	 * allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList()); andflag = true; //
	 * allRevenue.addAll(getKeywordByCountry(searchByFields.getCountry())); } else
	 * if (!andflag) {
	 * allRevenue.addAll(getKeywordByCountry(searchByFields.getCountry())); } } if (
	 * searchByFields.getKeywordType().length() != 0
	 * searchByFields.getKeywordType().size() != 0) { checkSearchStatus = true; if
	 * (allRevenue.size() != 0) { List<KeywordDetails> udd =
	 * getKeywordByType(searchByFields.getKeywordType()); allRevenue =
	 * allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList()); andflag = true; //
	 * allRevenue.addAll(getKeywordByType(searchByFields.getKeywordType())); } else
	 * if (!andflag) {
	 * allRevenue.addAll(getKeywordByType(searchByFields.getKeywordType())); } } if
	 * ( !StringUtils.isEmpty(searchByFields.getKeywordText())
	 * searchByFields.getKeywordText().size() != 0) { checkSearchStatus = true; if
	 * (allRevenue.size() != 0) { List<KeywordDetails> udd =
	 * getKeywordByKeywordText(searchByFields.getKeywordText()); allRevenue =
	 * allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList()); andflag = true; //
	 * allRevenue.addAll(getKeywordByKeywordText(searchByFields.getKeywordText()));
	 * } else if (!andflag) {
	 * allRevenue.addAll(getKeywordByKeywordText(searchByFields.getKeywordText()));
	 * } } if ( !StringUtils.isEmpty(searchByFields.getCountry())
	 * searchByFields.getLanguage().size() != 0) { checkSearchStatus = true; if
	 * (allRevenue.size() != 0) { List<KeywordDetails> udd =
	 * getKeywordByLanguage(searchByFields.getLanguage()); allRevenue =
	 * allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList()); andflag = true; } //
	 * allRevenue.addAll(getKeywordByLanguage(searchByFields.getLanguage())); else
	 * if (!andflag) {
	 * allRevenue.addAll(getKeywordByLanguage(searchByFields.getLanguage())); } } if
	 * ( !StringUtils.isEmpty(searchByFields.getCategory())
	 * searchByFields.getCategory().size() != 0) { checkSearchStatus = true; if
	 * (allRevenue.size() != 0) { List<KeywordDetails> udd =
	 * getKeywordByCategory(searchByFields.getCategory()); allRevenue =
	 * allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList()); andflag = true; //
	 * allRevenue.addAll(getKeywordByCategory(searchByFields.getCategory())); } else
	 * if (!andflag) {
	 * allRevenue.addAll(getKeywordByCategory(searchByFields.getCategory())); } } if
	 * ( !StringUtils.isEmpty(searchByFields.getSubCategory())
	 * searchByFields.getSubCategory().size() != 0) { checkSearchStatus = true; if
	 * (allRevenue.size() != 0) { List<KeywordDetails> udd =
	 * getKeywordBySubCategory(searchByFields.getSubCategory()); allRevenue =
	 * allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList()); andflag = true; } //
	 * allRevenue.addAll(getKeywordBySubCategory(searchByFields.getSubCategory()));
	 * 
	 * else if (!andflag) {
	 * allRevenue.addAll(getKeywordBySubCategory(searchByFields.getSubCategory()));
	 * } } if ( !StringUtils.isEmpty(searchByFields.getKeywordType3())
	 * searchByFields.getKeywordType3().size() != 0) { checkSearchStatus = true; if
	 * (allRevenue.size() != 0) { List<KeywordDetails> udd =
	 * getKeywordByKeywordType3(searchByFields.getKeywordType3()); allRevenue =
	 * allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList()); andflag = true; //
	 * allRevenue.addAll(getKeywordByKeywordType3(searchByFields.getKeywordType3()))
	 * ; } else if (!andflag) {
	 * allRevenue.addAll(getKeywordByKeywordType3(searchByFields.getKeywordType3()))
	 * ; } } if (!checkSearchStatus) { return getAllkeywords(); } else { //
	 * Set<KeywordDetails>allRevenueSet = //
	 * allRevenue.stream().collect(Collectors.toSet()); ArrayList<KeywordDetails>
	 * allRevenueList = allRevenue.stream().collect(Collectors.collectingAndThen(
	 * toCollection(() -> new
	 * TreeSet<>(Comparator.comparingInt(KeywordDetails::getId))), ArrayList::new));
	 * return allRevenueList; }
	 * 
	 * }
	 * 
	 * @SuppressWarnings("deprecation") public List<UrlDetails>
	 * findByFields(UrlDetailsFilter searchByFields) throws WebappException {
	 * boolean checkSearchStatus = false; List<UrlDetails> allRevenue = new
	 * ArrayList<>(); boolean andflag = false; if (
	 * !StringUtils.isEmpty(searchByFields.getMarket())
	 * searchByFields.getMarket().size() != 0) { checkSearchStatus = true; if
	 * (allRevenue.size() != 0) {
	 * 
	 * List<UrlDetails> udd = getUrlByMarket(searchByFields.getMarket()); allRevenue
	 * = allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList());
	 * 
	 * // allRevenue.retainAll(getUrlByMarket(searchByFields.getMarket())); andflag
	 * = true; } else if (!andflag) {
	 * allRevenue.addAll(getUrlByMarket(searchByFields.getMarket())); } } if (
	 * !StringUtils.isEmpty(searchByFields.getOfficialPartnerName())
	 * searchByFields.getOfficialPartnerName().size() != 0) { checkSearchStatus =
	 * true; if (allRevenue.size() != 0) {
	 * 
	 * List<UrlDetails> udd =
	 * getUrlByPartnerName(searchByFields.getOfficialPartnerName()); allRevenue =
	 * allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList());
	 * 
	 * // allRevenue.retainAll(getUrlByPartnerName(searchByFields.
	 * getOfficialPartnerName())); andflag = true; } else if (!andflag)
	 * allRevenue.addAll(getUrlByPartnerName(searchByFields.getOfficialPartnerName()
	 * )); } if ( !StringUtils.isEmpty(searchByFields.getSiteName())
	 * searchByFields.getSiteName().size() != 0) { checkSearchStatus = true; if
	 * (allRevenue.size() != 0) {
	 * 
	 * List<UrlDetails> udd = getUrlBySiteName(searchByFields.getSiteName());
	 * allRevenue = allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList());
	 * 
	 * // allRevenue.retainAll(getUrlBySiteName(searchByFields.getSiteName()));
	 * andflag = true; } else if (!andflag)
	 * allRevenue.addAll(getUrlBySiteName(searchByFields.getSiteName())); } if (
	 * !StringUtils.isEmpty(searchByFields.getType())
	 * searchByFields.getType().size() != 0) { checkSearchStatus = true; if
	 * (allRevenue.size() != 0) {
	 * 
	 * List<UrlDetails> udd = getUrlByType(searchByFields.getType()); allRevenue =
	 * allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList());
	 * 
	 * // allRevenue.retainAll(getUrlByType(searchByFields.getType())); andflag =
	 * true; } else if (!andflag)
	 * allRevenue.addAll(getUrlByType(searchByFields.getType())); } if (
	 * !StringUtils.isEmpty(searchByFields.getMasterPartnerID())
	 * searchByFields.getMasterPartnerID().size() != 0) { checkSearchStatus = true;
	 * if (allRevenue.size() != 0) {
	 * 
	 * List<UrlDetails> udd =
	 * getUrlByMasterPartnerById(searchByFields.getMasterPartnerID()); allRevenue =
	 * allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList());
	 * 
	 * // allRevenue.retainAll(getUrlByMasterPartnerById(searchByFields.
	 * getMasterPartnerID())); andflag = true; } else if (!andflag)
	 * allRevenue.addAll(getUrlByMasterPartnerById(searchByFields.getMasterPartnerID
	 * ())); } if (searchByFields.getCountry().size() != 0) { checkSearchStatus =
	 * true; if (allRevenue.size() != 0) { //
	 * allRevenue.retainAll(getUrlByCountry(searchByFields.getCountry())); //
	 * allRevenue = //
	 * allRevenue.stream().filter(getUrlByCountry(searchByFields.getCountry()) //
	 * ::contains).collect(Collectors.toList()); List<UrlDetails> udd =
	 * getUrlByCountry(searchByFields.getCountry()); allRevenue =
	 * allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList());
	 * 
	 * andflag = true;
	 * 
	 * }
	 * 
	 * Stream.concat(allRevenue.stream(),
	 * getUrlByCountry(searchByFields.getCountry()).stream())
	 * .collect(Collectors.toCollection(() -> new TreeSet<>(
	 * Comparator.comparing(UrlDetails::getId) )));
	 * 
	 * else if (!andflag)
	 * allRevenue.addAll(getUrlByCountry(searchByFields.getCountry())); }
	 * 
	 * if (searchByFields.getAmplify().size() != 0) { checkSearchStatus = true; if
	 * (allRevenue.size() != 0) {
	 * 
	 * List<UrlDetails> udd = getUrlByAmplify(searchByFields.getAmplify());
	 * allRevenue = allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList());
	 * 
	 * // allRevenue.retainAll(getUrlByAmplify(searchByFields.getAmplify()));
	 * andflag = true; } else if (!andflag)
	 * allRevenue.addAll(getUrlByAmplify(searchByFields.getAmplify())); } if
	 * (!StringUtils.isEmpty(searchByFields.getActive())) { checkSearchStatus =
	 * true; if (allRevenue.size() != 0) {
	 * 
	 * List<UrlDetails> udd = getUrlByActive(searchByFields.getActive()); allRevenue
	 * = allRevenue.stream().filter(os -> {
	 * 
	 * return udd.stream() // filter .anyMatch(ns -> // compare both os.getId() ==
	 * ns.getId()); }).collect(Collectors.toList());
	 * 
	 * // allRevenue.retainAll(getUrlByActive(searchByFields.getActive())); andflag
	 * = true; } else if (!andflag)
	 * allRevenue.addAll(getUrlByActive(searchByFields.getActive())); } if
	 * (!checkSearchStatus) { return getAllUrls(); } else { ArrayList<UrlDetails>
	 * allRevenueList = allRevenue.stream().collect(Collectors.collectingAndThen(
	 * toCollection(() -> new
	 * TreeSet<>(Comparator.comparingInt(UrlDetails::getId))), ArrayList::new)); //
	 * List<UrlDetails> u =new ArrayList<UrlDetails>(); // u.addAll(allRevenueSet);
	 * return allRevenueList; } }
	 * 
	 * @Override public String uploadEvidence1(MultipartFile evidence1, String
	 * accountName) throws WebappException { // TODO Auto-generated method stub
	 * return null; }
	 * 
	 * public List<UrlDetailsHistory> getUrlHistoryForUser(String user) { return
	 * urlHistoryRepo.findByUser(user); }
	 * 
	 * public List<KeywordDetailsHistory> getKeywordHistoryForUser(String
	 * requesterName) { return keywordHistoryRepo.findByUser(requesterName); }
	 * 
	 * @Override public List<KeywordDetails> findByFields(KeywordDetailsArrayFilter
	 * keywordDetailsFilter) throws WebappException { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public List<com.hp.BMT.services.dao.MCLDetails> getAllMCLDetails()
	 * { return mclRepo.findAllMCLs(); }
	 * 
	 * @Override public List<com.hp.BMT.services.dao.MCLDetails>
	 * getFilteredMCLDetails(MCLDetails mcldetails) { if
	 * (mcldetails.getMarket().trim() != "") {
	 * 
	 * if (mcldetails.getCountry().trim() != "") { if
	 * (mcldetails.getPrimaryLanguage().trim() != "") { if
	 * (mcldetails.getSecondaryLanguage().trim() != "") {
	 * mclRepo.findFilteredMCLs(mcldetails.getMarket(), mcldetails.getCountry(),
	 * mcldetails.getPrimaryLanguage(), mcldetails.getSecondaryLanguage()); } else {
	 * return mclRepo.findFilteredMarketAndCountryAndPrimaryLanguageMCLs(mcldetails.
	 * getMarket(), mcldetails.getCountry(), mcldetails.getPrimaryLanguage()); } }
	 * else { return
	 * mclRepo.findFilteredMarketAndCountryMCLs(mcldetails.getMarket(),
	 * mcldetails.getCountry()); } }
	 * 
	 * else { return mclRepo.findFilteredMarketMCLs(mcldetails.getMarket()); } }
	 * 
	 * return mclRepo.findFilteredMCLs(mcldetails.getMarket(),
	 * mcldetails.getCountry(), mcldetails.getPrimaryLanguage(),
	 * mcldetails.getSecondaryLanguage()); }
	 * 
	 * @Override public void addMCLDetails(MCLDetails mcldetails) { // TODO
	 * Auto-generated method stub com.hp.BMT.services.dao.MCLDetails mclDao = new
	 * com.hp.BMT.services.dao.MCLDetails();
	 * mclDao.setMarket(mcldetails.getMarket());
	 * mclDao.setCountry(mcldetails.getCountry());
	 * mclDao.setPrimaryLanguage(mcldetails.getPrimaryLanguage());
	 * mclDao.setSecondaryLanguage(mcldetails.getSecondaryLanguage());
	 * mclRepo.save(mclDao); }
	 * 
	 * @Override public void deleteMCLDetails(MCLDetailsId mcldetailsid) { // TODO
	 * Auto-generated method stub com.hp.BMT.services.dao.MCLDetails mclDao = new
	 * com.hp.BMT.services.dao.MCLDetails();
	 * mclDao.setMarket(mcldetailsid.getMarket());
	 * mclDao.setCountry(mcldetailsid.getCountry());
	 * mclDao.setPrimaryLanguage(mcldetailsid.getPrimaryLanguage());
	 * mclDao.setSecondaryLanguage(mcldetailsid.getSecondaryLanguage());
	 * mclDao.setId(mcldetailsid.getId()); mclRepo.delete(mclDao); }
	 * 
	 * @Override public void editMCLDetails(MCLDetailsId mcldetailsid) { // TODO
	 * Auto-generated method stub com.hp.BMT.services.dao.MCLDetails mclDao = new
	 * com.hp.BMT.services.dao.MCLDetails();
	 * mclDao.setMarket(mcldetailsid.getMarket());
	 * mclDao.setCountry(mcldetailsid.getCountry());
	 * mclDao.setPrimaryLanguage(mcldetailsid.getPrimaryLanguage());
	 * mclDao.setSecondaryLanguage(mcldetailsid.getSecondaryLanguage());
	 * mclDao.setId(mcldetailsid.getId()); mclRepo.save(mclDao); }
	 * 
	 * @Override public List<String> getListOfMarkets() { // TODO Auto-generated
	 * method stub return mclRepo.findallMarkets(); }
	 * 
	 * @Override public List<String> getListOfCountries(String market) { // TODO
	 * Auto-generated method stub return mclRepo.findallCountries(market); }
	 * 
	 * @Override public List<String> getListOfPrimaryLanguages(String market, String
	 * country) { // TODO Auto-generated method stub return
	 * mclRepo.findallPrimaryLanguages(market, country); }
	 * 
	 * @Override public List<String> getListOfSecondaryLanguages(String market,
	 * String country, String primaryLanguage) { // TODO Auto-generated method stub
	 * return mclRepo.findallSecondaryLanguages(market, country, primaryLanguage); }
	 * 
	 * @Override public List<com.hp.BMT.services.dao.CSDetails> getAllCSDetails() {
	 * return csRepo.findAllCSs(); }
	 * 
	 * @Override public List<com.hp.BMT.services.dao.CSDetails>
	 * getFilteredCSDetails( com.hp.BMT.services.entity.CSDetails csDetails) { if
	 * (csDetails.getCategory().trim() != "") { if
	 * (csDetails.getSubcategory().trim() != "") { return
	 * csRepo.findFilteredCSs(csDetails.getCategory(), csDetails.getSubcategory());
	 * } else { return csRepo.findFilteredCategoryCS(csDetails.getCategory()); } }
	 * return csRepo.findFilteredCSs(csDetails.getCategory(),
	 * csDetails.getSubcategory()); }
	 * 
	 * @Override public void addCSDetails(com.hp.BMT.services.entity.CSDetails
	 * csDetails) { com.hp.BMT.services.dao.CSDetails csDao = new
	 * com.hp.BMT.services.dao.CSDetails();
	 * csDao.setCategory(csDetails.getCategory());
	 * csDao.setSubcategory(csDetails.getSubcategory()); csRepo.save(csDao);
	 * 
	 * }
	 * 
	 * @Override public void deleteCSDetails(CSDetailsId csDetails) {
	 * com.hp.BMT.services.dao.CSDetails csDao = new
	 * com.hp.BMT.services.dao.CSDetails();
	 * csDao.setCategory(csDetails.getCategory());
	 * csDao.setSubcategory(csDetails.getSubcategory());
	 * csDao.setId(csDetails.getId()); csRepo.delete(csDao); }
	 * 
	 * @Override public void modifyCSDetails(CSDetailsId csDetails) {
	 * com.hp.BMT.services.dao.CSDetails csDao = new
	 * com.hp.BMT.services.dao.CSDetails();
	 * csDao.setCategory(csDetails.getCategory());
	 * csDao.setSubcategory(csDetails.getSubcategory());
	 * csDao.setId(csDetails.getId()); csRepo.save(csDao); }
	 * 
	 * @Override public List<String> getListOfCategories() { // TODO Auto-generated
	 * method stub return csRepo.findallCategories(); }
	 * 
	 * @Override public List<String> getListOfSubcategories(String category) { //
	 * TODO Auto-generated method stub return csRepo.findallSubcategories(category);
	 * }
	 * 
	 * @SuppressWarnings("unchecked") public UrlFiltterDetailsResponse
	 * getUrlFilterDetailsNew(UrlDetailsFilter udf) { StringBuffer statement = new
	 * StringBuffer("where");
	 * 
	 * UrlFiltterDetailsResponse ufdr = new UrlFiltterDetailsResponse();
	 * 
	 * List<String> nonEmptyFields = new ArrayList<String>();
	 * 
	 * List<String> emptyFields = new ArrayList<String>();
	 * 
	 * if (udf.getMarket().size() != 0) { nonEmptyFields.add("Market");
	 * ufdr.setMarkets(udf.getMarket()); // statement.append(" ");
	 * statement.append("("); for (String market : udf.getMarket()) {
	 * statement.append("`Market`="); statement.append("'" + market +
	 * "'").append(" OR"); } statement.delete(statement.length() - 2,
	 * statement.length()); statement.append(")"); statement.append(" AND"); } else
	 * { emptyFields.add("Market"); }
	 * 
	 * if (udf.getCountry().size() != 0) { nonEmptyFields.add("Country");
	 * ufdr.setCOUNTRYs(udf.getCountry()); // statement.append(" "); // add space
	 * statement.append("("); for (String country : udf.getCountry()) {
	 * statement.append("`COUNTRY`="); statement.append("'" + country +
	 * "'").append(" OR"); // add space } statement.delete(statement.length() - 2,
	 * statement.length()); statement.append(")"); statement.append(" AND"); } else
	 * { emptyFields.add("Country"); }
	 * 
	 * if (udf.getMasterPartnerID().size() != 0) {
	 * nonEmptyFields.add("MasterPartnerID");
	 * ufdr.setMasterPartnerIDs(udf.getMasterPartnerID()); // statement.append(" ");
	 * // add space statement.append("("); for (String MasterPartnerID :
	 * udf.getMasterPartnerID()) { statement.append("`﻿Master_Partner_ID`=");
	 * statement.append("'" + MasterPartnerID + "'").append(" OR"); // add space }
	 * statement.delete(statement.length() - 2, statement.length());
	 * statement.append(")"); statement.append(" AND"); } else {
	 * emptyFields.add("MasterPartnerID"); }
	 * 
	 * if (udf.getSiteName().size() != 0) { nonEmptyFields.add("SiteName");
	 * ufdr.setSiteNames(udf.getSiteName()); // statement.append(" ");
	 * statement.append("("); for (String SiteName : udf.getSiteName()) {
	 * statement.append("`Site_Nms`").append(" ='").append(SiteName).append("'").
	 * append(" OR"); // add space } statement.delete(statement.length() - 2,
	 * statement.length()); statement.append(")"); statement.append(" AND"); } else
	 * { emptyFields.add("SiteName"); }
	 * 
	 * if (udf.getType().size() != 0) { nonEmptyFields.add("Type");
	 * ufdr.setTypes(udf.getType()); // statement.append(" "); // add space
	 * statement.append("("); for (String SiteName : udf.getType()) {
	 * statement.append("`TYPE`=");
	 * statement.append("'").append(SiteName).append("'").append(" OR"); // add
	 * space } statement.delete(statement.length() - 2, statement.length());
	 * statement.append(")"); statement.append(" AND"); } else {
	 * emptyFields.add("Type"); }
	 * 
	 * if (udf.getOfficialPartnerName().size() != 0) {
	 * nonEmptyFields.add("OfficialPartnerName");
	 * ufdr.setPartnerNames(udf.getOfficialPartnerName()); // statement.append(" ");
	 * statement.append("("); for (String OfficialPartnerName :
	 * udf.getOfficialPartnerName()) { statement.append("`partner_name`="); // add
	 * space statement.append("'" + OfficialPartnerName + "'").append(" OR"); // add
	 * space } statement.delete(statement.length() - 2, statement.length());
	 * statement.append(")"); statement.append(" AND"); } else {
	 * emptyFields.add("OfficialPartnerName"); }
	 * 
	 * if (udf.getAmplify().size() != 0) {
	 * 
	 * nonEmptyFields.add("Amplify"); ufdr.setAmplifys(udf.getAmplify()); //
	 * statement.append(" "); statement.append("("); for (String Amplify :
	 * udf.getAmplify()) { statement.append("`﻿Amplify`="); // add space
	 * statement.append("'" + Amplify + "'").append(" OR"); // add space }
	 * statement.delete(statement.length() - 2, statement.length());
	 * statement.append(""); statement.append(" AND"); } else {
	 * emptyFields.add("Amplify"); }
	 * 
	 * if (udf.getActive() != "") { nonEmptyFields.add("Active");
	 * ufdr.setPrimaryActives(new
	 * ArrayList<String>(Arrays.asList(udf.getActive()))); // statement.append(" ");
	 * statement.append("`Active`="); // add space
	 * 
	 * statement.append("'" + udf.getActive() + "'").append(" OR"); // add space
	 * 
	 * statement.delete(statement.length() - 2, statement.length());
	 * statement.append(" AND"); } else { emptyFields.add("Active"); }
	 * 
	 * statement.delete(statement.length() - 3, statement.length());
	 * 
	 * for (String emptyField : emptyFields) { if (emptyField.equals("Market")) {
	 * StringBuffer newstatement = new StringBuffer(statement); String markStatement
	 * = newstatement.insert(0,
	 * "Select DISTINCT(Market) from url_details ").toString(); List<String> markets
	 * = springsRepo.queryWithEntityManager(markStatement); if (markets != null &&
	 * !markets.contains(null)) markets.sort(Comparator.naturalOrder());
	 * ufdr.setMarkets(markets); } if (emptyField.equals("Country")) { StringBuffer
	 * newstatement = new StringBuffer(statement); String COUNTRYStatement =
	 * "Select DISTINCT(COUNTRY) from url_details " + newstatement.toString();
	 * List<String> countries =
	 * springsRepo.queryWithEntityManager(COUNTRYStatement); if (countries != null
	 * && !countries.contains(null)) countries.sort(Comparator.naturalOrder());
	 * ufdr.setCOUNTRYs(countries); } else if (emptyField.equals("MasterPartnerID"))
	 * { StringBuffer newstatement = new StringBuffer(statement); String
	 * MasterPartnerIDStatement =
	 * "Select DISTINCT(﻿Master_Partner_ID) from url_details " +
	 * newstatement.toString(); List<String> MasterPartnerIDs =
	 * springsRepo.queryWithEntityManager(MasterPartnerIDStatement); if
	 * (MasterPartnerIDs != null && !MasterPartnerIDs.contains(null))
	 * MasterPartnerIDs.sort(Comparator.naturalOrder());
	 * ufdr.setMasterPartnerIDs(MasterPartnerIDs); } else if
	 * (emptyField.equals("OfficialPartnerName")) { // SiteName Type Amplify
	 * StringBuffer newstatement = new StringBuffer(statement); String
	 * OfficialPartnerNameStatement =
	 * "Select DISTINCT(partner_name) from url_details " + newstatement.toString();
	 * List<String> OfficialPartnerNames =
	 * springsRepo.queryWithEntityManager(OfficialPartnerNameStatement); if
	 * (OfficialPartnerNames != null && !OfficialPartnerNames.contains(null))
	 * OfficialPartnerNames.sort(Comparator.naturalOrder());
	 * ufdr.setPartnerNames(OfficialPartnerNames); } else if
	 * (emptyField.equals("SiteName")) { StringBuffer newstatement = new
	 * StringBuffer(statement); String SiteNameStatement = newstatement.insert(0,
	 * "Select DISTINCT(Site_Nms) from url_details ") .toString(); List<String>
	 * SiteNames = springsRepo.queryWithEntityManager(SiteNameStatement); if
	 * (SiteNames != null && !SiteNames.contains(null))
	 * SiteNames.sort(Comparator.naturalOrder()); ufdr.setSiteNames(SiteNames); }
	 * else if (emptyField.equals("Type")) { StringBuffer newstatement = new
	 * StringBuffer(statement); String TypeStatement =
	 * "Select DISTINCT(TYPE) from url_details " + newstatement.toString();
	 * List<String> Types = springsRepo.queryWithEntityManager(TypeStatement); if
	 * (Types != null && !Types.contains(null))
	 * Types.sort(Comparator.naturalOrder()); ufdr.setTypes(Types); } else if
	 * (emptyField.equals("Amplify")) { StringBuffer newstatement = new
	 * StringBuffer(statement); String AmplifyStatement =
	 * "Select DISTINCT(Amplify) from url_details " + newstatement.toString();
	 * List<String> Amplifys = springsRepo.queryWithEntityManager(AmplifyStatement);
	 * if (Amplifys != null && !Amplifys.contains(null))
	 * Amplifys.sort(Comparator.naturalOrder()); ufdr.setAmplifys(Amplifys); } else
	 * if (emptyField.equals("Active")) { StringBuffer newstatement = new
	 * StringBuffer(statement); String countryStatement =
	 * "Select DISTINCT(Active) from url_details " + newstatement.toString();
	 * List<String> actives = springsRepo.queryWithEntityManager(countryStatement);
	 * if (actives != null && !actives.contains(null))
	 * actives.sort(Comparator.naturalOrder()); ufdr.setPrimaryActives(actives); } }
	 * 
	 * return ufdr; }
	 * 
	 * public KeywordDetailsResponse getKeywordFilterDetailsNew(KeywordDetailsFilter
	 * kdf) {
	 * 
	 * KeywordDetailsResponse kdfr = new KeywordDetailsResponse();
	 * 
	 * StringBuffer statement = new StringBuffer("where");
	 * 
	 * KeywordDetailsResponse dr = new KeywordDetailsResponse();
	 * 
	 * List<String> nonEmptyFields = new ArrayList<String>();
	 * 
	 * List<String> emptyFields = new ArrayList<String>();
	 * 
	 * if (kdf.getMarket().size() != 0) { nonEmptyFields.add("Market");
	 * kdfr.setPartnerMarkets(kdf.getMarket()); statement.append("("); for (String
	 * market : kdf.getMarket()) { statement.append("`Market`=");
	 * statement.append("'" + market + "'").append(" OR"); }
	 * statement.delete(statement.length() - 2, statement.length());
	 * statement.append(")"); statement.append(" AND"); } else {
	 * emptyFields.add("Market"); }
	 * 
	 * if (kdf.getCountry().size() != 0) { nonEmptyFields.add("COUNTRY");
	 * kdfr.setCountries(kdf.getCountry()); statement.append("("); for (String
	 * country : kdf.getCountry()) { statement.append("`COUNTRY`=");
	 * statement.append("'" + country + "'").append(" OR"); }
	 * statement.delete(statement.length() - 2, statement.length());
	 * statement.append(")"); statement.append(" AND"); } else {
	 * emptyFields.add("COUNTRY"); }
	 * 
	 * if (kdf.getKeywordType().size() != 0) { nonEmptyFields.add("KeywordType");
	 * kdfr.setKeywordTypes(kdf.getKeywordType()); statement.append("("); for
	 * (String keywordType : kdf.getKeywordType()) {
	 * statement.append("`keyword_type`="); statement.append("'" + keywordType +
	 * "'").append(" OR"); } statement.delete(statement.length() - 2,
	 * statement.length()); statement.append(")"); statement.append(" AND"); } else
	 * { emptyFields.add("KeywordType"); }
	 * 
	 * if (kdf.getKeywordText().size() != 0) { nonEmptyFields.add("KeywordText");
	 * kdfr.setMasterKeywordTexts(kdf.getKeywordText()); statement.append("("); for
	 * (String keywordText : kdf.getKeywordText()) {
	 * statement.append("`keyword_text`="); statement.append("'" + keywordText +
	 * "'").append(" OR"); } statement.delete(statement.length() - 2,
	 * statement.length()); statement.append(")"); statement.append(" AND"); } else
	 * { emptyFields.add("KeywordText"); }
	 * 
	 * if (kdf.getLanguage().size() != 0) { nonEmptyFields.add("KeywordLanguage");
	 * kdfr.setKWLanguages(kdf.getLanguage()); statement.append("("); for (String
	 * language : kdf.getLanguage()) { statement.append("`KW_language`=");
	 * statement.append("'" + language + "'").append(" OR"); }
	 * statement.delete(statement.length() - 2, statement.length());
	 * statement.append(")"); statement.append(" AND"); } else {
	 * emptyFields.add("KeywordLanguage"); }
	 * 
	 * if (kdf.getCategory().size() != 0) { nonEmptyFields.add("Category");
	 * kdfr.setCategories(kdf.getCategory()); statement.append("("); for (String
	 * category : kdf.getCategory()) { statement.append("`category`=");
	 * statement.append("'" + category + "'").append(" OR"); }
	 * statement.delete(statement.length() - 2, statement.length());
	 * statement.append(")"); statement.append(" AND"); } else {
	 * emptyFields.add("Category"); }
	 * 
	 * if (kdf.getCategory().size() != 0) { nonEmptyFields.add("Category");
	 * kdfr.setCategories(kdf.getCategory()); // statement.append(" ");
	 * statement.append("`category`="); for (String category : kdf.getCategory()) {
	 * statement.append("'" + category + "'").append(" OR"); }
	 * statement.delete(statement.length() - 2, statement.length());
	 * statement.append(" AND"); } else { emptyFields.add("Category"); }
	 * 
	 * 
	 * if (kdf.getSubCategory().size() != 0) { nonEmptyFields.add("Subcategory");
	 * kdfr.setSubCategories(kdf.getSubCategory()); statement.append("("); for
	 * (String subcategory : kdf.getSubCategory()) {
	 * statement.append("`sub_category`="); statement.append("'" + subcategory +
	 * "'").append(" OR"); } statement.delete(statement.length() - 2,
	 * statement.length()); statement.append(")"); statement.append(" AND"); } else
	 * { emptyFields.add("Subcategory"); }
	 * 
	 * if (kdf.getKeywordType3().size() != 0) { nonEmptyFields.add("keywordtype3");
	 * kdfr.setKeywordType3(kdf.getKeywordType3()); statement.append("("); for
	 * (String kwt3 : kdf.getKeywordType3()) {
	 * statement.append("`keyword_type_3`="); statement.append("'" + kwt3 +
	 * "'").append(" OR"); } statement.delete(statement.length() - 2,
	 * statement.length()); statement.append(")"); statement.append(" AND"); } else
	 * { emptyFields.add("keywordtype3"); }
	 * 
	 * statement.delete(statement.length() - 3, statement.length());
	 * 
	 * for (String emptyField : emptyFields) { if (emptyField.equals("Market")) {
	 * String markStatement = "Select DISTINCT(Market) from keyword_details " +
	 * statement.toString(); List<String> markets =
	 * springsRepo.queryWithEntityManager(markStatement);
	 * markets.sort(Comparator.naturalOrder()); kdfr.setPartnerMarkets(markets); }
	 * if (emptyField.equals("COUNTRY")) { String COUNTRYStatement =
	 * "Select DISTINCT(COUNTRY) from keyword_details " + statement.toString();
	 * List<String> countries =
	 * springsRepo.queryWithEntityManager(COUNTRYStatement);
	 * countries.sort(Comparator.naturalOrder()); kdfr.setCountries(countries); } if
	 * (emptyField.equals("KeywordType")) { // KeywordType, KeywordText,
	 * KeywordLanguage, Category, Subcategory, // keywordtype3 String
	 * KeywordTypeStatement = "Select DISTINCT(keyword_type) from keyword_details "
	 * + statement.toString(); List<String> KeywordType =
	 * springsRepo.queryWithEntityManager(KeywordTypeStatement);
	 * KeywordType.sort(Comparator.naturalOrder());
	 * kdfr.setKeywordTypes(KeywordType); } if (emptyField.equals("KeywordText")) {
	 * // KeywordType, KeywordText, KeywordLanguage, Category, Subcategory, //
	 * keywordtype3 String KeywordTextStatement =
	 * "Select DISTINCT(keyword_text) from keyword_details " + statement.toString();
	 * List<String> KeywordText =
	 * springsRepo.queryWithEntityManager(KeywordTextStatement);
	 * KeywordText.sort(Comparator.naturalOrder());
	 * kdfr.setMasterKeywordTexts(KeywordText); } if
	 * (emptyField.equals("KeywordLanguage")) { // KeywordType, KeywordText,
	 * KeywordLanguage, Category, // Subcategory, keywordtype3 String
	 * KeywordLanguageStatement =
	 * "Select DISTINCT(KW_language) from keyword_details " + statement.toString();
	 * List<String> KeywordLanguage =
	 * springsRepo.queryWithEntityManager(KeywordLanguageStatement);
	 * KeywordLanguage.sort(Comparator.naturalOrder());
	 * kdfr.setKWLanguages(KeywordLanguage); } if (emptyField.equals("Category")) {
	 * // KeywordType, KeywordText, KeywordLanguage, Category, Subcategory, //
	 * keywordtype3 String CategoryStatement =
	 * "Select DISTINCT(category) from keyword_details " + statement.toString();
	 * List<String> category =
	 * springsRepo.queryWithEntityManager(CategoryStatement);
	 * category.sort(Comparator.naturalOrder()); kdfr.setCategories(category); } if
	 * (emptyField.equals("Subcategory")) { // KeywordType, KeywordText,
	 * KeywordLanguage, Category, Subcategory, // keywordtype3 String
	 * SubcategoryStatement = "Select DISTINCT(sub_category) from keyword_details "
	 * + statement.toString(); List<String> Subcategory =
	 * springsRepo.queryWithEntityManager(SubcategoryStatement);
	 * Subcategory.sort(Comparator.naturalOrder());
	 * kdfr.setSubCategories(Subcategory); } if (emptyField.equals("keywordtype3"))
	 * { // KeywordType, KeywordText, KeywordLanguage, Category, // Subcategory,
	 * keywordtype3 String kwt3Statement =
	 * "Select DISTINCT(keyword_type_3) from keyword_details " +
	 * statement.toString(); List<String> kwt3 =
	 * springsRepo.queryWithEntityManager(kwt3Statement);
	 * kwt3.sort(Comparator.naturalOrder()); kdfr.setKeywordType3(kwt3); }
	 * 
	 * // List<String> kwIdSerials = keywordRepo.findallKWIdSerials(); //
	 * kwIdSerials.sort(Comparator.naturalOrder()); //
	 * ufdr.setKWIdSerials(keywordRepo.findallKWIdSerials());
	 * 
	 * dr.setPartnerMarkets(keywordRepo.findallPartnerMarkets());
	 * ufdr.setCountries(keywordRepo.findallCountries());
	 * ufdr.setKeywordTypes(keywordRepo.findallKeywordTypes());
	 * ufdr.setMasterKeywordTexts(keywordRepo.findall﻿KeywordText()); //
	 * ufdr.setMasterKeywordTexts(new ArrayList<String>(Arrays.asList("")));
	 * ufdr.setKWLanguages(keywordRepo.findallKWLanguages());
	 * ufdr.setCategories(keywordRepo.findallCategories());
	 * ufdr.setSubCategories(keywordRepo.findallSubCategories());
	 * ufdr.setKeywordType3(keywordRepo.findallKeywordType3());
	 * 
	 * 
	 * return ufdr; } return kdfr; }
	 * 
	 * @Override public List<UrlHistoryResponse>
	 * getListOfHistoryItemsForUrl(UrlHistoryRequest uhr) throws IOException {
	 * 
	 * List<UrlDetailsHistory> udh = new ArrayList<UrlDetailsHistory>();
	 * 
	 * if (!uhr.getRequestType().equalsIgnoreCase("All")) { if
	 * (!uhr.getRequestStatus().equalsIgnoreCase("All")) { udh =
	 * urlHistoryRepo.findByRequestAndStatusTypes(uhr.getRequestType(),
	 * uhr.getRequestStatus(), uhr.getUsername()); } else { udh =
	 * urlHistoryRepo.findByRequestAndStatusTypesForAllStatus(uhr.getRequestType(),
	 * uhr.getUsername()); } } else if
	 * (!uhr.getRequestStatus().equalsIgnoreCase("All")) { udh =
	 * urlHistoryRepo.findByRequestAndStatusTypesForAllTypes(uhr.getRequestStatus(),
	 * uhr.getUsername()); } else { udh =
	 * urlHistoryRepo.findByUser(uhr.getUsername()); }
	 * 
	 * List<UrlHistoryResponse> uhrList = new ArrayList<UrlHistoryResponse>();
	 * 
	 * for (UrlDetailsHistory udhitem : udh) { UrlHistoryResponse uhre = new
	 * UrlHistoryResponse(); byte[] content = udhitem.getAttachment(); if (content
	 * == null) content = new byte[0];
	 * 
	 * InputStream is = new BufferedInputStream(new ByteArrayInputStream(content));
	 * // BufferedInputStream bis = new BufferedInputStream(new //
	 * ByteArrayInputStream(content));
	 * 
	 * String mimeType = URLConnection.guessContentTypeFromStream(is);
	 * 
	 * if (mimeType == null) mimeType = "text/plain";
	 * 
	 * uhre.setActive( udhitem.getActive() == null ? "N/A" :
	 * udhitem.getActive().equals("") ? "N/A" : udhitem.getActive());
	 * uhre.setActualId(udhitem.getActualId() == null ? "-1" :
	 * udhitem.getActualId().equals("") ? "-1" : udhitem.getActualId());
	 * uhre.setAmplify(udhitem.getAmplify() == null ? "N/A" :
	 * udhitem.getAmplify().equals("") ? "N/A" : udhitem.getAmplify());
	 * uhre.setAttachment("data:" + mimeType + ";base64," +
	 * Base64.getEncoder().encodeToString(content));
	 * uhre.setComment(udhitem.getComment() == null ? "N/A" :
	 * udhitem.getComment().equals("") ? "N/A" : udhitem.getComment());
	 * uhre.setCountry(udhitem.getCountry() == null ? "N/A" :
	 * udhitem.getCountry().equals("") ? "N/A" : udhitem.getCountry());
	 * uhre.setDetailedPartnerId(udhitem.getDetailedPartnerId() == null ? "N/A" :
	 * udhitem.getDetailedPartnerId().equals("") ? "N/A" :
	 * udhitem.getDetailedPartnerId()); uhre.setManager(udhitem.getManager() == null
	 * ? "N/A" : udhitem.getManager().equals("") ? "N/A" : udhitem.getManager());
	 * uhre.setMarket( udhitem.getMarket() == null ? "N/A" :
	 * udhitem.getMarket().equals("") ? "N/A" : udhitem.getMarket());
	 * uhre.setMasterPartnerID(udhitem.getMasterPartnerID() == null ? "N/A" :
	 * udhitem.getMasterPartnerID().equals("") ? "N/A" :
	 * udhitem.getMasterPartnerID());
	 * uhre.setOfficialPartnerName(udhitem.getOfficialPartnerName() == null ? "N/A"
	 * : udhitem.getOfficialPartnerName().equals("") ? "N/A" :
	 * udhitem.getOfficialPartnerName());
	 * uhre.setPrimaryLanguage(udhitem.getPrimaryLanguage() == null ? "N/A" :
	 * udhitem.getPrimaryLanguage().equals("") ? "N/A" :
	 * udhitem.getPrimaryLanguage()); uhre.setRequestedBy(udhitem.getRequestedBy()
	 * == null ? "N/A" : udhitem.getRequestedBy().equals("") ? "N/A" :
	 * udhitem.getRequestedBy()); uhre.setRequestedOn(udhitem.getRequestedOn() ==
	 * null ? "N/A" : udhitem.getRequestedOn().equals("") ? "N/A" :
	 * udhitem.getRequestedOn()); uhre.setRequesterEmail(udhitem.getRequesterEmail()
	 * == null ? "N/A" : udhitem.getRequesterEmail().equals("") ? "N/A" :
	 * udhitem.getRequesterEmail()); uhre.setRequestType(udhitem.getRequestType() ==
	 * null ? "N/A" : udhitem.getRequestType().equals("") ? "N/A" :
	 * udhitem.getRequestType()); uhre.setSiteName(udhitem.getSiteName() == null ?
	 * "N/A" : udhitem.getSiteName().equals("") ? "N/A" : udhitem.getSiteName());
	 * uhre.setStatus( udhitem.getStatus() == null ? "N/A" :
	 * udhitem.getStatus().equals("") ? "N/A" : udhitem.getStatus());
	 * uhre.setType(udhitem.getType() == null ? "N/A" : udhitem.getType().equals("")
	 * ? "N?A" : udhitem.getType()); uhre.setId(udhitem.getId()); uhrList.add(uhre);
	 * 
	 * } return uhrList; }
	 * 
	 * public void SubmitApprovalForUrl(SubmitApprovalReqForUrl sar) throws
	 * Exception { if (sar.getRequestType().equalsIgnoreCase("Add")) { if
	 * (sar.getApproveType().equalsIgnoreCase("Approved")) {
	 * com.hp.BMT.services.dao.UrlDetails ud = new
	 * com.hp.BMT.services.dao.UrlDetails();
	 * ud.setCOUNTRY(sar.getRequestData().getCountry() == null ? "N/A" :
	 * sar.getRequestData().getCountry().equals("") ? "N?A" :
	 * sar.getRequestData().getCountry());
	 * ud.setDetailedPartnerID(sar.getRequestData().getDetailedPartnerId() == null ?
	 * "N/A" : sar.getRequestData().getDetailedPartnerId().equals("") ? "N?A" :
	 * sar.getRequestData().getDetailedPartnerId());
	 * ud.setMarket(sar.getRequestData().getMarket() == null ? "N/A" :
	 * sar.getRequestData().getMarket().equals("") ? "N?A" :
	 * sar.getRequestData().getMarket());
	 * ud.setMasterPartnerID(sar.getRequestData().getMasterPartnerID() == null ?
	 * "N/A" : sar.getRequestData().getMasterPartnerID().equals("") ? "N?A" :
	 * sar.getRequestData().getMasterPartnerID());
	 * ud.setPartnerName(sar.getRequestData().getOfficialPartnerName() == null ?
	 * "N/A" : sar.getRequestData().getOfficialPartnerName().equals("") ? "N?A" :
	 * sar.getRequestData().getOfficialPartnerName());
	 * ud.setPrimaryLanguage(sar.getRequestData().getPrimaryLanguage() == null ?
	 * "N/A" : sar.getRequestData().getPrimaryLanguage().equals("") ? "N?A" :
	 * sar.getRequestData().getPrimaryLanguage());
	 * ud.setSiteNames(sar.getRequestData().getSiteName() == null ? "N/A" :
	 * sar.getRequestData().getSiteName().equals("") ? "N?A" :
	 * sar.getRequestData().getSiteName());
	 * ud.setTYPE(sar.getRequestData().getType() == null ? "N/A" :
	 * sar.getRequestData().getType().equals("") ? "N?A" :
	 * sar.getRequestData().getType());
	 * springsRepo.insertWithEntityManagerForAddUrl(ud);
	 * urlHistoryRepo.changeStatusByIdAndUniqueId(sar.getRequestData().getId(),
	 * "Approved"); List<com.hp.BMT.services.dao.MCLDetails> mclList =
	 * mclRepo.findifMCLExists( sar.getRequestData().getMarket(),
	 * sar.getRequestData().getCountry(),
	 * sar.getRequestData().getPrimaryLanguage()); if (mclList == null || (mclList
	 * != null && mclList.size() == 0)) { com.hp.BMT.services.dao.MCLDetails mclDao
	 * = new com.hp.BMT.services.dao.MCLDetails();
	 * mclDao.setMarket(sar.getRequestData().getMarket());
	 * mclDao.setCountry(sar.getRequestData().getCountry());
	 * mclDao.setPrimaryLanguage(sar.getRequestData().getPrimaryLanguage());
	 * mclRepo.save(mclDao); } //
	 * urlHistoryRepo.deleteByIdAndUniqueId(sar.getRequestData().getId()); } else if
	 * (sar.getApproveType().equalsIgnoreCase("Rejected")) {
	 * urlHistoryRepo.changeStatusByIdAndUniqueId(sar.getRequestData().getId(),
	 * "Rejected");
	 * 
	 * // urlHistoryRepo.deleteByIdAndUniqueId(sar.getRequestData().getId()); }
	 * 
	 * } else if (sar.getRequestType().equalsIgnoreCase("Delete")) { if
	 * (sar.getApproveType().equalsIgnoreCase("Approved")) {
	 * com.hp.BMT.services.dao.UrlDetails ud = new
	 * com.hp.BMT.services.dao.UrlDetails();
	 * urlRepo.deleteById(Integer.parseInt(sar.getRequestData().getActualId())); //
	 * urlHistoryRepo.deleteByIdAndUniqueId(sar.getRequestData().getId());
	 * urlHistoryRepo.changeStatusByIdAndUniqueId(sar.getRequestData().getId(),
	 * "Approved");
	 * 
	 * } else if (sar.getApproveType().equalsIgnoreCase("Rejected")) {
	 * urlHistoryRepo.changeStatusByIdAndUniqueId(sar.getRequestData().getId(),
	 * "Rejected");
	 * 
	 * // urlHistoryRepo.deleteByIdAndUniqueId(sar.getRequestData().getId()); } }
	 * else if (sar.getRequestType().equalsIgnoreCase("Modify")) { if
	 * (sar.getApproveType().equalsIgnoreCase("Approved")) {
	 * 
	 * Optional<com.hp.BMT.services.dao.UrlDetails> uds = urlRepo
	 * .findById(Integer.parseInt(sar.getRequestData().getActualId())); if
	 * (uds.isPresent()) { com.hp.BMT.services.dao.UrlDetails ud = uds.get(); //
	 * com.hp.BMT.services.dao.UrlDetails ud = new //
	 * com.hp.BMT.services.dao.UrlDetails();
	 * ud.setCOUNTRY(sar.getRequestData().getCountry());
	 * ud.setDetailedPartnerID(sar.getRequestData().getDetailedPartnerId());
	 * ud.setMarket(sar.getRequestData().getMarket());
	 * ud.setMasterPartnerID(sar.getRequestData().getMasterPartnerID());
	 * ud.setPartnerName(sar.getRequestData().getOfficialPartnerName());
	 * ud.setPrimaryLanguage(sar.getRequestData().getPrimaryLanguage());
	 * ud.setSiteNames(sar.getRequestData().getSiteName());
	 * ud.setTYPE(sar.getRequestData().getType()); urlRepo.save(ud);
	 * 
	 * } urlHistoryRepo.changeStatusByIdAndUniqueId(sar.getRequestData().getId(),
	 * "Approved");
	 * 
	 * // urlHistoryRepo.deleteByIdAndUniqueId(sar.getRequestData().getId()); } else
	 * if (sar.getApproveType().equalsIgnoreCase("Rejected")) {
	 * urlHistoryRepo.changeStatusByIdAndUniqueId(sar.getRequestData().getId(),
	 * "Rejected");
	 * 
	 * // urlHistoryRepo.deleteByIdAndUniqueId(sar.getRequestData().getId()); } }
	 * else { throw new Exception(); } }
	 * 
	 * public void SubmitApprovalForKeyword(SubmitApprovalReqForKeyword sar) throws
	 * Exception { if (sar.getRequestType().equalsIgnoreCase("Add")) { if
	 * (sar.getApproveType().equalsIgnoreCase("Approved")) {
	 * com.hp.BMT.services.dao.KeywordDetails ud = new
	 * com.hp.BMT.services.dao.KeywordDetails();
	 * 
	 * ud.setActive(sar.getRequestData().getActive() == null ? "N/A" :
	 * sar.getRequestData().getActive().equals("") ? "N/A" :
	 * sar.getRequestData().getActive());
	 * ud.setBase_Language_Keyword_Text(sar.getRequestData().
	 * getBaseLanguageKeywordText() == null ? "N/A" :
	 * sar.getRequestData().getBaseLanguageKeywordText().equals("") ? "N/A" :
	 * sar.getRequestData().getBaseLanguageKeywordText());
	 * ud.setCategory(sar.getRequestData().getCategory() == null ? "N/A" :
	 * sar.getRequestData().getCategory().equals("") ? "N/A" :
	 * sar.getRequestData().getCategory());
	 * ud.setCountry(sar.getRequestData().getCountry() == null ? "N/A" :
	 * sar.getRequestData().getCountry().equals("") ? "N/A" :
	 * sar.getRequestData().getCountry());
	 * ud.setHp_options_scoring(sar.getRequestData().getHpOptionsScoring() == null ?
	 * "N/A" : sar.getRequestData().getHpOptionsScoring().equals("") ? "N/A" :
	 * sar.getRequestData().getHpOptionsScoring());
	 * ud.setHp_total_options(sar.getRequestData().getHpTotalOptions() == null ?
	 * "N/A" : sar.getRequestData().getHpTotalOptions().equals("") ? "N/A" :
	 * sar.getRequestData().getHpTotalOptions()); //
	 * ud.setId(sar.getRequestData().getActualId());
	 * ud.setKeyword_text(sar.getRequestData().getKeywordText() == null ? "N/A" :
	 * sar.getRequestData().getKeywordText().equals("") ? "N/A" :
	 * sar.getRequestData().getKeywordText());
	 * ud.setKeyword_type_3(sar.getRequestData().getKeywordType3() == null ? "N/A" :
	 * sar.getRequestData().getKeywordType3().equals("") ? "N/A" :
	 * sar.getRequestData().getKeywordType3());
	 * ud.setKW_id_serial(getLastKeywordUsed());
	 * ud.setKW_language(sar.getRequestData().getLanguage() == null ? "N/A" :
	 * sar.getRequestData().getLanguage().equals("") ? "N/A" :
	 * sar.getRequestData().getLanguage());
	 * ud.setSub_category(sar.getRequestData().getSubCategory() == null ? "N/A" :
	 * sar.getRequestData().getSubCategory().equals("") ? "N/A" :
	 * sar.getRequestData().getSubCategory());
	 * ud.setKeyword_type(sar.getRequestData().getkeywordType() == null ? "N/A" :
	 * sar.getRequestData().getkeywordType().equals("") ? "N/A" :
	 * sar.getRequestData().getkeywordType());
	 * ud.setMarket(sar.getRequestData().getMarket() == null ? "N/A" :
	 * sar.getRequestData().getMarket().equals("") ? "N/A" :
	 * sar.getRequestData().getMarket());
	 * springsRepo.insertWithEntityManagerForAddKeyword(ud);
	 * keywordHistoryRepo.changeStatusByIdAndUniqueId(sar.getRequestData().getId(),
	 * "Approved"); kwSerialIdRepo.InserKwidserial(getLastKeywordUsed());
	 * List<com.hp.BMT.services.dao.MCLDetails> mclList = mclRepo.findifMCLExists(
	 * sar.getRequestData().getMarket(), sar.getRequestData().getCountry(),
	 * sar.getRequestData().getLanguage()); if (mclList == null || (mclList != null
	 * && mclList.size() == 0)) { com.hp.BMT.services.dao.MCLDetails mclDao = new
	 * com.hp.BMT.services.dao.MCLDetails();
	 * mclDao.setMarket(sar.getRequestData().getMarket());
	 * mclDao.setCountry(sar.getRequestData().getCountry());
	 * mclDao.setPrimaryLanguage(sar.getRequestData().getLanguage());
	 * mclRepo.save(mclDao); }
	 * 
	 * } else if (sar.getApproveType().equalsIgnoreCase("Rejected")) {
	 * keywordHistoryRepo.changeStatusByIdAndUniqueId(sar.getRequestData().getId(),
	 * "Rejected"); }
	 * 
	 * } else if (sar.getRequestType().equalsIgnoreCase("Delete")) { if
	 * (sar.getApproveType().equalsIgnoreCase("Approved")) {
	 * com.hp.BMT.services.dao.KeywordDetails ud = new
	 * com.hp.BMT.services.dao.KeywordDetails();
	 * keywordRepo.deleteById(Integer.parseInt(sar.getRequestData().getActualId()));
	 * // urlHistoryRepo.deleteByIdAndUniqueId(sar.getRequestData().getId());
	 * keywordHistoryRepo.changeStatusByIdAndUniqueId(sar.getRequestData().getId(),
	 * "Approved");
	 * 
	 * } else if (sar.getApproveType().equalsIgnoreCase("Rejected")) {
	 * keywordHistoryRepo.changeStatusByIdAndUniqueId(sar.getRequestData().getId(),
	 * "Rejected");
	 * 
	 * // urlHistoryRepo.deleteByIdAndUniqueId(sar.getRequestData().getId()); } }
	 * else if (sar.getRequestType().equalsIgnoreCase("Modify")) { if
	 * (sar.getApproveType().equalsIgnoreCase("Approved")) {
	 * 
	 * Optional<com.hp.BMT.services.dao.KeywordDetails> uds = keywordRepo
	 * .findById(Integer.parseInt(sar.getRequestData().getActualId())); if
	 * (uds.isPresent()) { com.hp.BMT.services.dao.KeywordDetails ud = uds.get();
	 * 
	 * ud.setActive(sar.getRequestData().getActive());
	 * ud.setBase_Language_Keyword_Text(sar.getRequestData().
	 * getBaseLanguageKeywordText());
	 * ud.setCategory(sar.getRequestData().getCategory());
	 * ud.setCountry(sar.getRequestData().getCountry());
	 * ud.setHp_options_scoring(sar.getRequestData().getHpOptionsScoring());
	 * ud.setHp_total_options(sar.getRequestData().getHpTotalOptions()); //
	 * ud.setId(sar.getRequestData().getActualId());
	 * ud.setKeyword_text(sar.getRequestData().getKeywordText());
	 * ud.setKeyword_type_3(sar.getRequestData().getKeywordType3());
	 * ud.setKW_id_serial(sar.getRequestData().getKeywordId());
	 * ud.setKW_language(sar.getRequestData().getLanguage());
	 * ud.setSub_category(sar.getRequestData().getSubCategory());
	 * ud.setKeyword_type(sar.getRequestData().getkeywordType());
	 * ud.setMarket(sar.getRequestData().getMarket()); keywordRepo.save(ud);
	 * 
	 * }
	 * keywordHistoryRepo.changeStatusByIdAndUniqueId(sar.getRequestData().getId(),
	 * "Approved"); } else if (sar.getApproveType().equalsIgnoreCase("Rejected")) {
	 * keywordHistoryRepo.changeStatusByIdAndUniqueId(sar.getRequestData().getId(),
	 * "Rejected"); } } else { throw new Exception(); } }
	 * 
	 * @Override
	 * 
	 * public List<KeywordHistoryResponse>
	 * getListOfHistoryItemsForKeyword(UrlHistoryRequest uhr) throws IOException {
	 * List<KeywordDetailsHistory> udh = new ArrayList<KeywordDetailsHistory>();
	 * 
	 * if (!uhr.getRequestType().equalsIgnoreCase("All")) { if
	 * (!uhr.getRequestStatus().equalsIgnoreCase("All")) { udh =
	 * keywordHistoryRepo.findByRequestAndStatusTypes(uhr.getRequestType(),
	 * uhr.getRequestStatus(), uhr.getUsername()); } else { udh =
	 * keywordHistoryRepo.findByRequestAndStatusTypesForAllStatus(uhr.getRequestType
	 * (), uhr.getUsername()); } } else if
	 * (!uhr.getRequestStatus().equalsIgnoreCase("All")) { udh =
	 * keywordHistoryRepo.findByRequestAndStatusTypesForAllTypes(uhr.
	 * getRequestStatus(), uhr.getUsername()); } else { udh =
	 * keywordHistoryRepo.findByUser(uhr.getUsername()); }
	 * 
	 * List<KeywordHistoryResponse> uhrList = new
	 * ArrayList<KeywordHistoryResponse>();
	 * 
	 * for (KeywordDetailsHistory udhitem : udh) { KeywordHistoryResponse uhre = new
	 * KeywordHistoryResponse(); byte[] content = udhitem.getAttachment(); if
	 * (content == null) content = new byte[0];
	 * 
	 * InputStream is = new BufferedInputStream(new ByteArrayInputStream(content));
	 * 
	 * String mimeType = URLConnection.guessContentTypeFromStream(is);
	 * 
	 * if (mimeType == null) mimeType = "text/plain";
	 * 
	 * uhre.setActualId(udhitem.getActualId() == null ? "-1" :
	 * udhitem.getActualId().equals("") ? "-1" : udhitem.getActualId());
	 * uhre.setAttachment("data:" + mimeType + ";base64," +
	 * Base64.getEncoder().encodeToString(content));
	 * uhre.setCategory(udhitem.getCategory() == null ? "N/A" :
	 * udhitem.getCategory().equals("") ? "N/A" : udhitem.getCategory());
	 * uhre.setComment(udhitem.getComment() == null ? "N/A" :
	 * udhitem.getComment().equals("") ? "N/A" : udhitem.getComment());
	 * uhre.setCountry(udhitem.getCountry() == null ? "N/A" :
	 * udhitem.getCountry().equals("") ? "N/A" : udhitem.getCountry());
	 * uhre.setHpOptionsScoring(udhitem.getHpOptionsScoring() == null ? "N/A" :
	 * udhitem.getHpOptionsScoring().equals("") ? "N/A" :
	 * udhitem.getHpOptionsScoring());
	 * uhre.setHpTotalOptions(udhitem.getHpTotalOptions() == null ? "N/A" :
	 * udhitem.getHpTotalOptions().equals("") ? "N/A" :
	 * udhitem.getHpTotalOptions()); uhre.setKeywordId(udhitem.getKeywordId() ==
	 * null ? "N/A" : udhitem.getKeywordId().equals("") ? "N/A" :
	 * udhitem.getKeywordId()); uhre.setKeywordText(udhitem.getKeywordText() == null
	 * ? "N/A" : udhitem.getKeywordText().equals("") ? "N/A" :
	 * udhitem.getKeywordText()); uhre.setKeywordType3(udhitem.getKeywordType3() ==
	 * null ? "N/A" : udhitem.getKeywordType3().equals("") ? "N/A" :
	 * udhitem.getKeywordType3()); uhre.setLanguage(udhitem.getLanguage() == null ?
	 * "N/A" : udhitem.getLanguage().equals("") ? "N/A" : udhitem.getLanguage());
	 * uhre.setManager(udhitem.getManager() == null ? "N/A" :
	 * udhitem.getManager().equals("") ? "N/A" : udhitem.getManager());
	 * uhre.setMarket( udhitem.getMarket() == null ? "N/A" :
	 * udhitem.getMarket().equals("") ? "N/A" : udhitem.getMarket());
	 * uhre.setRequestedBy(udhitem.getRequestedBy() == null ? "N/A" :
	 * udhitem.getRequestedBy().equals("") ? "N/A" : udhitem.getRequestedBy());
	 * uhre.setRequestedOn(udhitem.getRequestedOn() == null ? "N/A" :
	 * udhitem.getRequestedOn().equals("") ? "N/A" : udhitem.getRequestedOn());
	 * uhre.setRequesterEmail(udhitem.getRequesterEmail() == null ? "N/A" :
	 * udhitem.getRequesterEmail().equals("") ? "N/A" :
	 * udhitem.getRequesterEmail()); uhre.setRequestType(udhitem.getRequestType() ==
	 * null ? "N/A" : udhitem.getRequestType().equals("") ? "N/A" :
	 * udhitem.getRequestType()); uhre.setStatus( udhitem.getStatus() == null ?
	 * "N/A" : udhitem.getStatus().equals("") ? "N/A" : udhitem.getStatus());
	 * uhre.setSubCategory(udhitem.getSubCategory() == null ? "N/A" :
	 * udhitem.getSubCategory().equals("") ? "N/A" : udhitem.getSubCategory());
	 * uhre.setKeywordType(udhitem.getKeywordType() == null ? "N/A" :
	 * udhitem.getKeywordType().equals("") ? "N?A" : udhitem.getKeywordType());
	 * uhre.setId(udhitem.getId());
	 * 
	 * uhrList.add(uhre); } return uhrList; }
	 * 
	 * public User getUserService(String authUsername, String email) {
	 * 
	 * User user = new User(); user.setName(authUsername); user.setEmail(email);
	 * user.setAuthenticated("true");
	 * 
	 * user.setRole(userRepo.findRoleByEmail(email)); return user;
	 * 
	 * }
	 * 
	 * public com.hp.BMT.services.entity.UrlDetails
	 * getDbDataForUrlID(UrlHistoryResponse uhr) {
	 * 
	 * com.hp.BMT.services.dao.UrlDetails ud =
	 * urlRepo.findById(Integer.parseInt(uhr.getActualId())).get();
	 * com.hp.BMT.services.entity.UrlDetails ude = new
	 * com.hp.BMT.services.entity.UrlDetails();
	 * 
	 * ude.setActive(ud.getActive() == null ? "N/A" : ud.getActive().equals("") ?
	 * "N/A" : ud.getActive()); ude.setAmplify(ud.getAmplify() == null ? "N/A" :
	 * ud.getAmplify().equals("") ? "N/A" : ud.getAmplify());
	 * ude.setCountry(ud.getCOUNTRY() == null ? "N/A" : ud.getCOUNTRY().equals("") ?
	 * "N/A" : ud.getCOUNTRY()); ude.setDetailedPartnerId(ud.getDetailedPartnerID()
	 * == null ? "N/A" : ud.getDetailedPartnerID().equals("") ? "N/A" :
	 * ud.getDetailedPartnerID()); ude.setId(ud.getId());
	 * ude.setMarket(ud.getMarket() == null ? "N/A" : ud.getMarket().equals("") ?
	 * "N/A" : ud.getMarket()); ude.setMasterPartnerID(ud.getMasterPartnerID() ==
	 * null ? "N/A" : ud.getMasterPartnerID().equals("") ? "N/A" :
	 * ud.getMasterPartnerID()); ude.setOfficialPartnerName( ud.getPartnerName() ==
	 * null ? "N/A" : ud.getPartnerName().equals("") ? "N/A" : ud.getPartnerName());
	 * ude.setPrimaryLanguage(ud.getPrimaryLanguage() == null ? "N/A" :
	 * ud.getPrimaryLanguage().equals("") ? "N/A" : ud.getPrimaryLanguage());
	 * ude.setSiteName(ud.getSiteNames() == null ? "N/A" :
	 * ud.getSiteNames().equals("") ? "N/A" : ud.getSiteNames());
	 * ude.setType(ud.getTYPE() == null ? "N/A" : ud.getTYPE().equals("") ? "N/A" :
	 * ud.getTYPE()); return ude;
	 * 
	 * }
	 * 
	 * public KeywordDetails getDbDataForKeywordID(KeywordHistoryResponse khr) {
	 * com.hp.BMT.services.dao.KeywordDetails ud =
	 * keywordRepo.findById(Integer.parseInt(khr.getActualId())).get();
	 * com.hp.BMT.services.entity.KeywordDetails ude = new
	 * com.hp.BMT.services.entity.KeywordDetails();
	 * 
	 * ude.setCategory(ud.getCategory() == null ? "N/A" :
	 * ud.getCategory().equals("") ? "N/A" : ud.getCategory());
	 * ude.setCountry(ud.getCountry() == null ? "N/A" : ud.getCountry().equals("") ?
	 * "N/A" : ud.getCountry()); ude.setHpOptionsScoring(ud.getHp_options_scoring()
	 * == null ? "N/A" : ud.getHp_options_scoring().equals("") ? "N/A" :
	 * ud.getHp_options_scoring()); ude.setHpTotalOptions(ud.getHp_total_options()
	 * == null ? "N/A" : ud.getHp_total_options().equals("") ? "N/A" :
	 * ud.getHp_total_options()); ude.setId(ud.getId()); ude.setKeywordId(
	 * ud.getKW_id_serial() == null ? "N/A" : ud.getKW_id_serial().equals("") ?
	 * "N/A" : ud.getKW_id_serial()); ude.setKeywordText( ud.getKeyword_text() ==
	 * null ? "N/A" : ud.getKeyword_text().equals("") ? "N/A" :
	 * ud.getKeyword_text()); ude.setKeywordType( ud.getKeyword_type() == null ?
	 * "N/A" : ud.getKeyword_type().equals("") ? "N/A" : ud.getKeyword_type());
	 * ude.setKeywordType3(ud.getKeyword_type_3() == null ? "N/A" :
	 * ud.getKeyword_type_3().equals("") ? "N/A" : ud.getKeyword_type_3());
	 * ude.setLanguage( ud.getKW_language() == null ? "N/A" :
	 * ud.getKW_language().equals("") ? "N/A" : ud.getKW_language());
	 * ude.setMarket(ud.getMarket() == null ? "N/A" : ud.getMarket().equals("") ?
	 * "N/A" : ud.getMarket()); ude.setSubCategory( ud.getSub_category() == null ?
	 * "N/A" : ud.getSub_category().equals("") ? "N/A" : ud.getSub_category());
	 * return ude; }
	 * 
	 * public String getLastKeywordUsed() { String kwidserial =
	 * kwSerialIdRepo.findMaxKwidserial(); return "KW_" +
	 * (Integer.parseInt(kwidserial.substring(3, kwidserial.length())) + 1); }
	 * 
	 * @Override public UserDetails getUserService(String authUsername, String
	 * email) { // TODO Auto-generated method stub return null; }
	 * 
	 * @Override public List<AccountSummaryDetails> getAllRecords() { // TODO
	 * Auto-generated method stub return null; }
	 */
	

	@SuppressWarnings("deprecation")
	public String getValueOf(Cell cell) {
		
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			System.out.println(cell.getStringCellValue()+".............str val");
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_FORMULA:
			System.out.println(cell.getStringCellValue()+".............str val");
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_NUMERIC:
			double d = cell.getNumericCellValue();
			if (d == Math.floor(d)) {
			    return String.format("%.0f", d);
			} else {
			    return Double.toString(d);
			}
			
			/*
			 * System.out.println(cell.getNumericCellValue()+".............num val"); String
			 * result = Double.toString(cell.getNumericCellValue());
			 * if(result.contains(".")) result = result.split("\\.")[0];
			 * System.out.println(result+".............num val without dot"); return result;
			 */

		case Cell.CELL_TYPE_BLANK:
			return "";

		case Cell.CELL_TYPE_BOOLEAN:
			return Boolean.toString(cell.getBooleanCellValue());

		default:
			return "N/A";

		}
	}
}