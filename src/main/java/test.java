import com.fasterxml.jackson.databind.ObjectMapper;
import com.softpower.chihuahua.datamodel.JslogOnErrorModel;

public class test {

	public static void main(String[] args) throws Exception {
		final String jsonStr = "{\"error\":{\"id\":20,\"createUser\":\"anonymousUser\",\"createTime\":1440067507905,\"modifyUser\":\"anonymousUser\",\"modifyTime\":1440067507905,\"appId\":1,\"clientId\":20,\"screenshotId\":null,\"sourcecodeId\":null,\"errMsg\":\"this is error message\",\"url\":\"http://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js\",\"line\":68,\"col\":-1,\"errCount\":2,\"errObj\":null},\"client\":{\"id\":20,\"navigatorAppVersion\":\"5.0 (Windows)\",\"navigatorCookieEnabled\":\"true\",\"navigatorLanguage\":\"zh-TW\",\"navigatorPlatform\":\"Win32\",\"navigatorUserAgent\":\"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0\",\"navigatorVendor\":\"\",\"screenWidth\":\"2144\",\"screenHeight\":\"1206\",\"screenAvailWidth\":\"2144\",\"screenAvailHeight\":\"1161\",\"screenColorDepth\":\"24\",\"screenPixelDepth\":\"24\",\"locationHref\":\"http://localhost:28080/chihuahua/welcome\",\"locationHostname\":\"localhost\",\"locationPathname\":\"/chihuahua/welcome\",\"locationProtocol\":\"http:\",\"documentWidth\":\"936\",\"documentHeight\":\"1027\"},\"screenshot\":null,\"sourcecode\":{\"mimetype\":\"html/text\",\"content\":\"this is sourcecode content\"},\"appKey\":\"3d9a24a783494c0b9ef6eb88a811a4a5\"}";
		ObjectMapper mapper = new ObjectMapper();
		JslogOnErrorModel model = mapper.readValue(jsonStr, JslogOnErrorModel.class);
		System.out.println(model);
	}

}
