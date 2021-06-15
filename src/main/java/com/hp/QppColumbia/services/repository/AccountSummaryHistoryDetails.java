package com.hp.QppColumbia.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hp.QppColumbia.services.dao.AccountSummaryDetailsHistory;

public interface AccountSummaryHistoryDetails extends JpaRepository<AccountSummaryDetailsHistory, Integer> {
	@Query(value = "Select * from account_summary_history where homologatedName = ?1", nativeQuery = true)
	List<com.hp.QppColumbia.services.dao.AccountSummaryDetailsHistory> findAllRecords(String homologatedName); // where requesternm = ?1

	@Query(value = "Select * from account_summary_history", nativeQuery = true)
	List<com.hp.QppColumbia.services.dao.AccountSummaryDetailsHistory> findAllTheRecords(); // where requesternm = ?1

}
