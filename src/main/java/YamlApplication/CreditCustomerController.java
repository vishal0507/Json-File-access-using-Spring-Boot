//package com.example.api.Controller;
//
//import java.io.IOException;
//import java.util.List;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.api.CustomeExceptions.CustomerNotFoundException;
//import com.example.api.Modal.CreditCustomerModal;
//import com.example.api.Services.CreditCustomerServices;
//
//@RestController
//public class CreditCustomerController {
//	private final CreditCustomerServices customerService;
//
//	public CreditCustomerController(CreditCustomerServices customerService) {
//		this.customerService = customerService;
//	}
//
//	@GetMapping("yamlData/all")
//	public ResponseEntity<Object> getAllCustomers() throws IOException {
//		Object customers = customerService.getCustomer();
//		if (customers!=null) {
//			return new ResponseEntity<>(customers, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//
//	/*
//	 * @PostMapping("/yamlData/customer{id}") public
//	 * ResponseEntity<CreditCustomerModal> getCustomerDetails(@RequestParam String
//	 * id) {
//	 * 
//	 * CreditCustomerModal ans = customerService.getCustomerId(id); if (ans != null)
//	 * { return ResponseEntity.ok(ans); } else { throw new
//	 * CustomerNotFoundException("There is no such data present."); }
//	 * 
//	 * }
//	 * 
//	 * @PostMapping("/yamlData/customers") public List<CreditCustomerModal>
//	 * getCustomerAndInstitution(@RequestParam String id1, @RequestParam String id2)
//	 * { List<CreditCustomerModal> data =
//	 * customerService.getCustomerAndInstitutionDetails(id1, id2); if
//	 * (!data.isEmpty()) { return data; } else { throw new
//	 * CustomerNotFoundException("There is no such data present."); }
//	 * 
//	 * }
//	 */
//
//}
package YamlApplication;


