import java.util.Arrays;

import org.apache.catalina.util.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class test {

	public static void main(String[] args) throws Exception {
		final String authorization = "Basic " + Base64.encode("admin:admin123".getBytes());
		final String baseUrl = "http://localhost:28080/chihuahua/errors/scriptcode/1";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
		headers.add("Authorization", authorization);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		RestTemplate temp = new RestTemplate();
		try {
			ResponseEntity<String> jsonResp = temp.exchange(baseUrl, HttpMethod.GET, entity, String.class);
			HttpStatus httpStatus = jsonResp.getStatusCode();
			System.out.println(httpStatus);
			System.out.println(jsonResp.getBody());
		}catch(HttpClientErrorException hcee) {
			HttpStatus httpStatus = hcee.getStatusCode();
			System.out.println(httpStatus);
		}
	}

}
