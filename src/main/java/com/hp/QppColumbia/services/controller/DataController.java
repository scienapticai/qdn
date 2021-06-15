package com.hp.QppColumbia.services.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hp.QppColumbia.services.util.RestListInfo;

import io.swagger.annotations.ApiOperation;

import com.hp.QppColumbia.services.bean.HomoName;
import com.hp.QppColumbia.services.dao.AccountSummaryDetailsDao;
import com.hp.QppColumbia.services.dao.AccountSummaryDetailsHistory;

import com.hp.QppColumbia.services.entity.ASFilterResponse;
import com.hp.QppColumbia.services.entity.AccountSummaryDetails;
import com.hp.QppColumbia.services.entity.AccountSummaryFilterDetails;
import com.hp.QppColumbia.services.entity.EditASDetails;
import com.hp.QppColumbia.services.entity.UserDetails;
import com.hp.QppColumbia.services.repository.AccountSummaryRepo;
import com.hp.QppColumbia.services.repository.UserDetailsRepo;
import com.hp.QppColumbia.services.service.DataService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DataController {
	@Autowired
	private DataService dataService;

	@SuppressWarnings("unused")
	@Autowired
	private AccountSummaryRepo accountSummaryRepo;

	@SuppressWarnings("unused")
	@Autowired
	private UserDetailsRepo userDetailsRepo;

	static final Logger logger = Logger.getLogger(DataController.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = "/getUserDetails")
	public @ResponseBody RestListInfo<UserDetails> getUserDetails(
			@RequestHeader(value = "X-Auth-Username", required = false) String authUsername,
			@RequestHeader(value = "X-Auth-Email", required = false) String email) {

		RestListInfo<UserDetails> info = new RestListInfo<UserDetails>();
		try {
			List<UserDetails> status = new ArrayList(Arrays.asList(dataService.getUserService(authUsername, email))); // dataService.readStatusForTablesCheck();
			info.setOperationCode(200);
			info.setData(status);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
			info.setOperationCode(500);
		}
		System.out.println("ended Controller");
		return info;
	}

	/*
	 * @GetMapping(value = "/getUrlFilterDetails") public @ResponseBody
	 * RestListInfo<UrlFiltterDetailsResponse> getUrlFilterDetails() {
	 * 
	 * RestListInfo<UrlFiltterDetailsResponse> info = new
	 * RestListInfo<UrlFiltterDetailsResponse>(); try {
	 * List<UrlFiltterDetailsResponse> status = new
	 * ArrayList<UrlFiltterDetailsResponse>(
	 * Arrays.asList(dataService.getUrlFilterDetails()));
	 * info.setOperationCode(200); info.setData(status); } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @PostMapping(value = "/getUrlFilterDetailsNew") public @ResponseBody
	 * RestListInfo<UrlFiltterDetailsResponse> getUrlFilterDetailsNew(
	 * 
	 * @RequestBody UrlDetailsFilter udf) {
	 * 
	 * RestListInfo<UrlFiltterDetailsResponse> info = new
	 * RestListInfo<UrlFiltterDetailsResponse>(); try {
	 * List<UrlFiltterDetailsResponse> status = new
	 * ArrayList<UrlFiltterDetailsResponse>(
	 * Arrays.asList(dataService.getUrlFilterDetailsNew(udf)));
	 * info.setOperationCode(200); info.setData(status); } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @PostMapping(value = "/getKeywordFilterDetailsNew") public @ResponseBody
	 * RestListInfo<KeywordDetailsResponse> getKeywordFilterDetailsNew(
	 * 
	 * @RequestBody KeywordDetailsFilter udf) {
	 * 
	 * RestListInfo<KeywordDetailsResponse> info = new
	 * RestListInfo<KeywordDetailsResponse>(); try { List<KeywordDetailsResponse>
	 * status = new ArrayList<KeywordDetailsResponse>(
	 * Arrays.asList(dataService.getKeywordFilterDetailsNew(udf)));
	 * info.setOperationCode(200); info.setData(status); } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @GetMapping(value = "/getKeywordFilterDetails") public @ResponseBody
	 * RestListInfo<KeywordDetailsResponse> getKeywordFilterDetails() {
	 * 
	 * RestListInfo<KeywordDetailsResponse> info = new
	 * RestListInfo<KeywordDetailsResponse>(); try { List<KeywordDetailsResponse>
	 * status = new ArrayList<KeywordDetailsResponse>(
	 * Arrays.asList(dataService.getKeywordFilterDetails()));
	 * info.setOperationCode(200); info.setData(status); } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 */
	@GetMapping(value = "/getAllRecords")
	public @ResponseBody RestListInfo<AccountSummaryDetailsDao> getAllRecords() {

		RestListInfo<AccountSummaryDetailsDao> info = new RestListInfo<AccountSummaryDetailsDao>();

		try {
			List<AccountSummaryDetailsDao> status = dataService.getAllRecords();
			info.setOperationCode(200);

			info.setData(status);
		} catch (Exception e) {
			e.printStackTrace();
			info.setOperationCode(500);
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}

	@PostMapping(value = "/getFilteredRecords")
	public @ResponseBody RestListInfo<AccountSummaryDetailsDao> getFilteredRecords(
			@RequestBody AccountSummaryFilterDetails asfd)
			throws com.hp.QppColumbia.services.exception.WebappException {

		RestListInfo<AccountSummaryDetailsDao> info = new RestListInfo<AccountSummaryDetailsDao>();
		try {
			List<AccountSummaryDetailsDao> status = dataService
					.getAllFilteredRecords(asfd); /* dataService.findByFields(urlDetails); */
			info.setOperationCode(200);
			info.setData(status);
		} catch (Exception e) {
			e.printStackTrace();
			info.setOperationCode(500);
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}

	@SuppressWarnings("unused")
	@PostMapping(value = "/editRecordDetails")
	public @ResponseBody boolean editRecordDetails(@RequestBody EditASDetails editASDetails)
			throws com.hp.QppColumbia.services.exception.WebappException {

		RestListInfo<AccountSummaryDetailsDao> info = new RestListInfo<AccountSummaryDetailsDao>();
		try {
			boolean status = dataService.editRecord(editASDetails); /* dataService.editRecord(urlDetails); */
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
			return false;
		}

	}

	@PostMapping(value = "/getHistoryDetails")
	public @ResponseBody RestListInfo<AccountSummaryDetailsHistory> getHistoryDetails(
			@RequestBody HomoName homologatedName) {

		RestListInfo<AccountSummaryDetailsHistory> info = new RestListInfo<AccountSummaryDetailsHistory>();

		try {
			List<AccountSummaryDetailsHistory> status = dataService.getAllHistoryRecords(homologatedName.getHomologatedName());
			info.setOperationCode(200);

			info.setData(status);
		} catch (Exception e) {
			e.printStackTrace();
			info.setOperationCode(500);
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}

	@GetMapping(value = "/getAllHistoryDetails")
	public @ResponseBody RestListInfo<AccountSummaryDetailsHistory> getAllHistoryDetails() {

		RestListInfo<AccountSummaryDetailsHistory> info = new RestListInfo<AccountSummaryDetailsHistory>();

		try {
			List<AccountSummaryDetailsHistory> status = dataService.getAllTheHistoryRecords();
			info.setOperationCode(200);

			info.setData(status);
		} catch (Exception e) {
			e.printStackTrace();
			info.setOperationCode(500);
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}

	@GetMapping(value = "/getFilterDetails")
	public RestListInfo<ASFilterResponse> getFilterDetails() {
		RestListInfo<ASFilterResponse> info = new RestListInfo<ASFilterResponse>();

		try {
			List<ASFilterResponse> status = new ArrayList(Arrays.asList(dataService.getFilterDetails()));
			info.setOperationCode(200);

			info.setData(status);
		} catch (Exception e) {
			e.printStackTrace();
			info.setOperationCode(500);
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}

	@SuppressWarnings("unused")
	@ApiOperation(value = "Add Bulk Record Details")
	@PostMapping(value = "/uploadRecords")
	public @ResponseBody String uploadRecords(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "submittedBy", required = false) String submittedBy,
			@RequestParam(value = "submittedOn", required = false) String submittedOn,
			@RequestParam(value = "comment", required = false) String comment)
			throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		try {
			String response = dataService.uploadRecords(file, submittedBy, submittedOn, comment);

			return response;
		} catch (Exception e) {
			e.printStackTrace();

			logger.error("Exception :: " + e.getMessage());
		}
		return "false";
	}

	@SuppressWarnings("unused")
	@ApiOperation(value = "Add Bulk Record Details")
	@PostMapping(value = "/getRecordDetails")
	public @ResponseBody RestListInfo<AccountSummaryDetailsDao> getRecordDetails(
			@RequestBody HomoName homologatedName) {
		RestListInfo<AccountSummaryDetailsDao> status = new RestListInfo<AccountSummaryDetailsDao>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<AccountSummaryDetailsDao> accountSummaryDetailsDao = new ArrayList<AccountSummaryDetailsDao>(
					Arrays.asList(dataService.getRecordDetails(homologatedName.getHomologatedName())));
			status.setData(accountSummaryDetailsDao);
			status.setMessage("Success!");
			status.setOperationCode(200);
			return status;
		} catch (Exception e) {
			e.printStackTrace();

			logger.error("Exception :: " + e.getMessage());
		}
		return null;
	}

	/*
	 * @PostMapping(value = "/download/urldetails", produces =
	 * "application/octet-stream") public void
	 * downloadFilteredUrlDetails(@RequestBody UrlDetailsFilter urlDetails) {
	 * 
	 * RestListInfo<UrlDetails> info = new RestListInfo<UrlDetails>(); try {
	 * List<UrlDetails> status = null; info.setOperationCode(200);
	 * info.setData(status); } catch (Exception e) { e.printStackTrace();
	 * info.setOperationCode(500); logger.error("Exception :: " + e.getMessage()); }
	 * 
	 * }
	 * 
	 * @PostMapping(value = "/download/keyworddetails", produces =
	 * "application/octet-stream") public void
	 * downloadFilteredKeywordDetails(@RequestBody KeywordDetailsFilter urlDetails)
	 * {
	 * 
	 * RestListInfo<KeywordDetails> info = new RestListInfo<KeywordDetails>(); try {
	 * List<KeywordDetails> status = null; info.setOperationCode(200);
	 * info.setData(status); } catch (Exception e) { e.printStackTrace();
	 * info.setOperationCode(500); logger.error("Exception :: " + e.getMessage()); }
	 * 
	 * }
	 * 
	 * @GetMapping(value = "/getAllKeywordDetails") public @ResponseBody
	 * RestListInfo<KeywordDetails> getAllKeywordDetails(@RequestParam String
	 * pagenumber,
	 * 
	 * @RequestParam String pagesize) {
	 * 
	 * RestListInfo<KeywordDetails> info = new RestListInfo<KeywordDetails>(); try {
	 * List<KeywordDetails> status = null; info.setOperationCode(200);
	 * info.setData(status); } catch (Exception e) { e.printStackTrace();
	 * info.setOperationCode(500); logger.error("Exception :: " + e.getMessage()); }
	 * System.out.println("ended Controller"); return info; }
	 * 
	 * @PostMapping(value = "/getFilteredKeywordDetails") public @ResponseBody
	 * RestListInfo<KeywordDetails> getFilteredKeywordDetails(
	 * 
	 * @RequestBody KeywordDetailsFilter keywordDetailsFilter) {
	 * 
	 * RestListInfo<KeywordDetails> info = new RestListInfo<KeywordDetails>(); try {
	 * List<KeywordDetails> status = dataService .findByFields(keywordDetailsFilter)
	 * .stream().collect(Collectors.toList()) ; info.setOperationCode(200);
	 * info.setData(status); } catch (Exception e) { e.printStackTrace();
	 * info.setOperationCode(500); logger.error("Exception :: " + e.getMessage()); }
	 * System.out.println("ended Controller"); return info;
	 * 
	 * }
	 * 
	 * @PostMapping(value = "/editUrlDetails", consumes = { "multipart/form-data" },
	 * produces = "application/json") public @ResponseBody boolean
	 * editUrlDetails(@RequestParam("urlDetails") String model,
	 * 
	 * @RequestParam(value = "file", required = false) MultipartFile file) throws
	 * JsonMappingException, JsonProcessingException {
	 * 
	 * RestListInfo<UrlDetails> info = new RestListInfo<UrlDetails>(); ObjectMapper
	 * mapper = new ObjectMapper(); ModifyUrlDetails modelDTO =
	 * mapper.readValue(model, ModifyUrlDetails.class); try {
	 * dataService.ModifyUrlDetailsService(file, modelDTO);
	 * info.setOperationCode(200); return true; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return false; }
	 * 
	 * @PostMapping(value = "/editKeywordDetails") public @ResponseBody boolean
	 * editKeywordDetails(
	 * 
	 * @RequestParam("keywordsDetails") String model ,
	 * 
	 * @RequestParam(value = "file", required = false) MultipartFile file ) throws
	 * JsonMappingException, JsonProcessingException {
	 * 
	 * ObjectMapper mapper = new ObjectMapper(); ModifyKeywordDetails modelDTO =
	 * mapper.readValue(model, ModifyKeywordDetails.class); try {
	 * dataService.ModifyKeywordDetailsService( file, modelDTO); return true; }
	 * catch (Exception e) { e.printStackTrace(); logger.error("Exception :: " +
	 * e.getMessage()); } return false; }
	 * 
	 * @PostMapping(value = "/deleteUrlDetails") public @ResponseBody boolean
	 * deleteUrlDetails(@RequestParam("urlDetails") String model,
	 * 
	 * @RequestParam(value = "file", required = false) MultipartFile file) throws
	 * JsonMappingException, JsonProcessingException {
	 * 
	 * ObjectMapper mapper = new ObjectMapper(); DeleteUrlDetails modelDTO =
	 * mapper.readValue(model, DeleteUrlDetails.class); try {
	 * dataService.DeleteUrlDetailsService(file, modelDTO); return true; } catch
	 * (Exception e) { e.printStackTrace(); logger.error("Exception :: " +
	 * e.getMessage()); } return false; }
	 * 
	 * @PostMapping(value = "/deleteKeywordDetails") public @ResponseBody boolean
	 * deleteKeywordDetails(@RequestParam("keywordDetails") String model,
	 * 
	 * @RequestParam(value = "file", required = false) MultipartFile file) throws
	 * JsonMappingException, JsonProcessingException {
	 * 
	 * ObjectMapper mapper = new ObjectMapper(); DeleteKeywordDetails modelDTO =
	 * mapper.readValue(model, DeleteKeywordDetails.class); try {
	 * dataService.DeleteKeywordDetailsService(file, modelDTO); return true; } catch
	 * (Exception e) { e.printStackTrace(); logger.error("Exception :: " +
	 * e.getMessage()); } return false; }
	 * 
	 * @PostMapping(value = "/addKeywordDetails") public @ResponseBody boolean
	 * addKeywordDetails(
	 * 
	 * @RequestParam("keywordDetails") String model ,
	 * 
	 * @RequestParam(value = "file", required = false) MultipartFile file ) throws
	 * JsonMappingException, JsonProcessingException {
	 * 
	 * RestListInfo<AddKeywordDetails> info = new RestListInfo<AddKeywordDetails>();
	 * ObjectMapper mapper = new ObjectMapper(); AddKeywordDetails modelDTO =
	 * mapper.readValue(model, AddKeywordDetails.class); try {
	 * dataService.AddKeywordDetailsService( file, modelDTO);
	 * info.setOperationCode(200); return true; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return false;
	 * 
	 * }
	 * 
	 * @GetMapping(value = "/getAdminDetails") public @ResponseBody
	 * RestListInfo<AdminDetails> getAdminDetails(@RequestParam String userName) {
	 * RestListInfo<AdminDetails> info = new RestListInfo<AdminDetails>();
	 * 
	 * try { List<AdminDetails> status = null; info.setOperationCode(200);
	 * info.setData(status); } catch (Exception e) { e.printStackTrace();
	 * logger.error("Exception :: " + e.getMessage()); info.setOperationCode(500); }
	 * System.out.println("ended Controller"); return info; }
	 * 
	 * @GetMapping(value = "/getreqUrlQueueItems") public @ResponseBody
	 * RestListInfo<UrlDetails> getreqUrlQueueItems(@RequestParam String adminName,
	 * 
	 * @RequestParam String admimUrl) { RestListInfo<UrlDetails> info = new
	 * RestListInfo<UrlDetails>();
	 * 
	 * try { List<UrlDetails> status = null; info.setOperationCode(200);
	 * info.setData(status); } catch (Exception e) { e.printStackTrace();
	 * logger.error("Exception :: " + e.getMessage()); info.setOperationCode(500); }
	 * System.out.println("ended Controller"); return info; }
	 * 
	 * @GetMapping(value = "/getreqKeywordQueueItems") public @ResponseBody
	 * RestListInfo<KeywordDetails> getreqKeywordQueueItems(@RequestParam String
	 * adminName,
	 * 
	 * @RequestParam String admimUrl) { RestListInfo<KeywordDetails> info = new
	 * RestListInfo<KeywordDetails>(); try { List<KeywordDetails> status = null;
	 * info.setOperationCode(200); info.setData(status); } catch (Exception e) {
	 * e.printStackTrace(); logger.error("Exception :: " + e.getMessage());
	 * info.setOperationCode(500); } System.out.println("ended Controller"); return
	 * info; }
	 * 
	 * @PostMapping(value = "/addCSDetails") public @ResponseBody boolean
	 * addCSDetails(@RequestBody CSDetails keywordDetails) { try {
	 * dataService.addCSDetails(keywordDetails);
	 * 
	 * return true; } catch (Exception e) { e.printStackTrace();
	 * logger.error("Exception :: " + e.getMessage()); } return false; }
	 * 
	 * @PostMapping(value = "/editCSDetails") public @ResponseBody boolean
	 * editCSDetails(@RequestBody CSDetailsId keywordDetails) { try {
	 * dataService.modifyCSDetails(keywordDetails); return true; } catch (Exception
	 * e) { e.printStackTrace(); logger.error("Exception :: " + e.getMessage()); }
	 * return false; }
	 * 
	 * @PostMapping(value = "/deleteCSDetails") public @ResponseBody boolean
	 * deleteCSDetails(@RequestBody CSDetailsId keywordDetails) { try {
	 * dataService.deleteCSDetails(keywordDetails); return true; } catch (Exception
	 * e) { e.printStackTrace(); logger.error("Exception :: " + e.getMessage()); }
	 * System.out.println("ended Controller"); return false; }
	 * 
	 * @ApiOperation(value = "Add Url Details")
	 * 
	 * @PostMapping(value = "/addUrlDetails") public @ResponseBody boolean
	 * addUrlDetails(@RequestParam("urlDetails") String model,
	 * 
	 * @RequestParam(value = "file", required = false) MultipartFile file) throws
	 * JsonMappingException, JsonProcessingException {
	 * 
	 * RestListInfo<AddUrlDetails> info = new RestListInfo<AddUrlDetails>();
	 * ObjectMapper mapper = new ObjectMapper(); AddUrlDetails modelDTO =
	 * mapper.readValue(model, AddUrlDetails.class); try {
	 * dataService.AddUrlDetailsService(file, modelDTO); info.setOperationCode(200);
	 * return true; } catch (Exception e) { e.printStackTrace();
	 * info.setOperationCode(500); logger.error("Exception :: " + e.getMessage()); }
	 * return false; }
	 * 
	 * public @ResponseBody boolean uploadScreenShot(@RequestParam("evidence1")
	 * MultipartFile evidence1,
	 * 
	 * @RequestHeader(value = "X-Auth-Username", required = false) String
	 * authUsername) {
	 * 
	 * return false; }
	 * 
	 * @GetMapping(value = "/getUrlHistoryDetails")
	 * 
	 * @ResponseBody RestListInfo<UrlDetailsHistory>
	 * getUrlHistoryDetails(@RequestParam("requesterName") String requesterName) {
	 * RestListInfo<UrlDetailsHistory> info = new RestListInfo<UrlDetailsHistory>();
	 * try { List<UrlDetailsHistory> status =
	 * dataService.getUrlHistoryForUser(requesterName); status.forEach(udh -> {
	 * udh.setActive(udh.getActive() == null? "N/A" :
	 * udh.getActive().trim().equals("") ? "N/A" : udh.getActive());
	 * udh.setActivecolumn(udh.getActivecolumn() == null? "N/A" :
	 * udh.getActivecolumn().trim().equals("") ? "N/A" : udh.getActivecolumn());
	 * udh.setAmplify(udh.getAmplify() == null ? "N/A" :
	 * udh.getAmplify().trim().equals("") ? "N/A" : udh.getAmplify());
	 * udh.setApprovernm(udh.getApprovernm() == null? "N/A" :
	 * udh.getApprovernm().trim().equals("") ? "N/A" : udh.getApprovernm());
	 * udh.setComment(udh.getComment() == null? "N/A" :
	 * udh.getComment().trim().equals("") ? "N/A" : udh.getComment());
	 * udh.setCountry(udh.getCountry() == null? "N/A" :
	 * udh.getCountry().trim().equals("") ? "N/A" : udh.getCountry());
	 * udh.setDetailedPartnerId(udh.getDetailedPartnerId() == null? "N/A" :
	 * udh.getDetailedPartnerId().trim().equals("") ? "N/A" :
	 * udh.getDetailedPartnerId()); udh.setManager(udh.getManager() == null? "N/A" :
	 * udh.getManager().trim().equals("") ? "N/A" : udh.getManager());
	 * udh.setMarket(udh.getMarket() == null? "N/A" :
	 * udh.getMarket().trim().equals("") ? "N/A" : udh.getMarket());
	 * udh.setMasterPartnerID(udh.getMasterPartnerID() == null? "N/A" :
	 * udh.getMasterPartnerID().trim().equals("") ? "N/A" :
	 * udh.getMasterPartnerID());
	 * udh.setOfficialPartnerName(udh.getOfficialPartnerName() == null? "N/A" :
	 * udh.getOfficialPartnerName().trim().equals("") ? "N/A" :
	 * udh.getOfficialPartnerName());
	 * udh.setPrimaryLanguage(udh.getPrimaryLanguage() == null? "N/A" :
	 * udh.getPrimaryLanguage().trim().equals("") ? "N/A" :
	 * udh.getPrimaryLanguage()); udh.setRequestedBy(udh.getRequestedBy() == null?
	 * "N/A" : udh.getRequestedBy().trim().equals("") ? "N/A" :
	 * udh.getRequestedBy()); udh.setRequestedOn(udh.getRequestedOn() == null? "N/A"
	 * : udh.getRequestedOn().trim().equals("") ? "N/A" : udh.getRequestedOn());
	 * udh.setRequesterEmail(udh.getRequesterEmail() == null? "N/A" :
	 * udh.getRequesterEmail().trim().equals("") ? "N/A" : udh.getRequesterEmail());
	 * udh.setRequestType(udh.getRequestType() == null? "N/A" :
	 * udh.getRequestType().trim().equals("") ? "N/A" : udh.getRequestType());
	 * udh.setSiteName(udh.getSiteName() == null? "N/A" :
	 * udh.getSiteName().trim().equals("") ? "N/A" : udh.getSiteName());
	 * udh.setStatus(udh.getStatus() == null? "N/A" :
	 * udh.getStatus().trim().equals("") ? "N/A" : udh.getStatus());
	 * udh.setStatuscolumn(udh.getStatuscolumn() == null? "N/A" :
	 * udh.getStatuscolumn().trim().equals("") ? "N/A" : udh.getStatuscolumn());
	 * udh.setType(udh.getType() == null? "N/A" : udh.getType().trim().equals("") ?
	 * "N/A" : udh.getType());
	 * 
	 * }); Collections.reverse(status); info.setOperationCode(200);
	 * 
	 * info.setData(status); return info; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); }
	 * 
	 * return info; }
	 * 
	 * @GetMapping(value = "/getKeywordHistoryDetails")
	 * 
	 * @ResponseBody RestListInfo<KeywordDetailsHistory>
	 * getKeywordHistoryDetails(@RequestParam("requesterName") String requesterName)
	 * { RestListInfo<KeywordDetailsHistory> info = new
	 * RestListInfo<KeywordDetailsHistory>(); try { List<KeywordDetailsHistory>
	 * status = dataService.getKeywordHistoryForUser(requesterName);
	 * status.forEach(udh -> { udh.setCategory(udh.getCategory() == null? "N/A" :
	 * udh.getCategory().trim().equals("") ? "N/A" : udh.getCategory());
	 * udh.setCountry(udh.getCountry() == null? "N/A" :
	 * udh.getCountry().trim().equals("") ? "N/A" : udh.getCountry());
	 * udh.setHpOptionsScoring(udh.getHpOptionsScoring() == null ? "N/A" :
	 * udh.getHpOptionsScoring().trim().equals("") ? "N/A" :
	 * udh.getHpOptionsScoring()); udh.setHpTotalOptions(udh.getHpTotalOptions() ==
	 * null? "N/A" : udh.getHpTotalOptions().trim().equals("") ? "N/A" :
	 * udh.getHpTotalOptions()); udh.setKeywordId(udh.getKeywordId() == null? "N/A"
	 * : udh.getKeywordId().trim().equals("") ? "N/A" : udh.getKeywordId());
	 * udh.setKeywordText(udh.getKeywordText() == null? "N/A" :
	 * udh.getKeywordText().trim().equals("") ? "N/A" : udh.getKeywordText());
	 * udh.setKeywordType(udh.getKeywordType() == null? "N/A" :
	 * udh.getKeywordType().trim().equals("") ? "N/A" : udh.getKeywordType());
	 * udh.setKeywordType3(udh.getKeywordType3() == null? "N/A" :
	 * udh.getKeywordType3().trim().equals("") ? "N/A" : udh.getKeywordType3());
	 * udh.setLanguage(udh.getLanguage() == null? "N/A" :
	 * udh.getLanguage().trim().equals("") ? "N/A" : udh.getLanguage());
	 * udh.setManager(udh.getManager() == null? "N/A" :
	 * udh.getManager().trim().equals("") ? "N/A" : udh.getManager());
	 * udh.setMarket(udh.getMarket() == null? "N/A" :
	 * udh.getMarket().trim().equals("") ? "N/A" : udh.getMarket());
	 * udh.setRequestedBy(udh.getRequestedBy() == null? "N/A" :
	 * udh.getRequestedBy().trim().equals("") ? "N/A" : udh.getRequestedBy());
	 * udh.setRequestedOn(udh.getRequestedOn() == null? "N/A" :
	 * udh.getRequestedOn().trim().equals("") ? "N/A" : udh.getRequestedOn());
	 * udh.setRequesterEmail(udh.getRequesterEmail() == null? "N/A" :
	 * udh.getRequesterEmail().trim().equals("") ? "N/A" : udh.getRequesterEmail());
	 * udh.setSubCategory(udh.getSubCategory() == null? "N/A" :
	 * udh.getSubCategory().trim().equals("") ? "N/A" : udh.getSubCategory());
	 * udh.setRequestType(udh.getRequestType() == null? "N/A" :
	 * udh.getRequestType().trim().equals("") ? "N/A" : udh.getRequestType());
	 * udh.setStatus(udh.getStatus() == null? "N/A" :
	 * udh.getStatus().trim().equals("") ? "N/A" : udh.getStatus());
	 * 
	 * }); Collections.reverse(status); info.setOperationCode(200);
	 * 
	 * info.setData(status); return info; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); }
	 * 
	 * return info; }
	 * 
	 * @GetMapping(value = "/getAllMCLDetails")
	 * 
	 * @ResponseBody RestListInfo<com.hp.BMT.services.dao.MCLDetails>
	 * getAllMCLDetails() { RestListInfo<com.hp.BMT.services.dao.MCLDetails> info =
	 * new RestListInfo<com.hp.BMT.services.dao.MCLDetails>(); try {
	 * List<com.hp.BMT.services.dao.MCLDetails> status =
	 * dataService.getAllMCLDetails();
	 * 
	 * info.setOperationCode(200);
	 * 
	 * info.setData(status); return info; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @PostMapping(value = "/getFilteredMCLDetails")
	 * 
	 * @ResponseBody RestListInfo<com.hp.BMT.services.dao.MCLDetails>
	 * getFilteredMCLDetails(@RequestBody MCLDetails mcldetails) {
	 * RestListInfo<com.hp.BMT.services.dao.MCLDetails> info = new
	 * RestListInfo<com.hp.BMT.services.dao.MCLDetails>(); try {
	 * List<com.hp.BMT.services.dao.MCLDetails> status =
	 * dataService.getFilteredMCLDetails(mcldetails);
	 * 
	 * info.setOperationCode(200);
	 * 
	 * info.setData(status); return info; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @PostMapping(value = "/addMCLDetails")
	 * 
	 * @ResponseBody boolean addMCLDetails(@RequestBody MCLDetails mcldetails) {
	 * 
	 * try { dataService.addMCLDetails(mcldetails);
	 * 
	 * return true; } catch (Exception e) { e.printStackTrace();
	 * logger.error("Exception :: " + e.getMessage()); } return false; }
	 * 
	 * @PostMapping(value = "/deleteMCLDetails")
	 * 
	 * @ResponseBody boolean deleteMCLDetails(@RequestBody MCLDetailsId mcldetails)
	 * { try { dataService.deleteMCLDetails(mcldetails);
	 * 
	 * return true; } catch (Exception e) { e.printStackTrace();
	 * logger.error("Exception :: " + e.getMessage()); } return false; }
	 * 
	 * @PostMapping(value = "/editMCLDetails")
	 * 
	 * @ResponseBody boolean editMCLDetails(@RequestBody MCLDetailsId mcldetailsid)
	 * { try { dataService.editMCLDetails(mcldetailsid);
	 * 
	 * return true; } catch (Exception e) { e.printStackTrace();
	 * logger.error("Exception :: " + e.getMessage()); } return false; }
	 * 
	 * @GetMapping(value = "/getListOfMarkets")
	 * 
	 * @ResponseBody RestListInfo<String> getListOfMarkets() { RestListInfo<String>
	 * info = new RestListInfo<String>(); try { List<String> status =
	 * dataService.getListOfMarkets();
	 * 
	 * info.setOperationCode(200);
	 * 
	 * info.setData(status); return info; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @GetMapping(value = "/getListOfCountries")
	 * 
	 * @ResponseBody RestListInfo<String> getListOfCountries(@RequestParam String
	 * market) { RestListInfo<String> info = new RestListInfo<String>(); try {
	 * List<String> status = dataService.getListOfCountries(market);
	 * 
	 * info.setOperationCode(200);
	 * 
	 * info.setData(status); return info; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @GetMapping(value = "/getListOfPrimaryLanguages")
	 * 
	 * @ResponseBody public RestListInfo<String>
	 * getListOfPrimaryLanguages(@RequestParam String country, @RequestParam String
	 * market) { RestListInfo<String> info = new RestListInfo<String>(); try {
	 * List<String> status = dataService.getListOfPrimaryLanguages(market, country);
	 * 
	 * info.setOperationCode(200);
	 * 
	 * info.setData(status); return info; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @GetMapping(value = "/getListOfSecondaryLanguages")
	 * 
	 * @ResponseBody RestListInfo<String> getListOfSecondaryLanguages(@RequestParam
	 * String country, @RequestParam String market,
	 * 
	 * @RequestParam String primaryLanguage) { RestListInfo<String> info = new
	 * RestListInfo<String>(); try { List<String> status =
	 * dataService.getListOfSecondaryLanguages(market, country, primaryLanguage);
	 * 
	 * info.setOperationCode(200);
	 * 
	 * info.setData(status); return info; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @GetMapping(value = "/getAllCSDetails")
	 * 
	 * @ResponseBody RestListInfo<com.hp.BMT.services.dao.CSDetails>
	 * getAllCSDetails() { RestListInfo<com.hp.BMT.services.dao.CSDetails> info =
	 * new RestListInfo<com.hp.BMT.services.dao.CSDetails>(); try {
	 * List<com.hp.BMT.services.dao.CSDetails> status =
	 * dataService.getAllCSDetails();
	 * 
	 * info.setOperationCode(200);
	 * 
	 * info.setData(status); return info; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @PostMapping(value = "/getFilteredCSDetails")
	 * 
	 * @ResponseBody RestListInfo<com.hp.BMT.services.dao.CSDetails>
	 * getFilteredCSDetails(@RequestBody CSDetails csDetails) {
	 * RestListInfo<com.hp.BMT.services.dao.CSDetails> info = new
	 * RestListInfo<com.hp.BMT.services.dao.CSDetails>(); try {
	 * List<com.hp.BMT.services.dao.CSDetails> status =
	 * dataService.getFilteredCSDetails(csDetails);
	 * 
	 * info.setOperationCode(200);
	 * 
	 * info.setData(status); return info; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @GetMapping(value = "/getListOfCategories")
	 * 
	 * @ResponseBody RestListInfo<String> getListOfCategories() {
	 * RestListInfo<String> info = new RestListInfo<String>(); try { List<String>
	 * status = dataService.getListOfCategories();
	 * 
	 * info.setOperationCode(200);
	 * 
	 * info.setData(status); return info; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @PostMapping(value = "/getListOfSubcategories")
	 * 
	 * @ResponseBody RestListInfo<String> getListOfSubcategories(@RequestBody
	 * Category category) { RestListInfo<String> info = new RestListInfo<String>();
	 * try { List<String> status =
	 * dataService.getListOfSubcategories(category.getCategory());
	 * 
	 * info.setOperationCode(200);
	 * 
	 * info.setData(status); return info; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @PostMapping(value = "/getListOfHistoryItemsForUrl")
	 * 
	 * @ResponseBody RestListInfo<UrlHistoryResponse>
	 * getListOfHistoryItemsForUrl(@RequestBody UrlHistoryRequest uhr) {
	 * RestListInfo<UrlHistoryResponse> info = new
	 * RestListInfo<UrlHistoryResponse>(); try { List<UrlHistoryResponse> status =
	 * dataService.getListOfHistoryItemsForUrl(uhr);
	 * 
	 * info.setOperationCode(200);
	 * 
	 * info.setData(status); return info; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @PostMapping(value = "/SubmitApprovalForUrl")
	 * 
	 * @ResponseBody boolean SubmitApprovalForUrl(@RequestBody
	 * SubmitApprovalReqForUrl sar) { try { dataService.SubmitApprovalForUrl(sar);
	 * 
	 * return true; } catch (Exception e) { e.printStackTrace();
	 * logger.error("Exception :: " + e.getMessage()); } return false; }
	 * 
	 * @PostMapping(value = "/getListOfHistoryItemsForKeyword")
	 * 
	 * @ResponseBody RestListInfo<KeywordHistoryResponse>
	 * getListOfHistoryItemsForKeyword(@RequestBody UrlHistoryRequest uhr) {
	 * RestListInfo<KeywordHistoryResponse> info = new
	 * RestListInfo<KeywordHistoryResponse>(); try { List<KeywordHistoryResponse>
	 * status = dataService.getListOfHistoryItemsForKeyword(uhr);
	 * 
	 * info.setOperationCode(200);
	 * 
	 * info.setData(status); return info; } catch (Exception e) {
	 * e.printStackTrace(); info.setOperationCode(500); logger.error("Exception :: "
	 * + e.getMessage()); } return info; }
	 * 
	 * @PostMapping(value = "/SubmitApprovalForKeyword")
	 * 
	 * @ResponseBody boolean SubmitApprovalForKeyword(@RequestBody
	 * SubmitApprovalReqForKeyword sar) { try {
	 * dataService.SubmitApprovalForKeyword(sar); return true; } catch (Exception e)
	 * { e.printStackTrace(); logger.error("Exception :: " + e.getMessage()); }
	 * return false; }
	 * 
	 * @ApiOperation(value = "Get the username")
	 * 
	 * @GetMapping(value = "/getUser")
	 * 
	 * @ResponseBody public RestListInfo<User> currentUserName(
	 * 
	 * @RequestHeader(value = "X-Auth-Username", required = false) String
	 * authUsername, @RequestHeader(value = "X-Auth-Email", required = false) String
	 * email ) { RestListInfo<User> info = new RestListInfo<User>(); try { User
	 * status = dataService.getUserService("prajwal", "prjwal@hp.com"); List<User>
	 * user = new ArrayList<User>(); user.add(status);
	 * 
	 * info.setOperationCode(200);
	 * 
	 * info.setData(user); return info;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); info.setOperationCode(500);
	 * logger.error("Exception :: " + e.getMessage()); } return info;
	 * 
	 * }
	 * 
	 * @PostMapping(value = "/getDbDataForUrlID")
	 * 
	 * @ResponseBody RestListInfo<com.hp.BMT.services.entity.UrlDetails>
	 * getDbDataForUrlID(@RequestBody UrlHistoryResponse uhr) {
	 * RestListInfo<com.hp.BMT.services.entity.UrlDetails> info = new
	 * RestListInfo<com.hp.BMT.services.entity.UrlDetails>(); try {
	 * List<com.hp.BMT.services.entity.UrlDetails> lud = new
	 * ArrayList<com.hp.BMT.services.entity.UrlDetails>();
	 * com.hp.BMT.services.entity.UrlDetails ud =
	 * dataService.getDbDataForUrlID(uhr); info.setOperationCode(200); lud.add(ud);
	 * info.setData(lud); return info;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); logger.error("Exception :: " +
	 * e.getMessage()); } return info; }
	 * 
	 * @PostMapping(value = "/getDbDataForkeywordID")
	 * 
	 * @ResponseBody RestListInfo<com.hp.BMT.services.entity.KeywordDetails>
	 * getDbDataForKeywordID(
	 * 
	 * @RequestBody KeywordHistoryResponse uhr) {
	 * RestListInfo<com.hp.BMT.services.entity.KeywordDetails> info = new
	 * RestListInfo<com.hp.BMT.services.entity.KeywordDetails>(); try {
	 * List<com.hp.BMT.services.entity.KeywordDetails> lud = new
	 * ArrayList<com.hp.BMT.services.entity.KeywordDetails>();
	 * com.hp.BMT.services.entity.KeywordDetails ud =
	 * dataService.getDbDataForKeywordID(uhr); info.setOperationCode(200);
	 * lud.add(ud); info.setData(lud); return info;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); logger.error("Exception :: " +
	 * e.getMessage()); } return info; }
	 */
	/*
	 * @PostMapping(value = "/getUserRole")
	 * 
	 * @ResponseBody RestListInfo<com.hp.BMT.services.entity.KeywordDetails>
	 * getUserRole(
	 * 
	 * @RequestHeader(value = "X-Auth-Username", required = false) String
	 * authUsername, @RequestHeader(value = "X-Auth-Email", required = false) String
	 * email ) { RestListInfo<com.hp.BMT.services.entity.KeywordDetails> info = new
	 * RestListInfo<com.hp.BMT.services.entity.KeywordDetails>(); try {
	 * List<com.hp.BMT.services.entity.KeywordDetails> lud = new
	 * ArrayList<com.hp.BMT.services.entity.KeywordDetails>();
	 * com.hp.BMT.services.entity.KeywordDetails ud =
	 * dataService.getDbDataForKeywordID(uhr); info.setOperationCode(200);
	 * lud.add(ud); info.setData(lud); return info;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); logger.error("Exception :: " +
	 * e.getMessage()); } return info; }
	 */
}