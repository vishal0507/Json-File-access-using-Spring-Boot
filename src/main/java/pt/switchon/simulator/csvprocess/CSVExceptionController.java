package pt.switchon.simulator.csvprocess;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CSVExceptionController {

	@ExceptionHandler(value = CsvFileException.class)
	public ResponseEntity<Map<String, String>> handleCsvException(CsvFileException c) {
		Map<String, String> response = new HashMap<>();
		response.put("Status", "01");
		response.put("Description", "Not a valid csv file");
		response.put("Id", c.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleCustomerException(CustomerNotFoundException c) {
		Map<String, String> response = new HashMap<>();
		response.put("Status", "01");
		response.put("Description", "Customer Not Found in CSV File");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

}
