package com.hp.QppColumbia.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao;
import com.hp.QppColumbia.services.entity.AccountSummaryDetails;

public interface AccountSummaryRepo extends JpaRepository<AccountSummaryDetailsDao, Integer> {
	@Query(value = "Select * from account_summary", nativeQuery = true)
	List<com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao> findAllRecords(); // where requesternm = ?1
	// and razonSocial=?2 and taxId=?3 and locationId = ?4 and suppliesStatus= ?5

	@Query(value = "Select * from account_summary where homologatedName = ?1", nativeQuery = true)
    com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao findRecordDetails(String homologatedName);
	
	@Query(value = "Select * from account_summary where homologatedName = ?1", nativeQuery = true)
    Optional <com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao> findRecordDetailsNew(String homologatedName); 
	
	@Query(value = "Select * from account_summary where homologatedName =?1", nativeQuery = true)
	List<com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao> findAllRecordsByHN(String HN); // where requesternm =
																									// ?1

	@Query(value = "Select * from account_summary where razonSocial =?1", nativeQuery = true)
	List<com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao> findAllRecordsByRazonSocial(String razonSocial); // where
																													// requesternm
																													// =
																													// ?1

	@Query(value = "Select * from account_summary where taxId =?1", nativeQuery = true)
	List<com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao> findAllRecordsByTaxId(String taxId); // where
																										// requesternm =
																										// ?1

	@Query(value = "Select * from account_summary where locationId =?1", nativeQuery = true)
	List<com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao> findAllRecordsByLocationId(String locationId); // where
																													// requesternm
																													// =
																													// ?1

	@Query(value = "Select * from account_summary where suppliesStatus =?1", nativeQuery = true)
	List<com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao> findAllRecordsBySuppliesStatus(
			String suppliesStatus); // where requesternm = ?1

	@Query(value = "Select DISTINCT(homologatedName) from account_summary", nativeQuery = true)
	List<String> findallHM();

	@Query(value = "Select DISTINCT(razonSocial) from account_summary", nativeQuery = true)
	List<String> findallRS();

	@Query(value = "Select DISTINCT(taxId) from account_summary", nativeQuery = true)
	List<String> findallTI();

	@Query(value = "Select DISTINCT(locationId) from account_summary", nativeQuery = true)
	List<String> findallLocationId();

	@Query(value = "Select DISTINCT(suppliesStatus) from account_summary", nativeQuery = true)
	List<String> findallSuppliesStatus();
}
