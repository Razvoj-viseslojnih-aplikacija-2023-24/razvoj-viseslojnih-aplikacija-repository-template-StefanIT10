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

import rva.models.Rociste;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RocisteControllerIntegrationTest {
	
	@Autowired
	TestRestTemplate template;

	long largestId;

	public void createHighestId() {
		ResponseEntity<List<Rociste>> response = template.exchange("/rociste", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Rociste>>() {
				});
		ArrayList<Rociste> list = (ArrayList<Rociste>) response.getBody();
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			if (largestId <= list.get(i).getId()) {
				largestId = list.get(i).getId() + 1;
			}
		}

	}

	private void getHighestId() {
		createHighestId();
		largestId--;
	}
	
	@Test
	@Order(1)
	void testGetAllRociste() {
		ResponseEntity<List<Rociste>> response = template.exchange("/rociste", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Rociste>>() {
				});
		int statusCode = response.getStatusCode().value();
		List<Rociste> rocista = response.getBody();

		assertEquals(200, statusCode);
		assertNotNull(rocista);
	}
	
	@Test
	@Order(2)
	void testGetRocisteById() {
		int id = 1;
		ResponseEntity<Rociste> response = template.getForEntity("/rociste/id/" + id, Rociste.class);
		int statusCode = response.getStatusCode().value();
		Rociste rociste = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(rociste);
		assertEquals(id, rociste.getId());
	}
	
	@Test
	@Order(3)
	void testGetRocisteBySudnica() {
		String sudnica = "Sudnica u No";
		ResponseEntity<List<Rociste>> response = template.exchange("/rociste/sudnica/" + sudnica, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Rociste>>(){});
		int statusCode = response.getStatusCode().value();
		List<Rociste> rociste =  response.getBody();
		String nazivSudnice =   rociste.get(0).getSudnica();
		
		assertEquals(200, statusCode );
		assertNotNull(rociste.get(0));
		assertTrue(nazivSudnice.startsWith(sudnica));	
	}
	
	@Test
	@Order(4)
	void testGetRocisteByPredmet() {
		long predmetId = 1;
		ResponseEntity<List<Rociste>> response = template.exchange("/rociste/predmet/" + predmetId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Rociste>>(){});
		int statusCode = response.getStatusCode().value();
		List<Rociste> rocista =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(rocista.get(0));
		for(Rociste r: rocista) {
			assertTrue(r.getPredmet().getId() == 1);
		}
	}
	
	@Test
	@Order(5)
	void testGetRocisteByUcesnik() {
		long ucesnikId = 1;
		ResponseEntity<List<Rociste>> response = template.exchange("/rociste/ucesnik/" + ucesnikId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Rociste>>(){});
		int statusCode = response.getStatusCode().value();
		List<Rociste> rocista =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(rocista.get(0));
		for(Rociste p: rocista) {
			assertTrue(p.getUcesnik().getId() == 1);
		}
	}
	
	@Test
	@Order(6)
	void testCreateRociste() {
		Rociste rociste = new Rociste();
		rociste.setDatumRocista(new Date());
		rociste.setSudnica("POST Sudnica");
		
		HttpEntity<Rociste> entity = new HttpEntity<Rociste>(rociste);
		createHighestId();
		ResponseEntity<Rociste> response  = template.postForEntity("/rociste", entity, Rociste.class);		
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(201, response.getStatusCode().value());
		assertEquals(rociste.getDatumRocista(), response.getBody().getDatumRocista());
		assertEquals(rociste.getSudnica(), response.getBody().getSudnica());
	
	}
	
	@Test
	@Order(7)
	void testUpdateRociste() {
		Rociste rociste = new Rociste();
		rociste.setDatumRocista(new Date());
		rociste.setSudnica("PUT Sudnica");
		
		HttpEntity<Rociste> entity = new HttpEntity<Rociste>(rociste);
		getHighestId();
		ResponseEntity<Rociste> response  = template.exchange("/rociste/id/" + largestId, HttpMethod.PUT, entity, Rociste.class);
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(200, response.getStatusCode().value());
		assertEquals(rociste.getDatumRocista(), response.getBody().getDatumRocista());
		assertEquals(rociste.getSudnica(), response.getBody().getSudnica());
	
	}
	
	@Test
	@Order(8)
	void testDeleteRocisteById() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/rociste/id/" + largestId, HttpMethod.DELETE, null, String.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().contains("has been deleted"));
	}
	


}
