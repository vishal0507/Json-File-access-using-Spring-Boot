package pt.switchon.simulator.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import jakarta.annotation.PostConstruct;
import pt.switchon.simulator.ExceptionHandler.CustomeApiNameException;
import pt.switchon.simulator.ExceptionHandler.CustomeSuccessException;
import pt.switchon.simulator.ExceptionHandler.CustomerFailureException;
import pt.switchon.simulator.ExceptionHandler.CustomerFailureListException;

@Service
public class JsonService {
	private static final ObjectMapper obj = new ObjectMapper();
	private Map<String, JsonNode> walletCache = new HashMap<>();
	private final String success = "Success";
	private static String jsonFile = System.getenv("SWITCH_ON_JSON");

	/**
	 * This method is used to pre load the json file data and to store in cache
	 * variable.
	 * 
	 * @throws ParseException
	 */
	@PostConstruct
	public void initialize() {
		try {
			byte[] resource = Files.readAllBytes(Paths.get(jsonFile));
			String jsonData = new String(resource);
			JsonNode root = obj.readTree(jsonData);
			Iterator<Entry<String, JsonNode>> customerEntries = root.fields();
			while (customerEntries.hasNext()) {
				Map.Entry<String, JsonNode> customerEntry = customerEntries.next();
				String key = customerEntry.getKey();
				JsonNode value = customerEntry.getValue();
				walletCache.put(key, value);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new CustomeApiNameException("There is no json file.");
		}

	}

	/**
	 * This Method is used to validate the mobile number from the request
	 * 
	 * @param mobileNumber
	 * @param apiName
	 * @return JsonNode
	 */
	public JsonNode mobileValidation(String name, String apiName) {
		JsonNode getNode = walletCache.get(apiName);
		if (getNode != null) {
			if (getNode.has("FailureTestOn")) {
				String failureTest = getNode.get("FailureTestOn").asText();
				switch (failureTest) {
				case "MobileNumber":
					JsonNode data = mobileNumberTest(name, apiName, failureTest);
					return data;
				}
			}
		} else {
			throw new CustomeApiNameException("There is no such apiname.");
		}
		return null;
	}

	/**
	 * This method is used to return the mobile number request test
	 * 
	 * @param mobileNumber
	 * @param apiName
	 * @param failureTest
	 * @return JsonNode
	 */
	private JsonNode mobileNumberTest(String name, String apiName, String failureTest) {
		ObjectMapper obj = new ObjectMapper();
		JsonNode req;
		try {
			req = obj.readTree(name);
			List<Integer> mobileFailureList;
			JsonNode mobileCheck = walletCache.get("FailureList").get(failureTest);
			if (mobileCheck != null) {
				mobileFailureList = obj.readValue(walletCache.get("FailureList").get(failureTest).toString(),
						new TypeReference<>() {
						});

				boolean numberCheck = mobileFailureList.contains(Integer.parseInt(req.get("name").asText()));
				if (numberCheck == true) {
					return apiFailure(apiName);
				} else {
					return apiSuccess(apiName, failureTest, req);
				}

			} else {
				throw new CustomerFailureListException(
						"There is no mobile failure list is json file for this apiname.");
			}

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * This method is used to give the JsonNode response of the failure case.
	 * 
	 * @param apiName
	 * @return
	 */
	private JsonNode apiFailure(String apiName) {
		JsonNode response = walletCache.get(apiName).get("Failure");
		if (response != null) {
			return response;
		} else {
			throw new CustomerFailureException("There is no failure field in the apiname.");
		}
	}

	/**
	 * This method is used to give the JsonNode response of the success case.
	 * 
	 * @param apiName
	 * @param failureTest
	 * @param req
	 * @return
	 */
	private JsonNode apiSuccess(String apiName, String failureTest, JsonNode req) {
		JsonNode successResponse = walletCache.get(apiName).get(success);
		if (successResponse == null) {
			throw new CustomeSuccessException("There is no success field in the paritcular apiname.");
		} else {
			String ecoCheck = walletCache.get(apiName).get("EchoCheck").toString();
			boolean ecoChecker = ecoCheck.contains(failureTest);
			ObjectNode ecoResponse = (ObjectNode) successResponse;
			if (ecoChecker == true) {
				switch (apiName) {
				case "createWallet":
					((ObjectNode) ecoResponse).put("MobileNo", req.get("name").asText());
					return ecoResponse;
				case "getWallet":
					String cardProxyNumber = getRandomCardProxyNumber(apiName);
					JsonNode cardProxy = new TextNode(cardProxyNumber);
					((ObjectNode) ecoResponse).put("MobileNo", req.get("name").asText());
					ecoResponse.set("CardProxyNumber", cardProxy);
					return ecoResponse;

				}

			} else {
				String randomMobileNumber = getRandomPhoneNumber(apiName, failureTest);
				JsonNode randMobileNumber = new TextNode(randomMobileNumber);
				switch (apiName) {
				case "createWallet":
					ecoResponse.put("MobileNo", randMobileNumber.asText());
					return ecoResponse;
				case "getWallet":
					String cardProxyNumber = getRandomCardProxyNumber(apiName);
					JsonNode cardProxy = new TextNode(cardProxyNumber);
					((ObjectNode) ecoResponse).put("MobileNo", randomMobileNumber);
					ecoResponse.set("CardProxyNumber", cardProxy);
					return ecoResponse;
				}

			}
		}
		return successResponse;

	}

	/**
	 * This method is used to return random phone number.
	 * 
	 * @return String
	 */

	private String getRandomPhoneNumber(String apiName, String failureTest) {

		JsonNode randomFields = walletCache.get(apiName).get("Random_Fields");
		JsonNode mobileFields = null;
		for (JsonNode a : randomFields) {
			String val = a.get("Tag").asText();
			if (val.equals(failureTest)) {
				mobileFields = a;
			}
		}
		int length = mobileFields.get("Length").asInt();
		String prefix = mobileFields.get("Prefix").asText();
		System.out.print("Length " + length + "Prefix " + prefix.length());
		String tag = generateRandom(length - (prefix.length() - 1));
		return prefix + tag;

	}

	/**
	 * This method is used to return random CardProxyNumber
	 * 
	 * @param apiName
	 * @return
	 */
	private String getRandomCardProxyNumber(String apiName) {
		JsonNode randomFields = walletCache.get(apiName).get("Random_Fields");
		JsonNode cardProxy = null;
		for (JsonNode j : randomFields) {
			String val = j.get("Tag").asText();
			if (val.equals("CardProxyNumber")) {
				cardProxy = j;
			}
		}
		int length = cardProxy.get("Length").asInt();
		String prefix = cardProxy.get("Prefix").asText();
		int remainingNumber = length - prefix.length();
		String proxyNumber = generateRandom(remainingNumber);
		return prefix + proxyNumber;

	}

	/**
	 * This method is used to get the random number
	 * 
	 * @param length
	 * @return String
	 */
	private String generateRandom(int length) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
}
