package com.softpower.chihuahua.others;

import org.junit.Test;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Reference : https://malalanayake.wordpress.com/2014/06/27/spring-security-on-rest-api/
 * <pre>
 * [login failure]
 * curl -i -X POST -d username=admin -d password=admin1234 -c ./cookies.txt http://localhost:28080/chihuahua/errors/j_spring_security_check
 *
 * [login success (authorize OK)]
 * curl -i -X POST -d username=admin -d password=admin123  -c ./cookies.txt http://localhost:28080/chihuahua/errors/j_spring_security_check
 *
 * [login success (authorize NOT OK)]
 * curl -i -X POST -d username=user -d password=user123  -c ./cookies.txt http://localhost:28080/chihuahua/errors/j_spring_security_check
 *
 *
 * [get resource (without cookie)]
 * curl -i -H "Content-Type:text/plain" -X GET http://localhost:28080/chihuahua/errors/scriptcode/1
 *
 * [get resource (with cookie)]
 * curl -i -H "Content-Type:text/plain" -X GET -b ./cookies.txt http://localhost:28080/chihuahua/errors/scriptcode/1
 * </pre>
 */
public class RESTfulTest {
	final String baseURL = "http://localhost:28080/chihuahua/errors/j_spring_security_check";
	final String testURL = "http://localhost:28080/chihuahua/errors/scriptcode/1";

	private void testLogin(String usernameAndPassword) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		RestTemplate temp = new RestTemplate();
		try {
			final String url = baseURL + usernameAndPassword;
			ResponseEntity<String> resp = temp.exchange(url, HttpMethod.POST, entity, String.class);
			HttpStatus httpStatus = resp.getStatusCode();
			System.out.println(httpStatus);
			System.out.println(resp.getHeaders().get("Set-Cookie"));
		}catch(HttpClientErrorException hcee) {
			HttpStatus httpStatus = hcee.getStatusCode();
			System.out.println(httpStatus);
		}
	}

	@Test
	public void testLoginFailure() {
		testLogin("?username=admin&password=admin1234");
	}

	@Test
	public void testLoginSuccess() {
		testLogin("?username=admin&password=admin123");
	}

	@Test
	public void testLoginSuccess2() {
		testLogin("?username=user&password=user123");
	}

	private void testRequestTestUrl(String jSessionId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		if (StringUtils.isNotBlank(jSessionId)) {
			headers.set("Cookie", "JSESSIONID=" + jSessionId);
		}

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		RestTemplate temp = new RestTemplate();
		try {
			final String url = testURL;
			ResponseEntity<String> resp = temp.exchange(url, HttpMethod.GET, entity, String.class);
			HttpStatus httpStatus = resp.getStatusCode();
			System.out.println(httpStatus);
			System.out.println(resp.getBody());
		}catch(HttpClientErrorException hcee) {
			HttpStatus httpStatus = hcee.getStatusCode();
			System.out.println(httpStatus);
		}
	}

	@Test
	public void testRequestTestUrlWithoutCookie() {
		testRequestTestUrl(null);
	}

	@Test
	public void testRequestTestUrlWithCookie() {
		testRequestTestUrl("9B98038C09019BBECCAB4E31787D232B");
	}

}
