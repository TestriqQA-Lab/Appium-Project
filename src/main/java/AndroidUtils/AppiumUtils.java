package AndroidUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.testng.reporters.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {
	
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	
    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        // Use the updated method with Charset
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }

    public List<HashMap<String, String>> getJsonData(String filePath, String dataSet) throws IOException {
    // Parse the JSON file
    String jsonContent = new String(Files.readFile((InputStream) Paths.get(filePath)));
    
    // Create a JSON object to access specific datasets
    JSONObject jsonObject = new JSONObject(jsonContent);
    
    // Get the data for the requested dataset (e.g., "loginData" or "signUpData")
    JSONArray dataArray = jsonObject.getJSONArray(dataSet);
    
    List<HashMap<String, String>> data = new ArrayList<>();
    
    for (int i = 0; i < dataArray.length(); i++) {
        JSONObject obj = dataArray.getJSONObject(i);
        HashMap<String, String> map = new HashMap<>();
        obj.keySet().forEach(key -> map.put(key, obj.getString(key)));
        data.add(map);
    }
    
    return data;
}

    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
	
	service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Testriq\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
			.withIPAddress(ipAddress).usingPort(port).build();
			service.start();
			return service;
}
    public String getScreenshot(String testCaseName, AppiumDriver driver) throws IOException
    {
    	File source = driver.getScreenshotAs(OutputType.FILE);
    	String destinationFile = System.getProperty("user.dir") + "\\reports\\Screenshots"+testCaseName+".png";
    	FileUtils.copyFile(source, new File (destinationFile));
    	return destinationFile;
    }

}