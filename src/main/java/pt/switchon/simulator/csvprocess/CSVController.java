//package pt.switchon.simulator.csvprocess;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class CSVController {
//
//	@Autowired
//	private CsvService csv;
//
//	@PostMapping("/dataById")
//	public ResponseEntity<Customer> getDataById(@RequestParam String id) {
//		Customer data = csv.getDataById(id);
//		if (data != null) {
//			return ResponseEntity.ok(data);
//		} else {
//			throw new CustomerNotFoundException("No Such Id is present in CSV File.");
//		}
//	}
//
//	@PostMapping("/dataByName")
//	public ResponseEntity<List<Customer>> getDataByName(@RequestParam String name) {
//
//		List<Customer> data = csv.getDataByName(name);
//		if (!data.isEmpty()) {
//			return ResponseEntity.ok(data);
//		} else {
//			throw new CustomerNotFoundException("No Such Name is present in CSV File.");
//		}
//
//	}
//
//	@PostMapping("/refresh")
//	public ResponseEntity<Map<String, String>> refreshDetails() throws NumberFormatException, IOException {
//
//		Map<String, String> response = new HashMap<>();
//		csv.initalize();
//		response.put("Status", "00");
//		response.put("Description", "Success");
//		return ResponseEntity.ok(response);
//
//	}
//
//}
