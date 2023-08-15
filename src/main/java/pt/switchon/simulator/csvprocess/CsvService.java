//package pt.switchon.simulator.csvprocess;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import org.springframework.stereotype.Service;
//
//import jakarta.annotation.PostConstruct;
//
//@Service
//public class CsvService {
//	private Map<String, Customer> customerDatas = new HashMap<>();
//	private static String csvFile = System.getenv("SWITCH_ON_CSV");	
//
//	@PostConstruct
//	public void initalize() {
//		if (csvFile == null) {
//			throw new RuntimeException("There is no datas in csv files....");
//		}
//		try (InputStream inputStream = new FileInputStream(csvFile);
//				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//			String line;
//			while ((line = reader.readLine()) != null) {
//				String[] fields = line.split(",");
//				String id = fields[0];
//				String name = fields[1];
//				String age = fields[2];
//				String address = fields[3];
//				customerDatas.put(id, new Customer(id, name, age, address));
//			}
//		} catch (IOException e) {
//			throw new CsvFileException(csvFile);
//		}
//	}
//
//	public Customer getDataById(String id) {
//		return customerDatas.get(id);
//
//	}
//
//	public List<Customer> getDataByName(String name) {
//
//		List<Customer> list = new ArrayList<>();
//		for (Customer cus : customerDatas.values()) {
//			if (cus.getName().equals(name)) {
//				list.add(cus);
//			}
//		}
//		return list;
//	}
//
//}
