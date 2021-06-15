package com.hp.QppColumbia.services.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao;
import com.hp.QppColumbia.services.dao.AccountSummaryDetailsHistory;
import com.hp.QppColumbia.services.entity.ASFilterResponse;
import com.hp.QppColumbia.services.entity.AccountSummaryDetails;
import com.hp.QppColumbia.services.entity.AccountSummaryFilterDetails;
import com.hp.QppColumbia.services.entity.EditASDetails;
import com.hp.QppColumbia.services.entity.UserDetails;
import com.hp.QppColumbia.services.repository.AccountSummaryHistoryDetails;

/**
 * @author kovoor.prajwal
 * @Date : January, 2021
 *
 */

public interface DataService {
	/*
	 * public List<PersonEntity> readStatusForTablesCheck();
	 * 
	 * String uploadEvidence1(MultipartFile evidence1, String accountName) throws
	 * WebappException;
	 * 
	 * public List<UrlDetails> findByFields(UrlDetailsFilter searchByFields) throws
	 * WebappException;
	 * 
	 * public List<KeywordDetails> findByFields(KeywordDetailsFilter
	 * keywordDetailsFilter) throws WebappException;
	 * 
	 * public List<KeywordDetails> findByFields(KeywordDetailsArrayFilter
	 * keywordDetailsFilter) throws WebappException;
	 * 
	 * public UrlFiltterDetailsResponse getUrlFilterDetails();
	 * 
	 * public KeywordDetailsResponse getKeywordFilterDetails();
	 * 
	 * public void ModifyUrlDetailsService(MultipartFile attachment,
	 * com.hp.BMT.services.entity.ModifyUrlDetails urlDetails) throws IOException;
	 * 
	 * public void AddKeywordDetailsService( MultipartFile attachment,
	 * com.hp.BMT.services.entity.AddKeywordDetails keywordDetails) throws
	 * IOException;
	 * 
	 * public void DeleteUrlDetailsService(MultipartFile attachment,
	 * com.hp.BMT.services.entity.DeleteUrlDetails urlDetails) throws IOException;
	 * 
	 * public void AddUrlDetailsService(MultipartFile attachment,
	 * com.hp.BMT.services.entity.AddUrlDetails keywordDetails) throws IOException;
	 * 
	 * public void ModifyKeywordDetailsService( MultipartFile attachment,
	 * com.hp.BMT.services.entity.ModifyKeywordDetails keywordDetails) throws
	 * IOException;
	 * 
	 * public void DeleteKeywordDetailsService(MultipartFile attachment,
	 * com.hp.BMT.services.entity.DeleteKeywordDetails keywordDetails) throws
	 * IOException;
	 * 
	 * public List<UrlDetailsHistory> getUrlHistoryForUser(String requesterName);
	 * 
	 * public List<KeywordDetailsHistory> getKeywordHistoryForUser(String
	 * requesterName);
	 * 
	 * //public List<MCLDetails> getAllMCLDetails();
	 * 
	 * public List<com.hp.BMT.services.dao.MCLDetails>
	 * getFilteredMCLDetails(MCLDetails mcldetails);
	 * 
	 * public void addMCLDetails(MCLDetails mcldetails);
	 * 
	 * public void deleteMCLDetails(MCLDetailsId mcldetailsid);
	 * 
	 * public void editMCLDetails(MCLDetailsId mcldetailsid);
	 * 
	 * public List<String> getListOfMarkets();
	 * 
	 * public List<String> getListOfCountries(String market);
	 * 
	 * public List<String> getListOfPrimaryLanguages(String market, String country);
	 * 
	 * public List<String> getListOfSecondaryLanguages(String market,String country,
	 * String primaryLanguage);
	 * 
	 * List<com.hp.BMT.services.dao.MCLDetails> getAllMCLDetails();
	 * 
	 * List<com.hp.BMT.services.dao.CSDetails> getAllCSDetails();
	 * 
	 * public List<com.hp.BMT.services.dao.CSDetails> getFilteredCSDetails(CSDetails
	 * csDetails);
	 * 
	 * public void addCSDetails(CSDetails mcldetails);
	 * 
	 * public void deleteCSDetails(CSDetailsId mcldetails);
	 * 
	 * public void modifyCSDetails(CSDetailsId mcldetails);
	 * 
	 * public List<String> getListOfCategories();
	 * 
	 * public List<String> getListOfSubcategories(String category);
	 * 
	 * public UrlFiltterDetailsResponse getUrlFilterDetailsNew(UrlDetailsFilter
	 * udf);
	 * 
	 * public KeywordDetailsResponse getKeywordFilterDetailsNew(KeywordDetailsFilter
	 * udf);
	 * 
	 * public List<UrlHistoryResponse> getListOfHistoryItemsForUrl(UrlHistoryRequest
	 * uhr) throws IOException;
	 * 
	 * public User getUserService(String authUsername, String email);
	 * 
	 * public void SubmitApprovalForUrl(SubmitApprovalReqForUrl sar) throws
	 * Exception;
	 * 
	 * public List<KeywordHistoryResponse>
	 * getListOfHistoryItemsForKeyword(UrlHistoryRequest uhr) throws IOException;
	 * 
	 * public UrlDetails getDbDataForUrlID(UrlHistoryResponse uhr);
	 * 
	 * public KeywordDetails getDbDataForKeywordID(KeywordHistoryResponse khr);
	 * 
	 * public void SubmitApprovalForKeyword(SubmitApprovalReqForKeyword sar) throws
	 * Exception;
	 */
	public UserDetails getUserService(String authUsername, String email);

	public List<AccountSummaryDetailsDao> getAllRecords();

	public boolean editRecord(EditASDetails editRecord);

	public List<AccountSummaryDetailsHistory> getAllHistoryRecords(String homologatedName);
	
	public List<AccountSummaryDetailsHistory> getAllTheHistoryRecords();

	public List<AccountSummaryDetailsDao> getAllFilteredRecords(
			AccountSummaryFilterDetails accountSummaryFilterDetails);

	public ASFilterResponse getFilterDetails();

	public String uploadRecords(MultipartFile attachment, String submittedBy, String submittedOn, String comment) throws IOException;

	public AccountSummaryDetailsDao getRecordDetails(String homologatedName);
}