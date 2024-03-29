package rva.integrationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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

import rva.models.Sud;
import rva.models.Ucesnik;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UcesnikControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	int highestId;
	
	void createHighestId() {
		ResponseEntity<List<Ucesnik>> response = template
				.exchange("/ucesnik", HttpMethod.GET,
						null,
						new ParameterizedTypeReference<List<Ucesnik>>() {} );
		ArrayList<Ucesnik> list = (ArrayList<Ucesnik>) response.getBody();
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
	void testGetAllUcesnik() {
		ResponseEntity<List<Ucesnik>> response = template.exchange("/ucesnik", HttpMethod.GET, 
				null, new ParameterizedTypeReference<List<Ucesnik>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Ucesnik> ucesnici = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(ucesnici);
		
		
	}
	
	@Test
	@Order(2)
	void testGetUcesnikById() {
		int id = 1;
		ResponseEntity<Ucesnik> response = template.exchange("/ucesnik/id/" + id, HttpMethod.GET, null, Ucesnik.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertNotNull(response.getBody());
		assertEquals(id, response.getBody().getId());
	}
	
	@Test
	@Order(3)
	void testGetUcesnikByMbr() {
		String mbr = "4";
		ResponseEntity<List<Ucesnik>> response = template.exchange("/ucesnik/mbr/" + mbr, HttpMethod.GET, 
				null, new ParameterizedTypeReference<List<Ucesnik>>() {} );
		int statusCode = response.getStatusCode().value();
		List<Ucesnik> ucesnici = response.getBody();
		String mbrUcesnika =   ucesnici.get(0).getMbr();
		
		assertEquals(200, statusCode);
		assertNotNull(ucesnici.get(0));
		assertTrue(mbrUcesnika.startsWith(mbr));	
		
		}
	
	

	
	@Test
	@Order(4)
	void testCreateUcesnik() {
		Ucesnik ucesnik = new Ucesnik();
		ucesnik.setIme("POST ime");
		ucesnik.setMbr("POST mbr");
		ucesnik.setPrezime("POST prezime");
		ucesnik.setStatus("POST status");
		
		HttpEntity<Ucesnik> entity = new HttpEntity<Ucesnik>(ucesnik);
		createHighestId();
		ResponseEntity<Ucesnik> response = template
				.exchange("/ucesnik", HttpMethod.POST,
						entity, Ucesnik.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(201, statusCode);
		assertEquals("/ucesnik/id/" + highestId, 
				response.getHeaders().getLocation().getPath());
		assertEquals(ucesnik.getIme(), response.getBody().getIme());
		assertEquals(ucesnik.getMbr(), response.getBody().getMbr());
		assertEquals(ucesnik.getPrezime(), response.getBody().getPrezime());
		assertEquals(ucesnik.getStatus(), response.getBody().getStatus());
	}
	
	@Test
	@Order(5)
	void testUpdateUcesnik() {
		Ucesnik ucesnik = new Ucesnik();
		ucesnik.setIme("PUT ime");
		ucesnik.setMbr("PUT mbr");
		ucesnik.setPrezime("PUT prezime");
		ucesnik.setStatus("PUT status");

		
		HttpEntity<Ucesnik> entity = new HttpEntity<Ucesnik>(ucesnik);
		getHighestId();
		ResponseEntity<Ucesnik> response = template
				.exchange("/ucesnik/id/" + highestId, HttpMethod.PUT,
						entity, Ucesnik.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody() instanceof Ucesnik);
		assertEquals(ucesnik.getIme(), response.getBody().getIme());
		assertEquals(ucesnik.getMbr(), response.getBody().getMbr());
		assertEquals(ucesnik.getPrezime(), response.getBody().getPrezime());
		assertEquals(ucesnik.getStatus(), response.getBody().getStatus());
		
	}
	
	@Test
	@Order(6)
	void testDeleteUcesnikById() {
		getHighestId();
		ResponseEntity<String> response = template
				.exchange("/ucesnik/id/" + highestId, HttpMethod.DELETE,
						null, String.class);
	
		
		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().contains("has been deleted"));

	}
	
}
