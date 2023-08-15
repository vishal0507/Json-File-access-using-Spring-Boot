package pt.switchon.simulator.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import pt.switchon.simulator.ExceptionHandler.CustomeApiNameException;
import pt.switchon.simulator.services.JsonService;

@RestController
public class PayPointCreateController {

	private final JsonService data;

	@Autowired
	public PayPointCreateController(JsonService data) {
		this.data = data;
	}

	@PostMapping("/createWallet")
	public ResponseEntity<JsonNode> createMobileNumber(@RequestBody String name) {
		JsonNode response = data.mobileValidation(name, "createWallet");
		if (response != null) {
//			if ((response.get("Response").get("Status").asText()).equals("success")) {
//				String a = response.get("Response").get("CallBack_Url").toString();
//				if (response.get("Response").get("CallBack_Url").toString() != null) {
//					OkHttpClient client = new OkHttpClient();
//					Request request = new Request.Builder().url("https://dummyjson.com/products/1").build();
//					try (Response res = client.newCall(request).execute()) {
//						Response e = res;
//						System.out.println(e);
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//
//				} else {
//					return ResponseEntity.ok(response);
//				}
//			}
			return ResponseEntity.ok(response);
		} else {
			throw new CustomeApiNameException("There is no such apiName name you provided is present ");
		}
	}

	@PostMapping("/getWallet")
	public ResponseEntity<JsonNode> getMobileNumber(@RequestBody String name) {
		JsonNode response = data.mobileValidation(name, "getWallet");
		if (response != null) {
			return ResponseEntity.ok(response);
		} else {
			throw new CustomeApiNameException("There is no such apiName name you provided is present ");
		}
	}

	@PostMapping("/refresh")
	public ResponseEntity<Map<String, String>> refreshDetails() {

		Map<String, String> response = new HashMap<>();
		data.initialize();
		response.put("Status", "00");
		response.put("Description", "Success");
		return ResponseEntity.ok(response);

	}

}
