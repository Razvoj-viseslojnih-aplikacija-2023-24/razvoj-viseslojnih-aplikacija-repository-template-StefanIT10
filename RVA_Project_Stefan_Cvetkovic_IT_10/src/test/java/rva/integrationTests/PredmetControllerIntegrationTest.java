package rva.integrationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import rva.models.Predmet;
import rva.models.Sud;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PredmetControllerIntegrationTest {
	
	@Autowired
	TestRestTemplate template;
	
	int highestId;
	
	void createHighestId() {
		ResponseEntity<List<Predmet>> response = template
				.exchange("/predmet", HttpMethod.GET,
						null,
						new ParameterizedTypeReference<List<Predmet>>() {} );
		ArrayList<Predmet> list = (ArrayList<Predmet>) response.getBody();
		for(int i = 0; i < list.size(); i++) {
			if(highestId <= list.get(i).getId()) {
				highestId = list.get(i).getId() + 1;
			}
		}
		
	}
	

	void getHighestId() {
		createHighestId();
		highestId--;
	}
	
	@Test
	@Order(1)
	void testGetAllPredmet() {
		ResponseEntity<List<Predmet>> response = template.exchange("/predmet", HttpMethod.GET, 
				null, new ParameterizedTypeReference<List<Predmet>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Predmet> predmeti = response.getBody();
		
		assertEquals(200, statusCode);
		assertTrue(!predmeti.isEmpty());
		
		
	}
	
	@Test
	@Order(2)
	void testGetPredmetById() {
		int id = 1;
		ResponseEntity<Predmet> response = template.exchange("/predmet/id/" + id, HttpMethod.GET, null, Predmet.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertNotNull(response.getBody());
		assertEquals(id, response.getBody().getId());
	}
	
	
	@Test
	@Order(3)
	void testGetPredmetByAktivan() {
	    boolean aktivan = true;
	    ResponseEntity<List<Predmet>> response = template.exchange("/predmet/aktivan/" + aktivan, HttpMethod.GET,
	            null, new ParameterizedTypeReference<List<Predmet>>() {});
	    int statusCode = response.getStatusCode().value();
	    List<Predmet> predmeti = response.getBody();

	    assertEquals(200, statusCode);
	    assertNotNull(response.getBody());
	    for (Predmet p : predmeti) {
	        assertTrue(p.isAktivan() == aktivan);
	    }
	}
	
	@Test
	@Order(4)
	void testCreatePredmet() {
		Predmet predmet = new Predmet();
		predmet.setBrojPr("POST brojPr");
		predmet.setOpis("POST opis");
		Date datum = new Date();
		predmet.setDatumPocetka(datum);
		predmet.setAktivan(false);
		
		HttpEntity<Predmet> entity = new HttpEntity<Predmet>(predmet);
		createHighestId();
		ResponseEntity<Predmet> response = template
				.exchange("/predmet", HttpMethod.POST,
						entity, Predmet.class);
		int statusCode = response.getStatusCode().value();
		
		assertTrue(response.hasBody());
		assertEquals(201, statusCode);
		assertEquals("/predmet/id/" + highestId, 
				response.getHeaders().getLocation().getPath());
		assertEquals(predmet.getBrojPr(), response.getBody().getBrojPr());
		assertEquals(predmet.getOpis(), response.getBody().getOpis());
		assertEquals(predmet.getDatumPocetka(), response.getBody().getDatumPocetka());
		assertEquals(predmet.isAktivan(), response.getBody().isAktivan());
	}
	
	@Test
	@Order(5)
	void testUpdatePredmet() {
		Predmet predmet = new Predmet();
		predmet.setAktivan(true);
		predmet.setBrojPr("PUT brojPr");
		
		HttpEntity<Predmet> entity = new HttpEntity<Predmet>(predmet);
		getHighestId();
		ResponseEntity<Predmet> response = template
				.exchange("/predmet/id/" + highestId, HttpMethod.PUT,
						entity, Predmet.class);
		int statusCode = response.getStatusCode().value();
		
		assertTrue(response.hasBody());
		assertEquals(200, statusCode);
		assertTrue(response.getBody() instanceof Predmet);
		assertEquals(predmet.getBrojPr(), response.getBody().getBrojPr());
		assertEquals(predmet.isAktivan(), response.getBody().isAktivan());
	}
	
	@Test
	@Order(6)
	void testDeletePredmetById() {
		getHighestId();
		ResponseEntity<String> response = template
				.exchange("/predmet/id/" + highestId, HttpMethod.DELETE,
						null, String.class);
	
		
		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().contains("has been deleted"));
	}
	

	@Test
	@Order(7)
	void testGetPredmetBySud() {
		long sudId = 1;
		ResponseEntity<List<Predmet>> response = template.exchange("/predmet/sud/" + sudId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Predmet>>(){});
		int statusCode = response.getStatusCode().value();
		List<Predmet> predmeti =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(predmeti.get(0));
		for(Predmet p: predmeti) {
			assertTrue(p.getSud().getId() == 1);
		}
	}
	

}
