package YamlApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

@Service
public class YamlServices {

	public YamlServices() throws IOException {
		getCustomer();
	}

	Map<String, List<Map<String, Object>>> data;

	public void getCustomer() throws IOException {

		Yaml yaml = new Yaml();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("customer.yaml")) {
			if (input != null) {
				data = yaml.load(input);

			} else {
				throw new RuntimeException("Failed to load yaml files...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Map<String, Object>> createCard() {
		List<Map<String, Object>> e = data.get("CreateCard");
		return e;
	}

	public List<Map<String, Object>> getDetails() {
		List<Map<String, Object>> e = data.get("GetWallet");
		return e;
	}

}
