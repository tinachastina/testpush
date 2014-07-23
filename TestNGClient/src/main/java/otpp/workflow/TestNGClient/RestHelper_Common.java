package otpp.workflow.TestNGClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestHelper_Common {
	
	private static String serviceUrl = "http://localhost:8080/engine-rest";
	
	protected final static String by = "?";
	protected final static String and = "&";
	
    protected final static String Active = "active={active}";
    protected final static String Suspended = "suspended={suspended}";
    protected final static String IncidentId = "incidentId={incidentId}";
    protected final static String IncidentType = "incidentType={incidentType}";
    protected final static String IncidentMessage = "incidentMessage={incidentMessage}";
    protected final static String IncidentMessageLike = "incidentMessageLike={incidentMessageLike}";
    protected final static String sortBy = "sortBy={sortBy}";
    protected final static String sortOrder = "sortOrder={sortOrder}";
    protected final static String firstResult = "firstResult={firstResult}";
    protected final static String maxResults = "maxResults={maxResults}";
    private static String user = "demo";
    private static String password = "demo";

	/**
	 * 
	 * @return
	 */
	protected RestClient getMapperForJsonArray(){
		RestClient restTemplate = new RestClient(user, password);
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		return restTemplate;
	}
	/**
	 * 
	 * @return
	 */
	protected RestClient getMapperForJsonObject(){
		RestClient template = new RestClient(user, password);
        CustomDeserializer customConverter = new CustomDeserializer();
        List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
        list.add(new CustomDeserializer());
        template.setMessageConverters(list);
        return template;
	}
	
	protected RestClient getClient(){
		RestClient template = new RestClient(user, password);
		return template;
	}
	
	protected String getServiceurl() {
		return serviceUrl;
	}
}
