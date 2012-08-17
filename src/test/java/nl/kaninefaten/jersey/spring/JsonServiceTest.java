package nl.kaninefaten.jersey.spring;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.springframework.web.context.ContextLoaderListener;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.MediaTypes;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class JsonServiceTest extends JerseyTest {

	public JsonServiceTest() throws Exception {
		super(
				new WebAppDescriptor.Builder(
						"com.sun.jersey.samples.springannotations.resources.jerseymanaged")
						.contextPath("spring")
						.initParam("com.sun.jersey.api.json.POJOMappingFeature", "true") // Add this init feature to be able to work with json objects
						.contextParam("contextConfigLocation",
								"classpath:/nl/kaninefaten/jersey/spring/applicationContext.xml")
						.servletClass(SpringServlet.class)
						.contextListenerClass(ContextLoaderListener.class)
						.build());
	}

	@Test
	public void doTestApplicationWadl() {
		WebResource webResource = resource();

		String wadl = webResource.path("application.wadl")
				.accept(MediaTypes.WADL).get(String.class);

		assertTrue(
				"Method: doTestApplicationWadl \nMessage: Something wrong, the returned "
						+ "WADL's length is not > 0", wadl.length() > 0);

	}
	
	
	@Test
	public void doTestJsonString() {

		WebResource webResource = resource();
		String returnedString = null;
		try{
			returnedString = webResource.path("/sample/json")
				.accept(MediaType.APPLICATION_JSON).get(String.class);
		}catch (Exception e){
			fail(e.getMessage());
		}
		assert(returnedString.startsWith("{"));;

	}

	
}
