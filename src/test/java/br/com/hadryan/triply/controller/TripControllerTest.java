package br.com.hadryan.triply.controller;

import br.com.hadryan.triply.config.SetupTestContainers;
import br.com.hadryan.triply.domain.entity.Trip;
import br.com.hadryan.triply.mapper.request.TripPostRequest;
import br.com.hadryan.triply.mapper.response.TripResponse;
import br.com.hadryan.triply.repository.TripRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TripControllerTest extends SetupTestContainers {

    public static final String BASE_URL = "/api/v1/trips";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TripRepository tripRepository;

    @Test
    @DisplayName("find by id return trip successfully")
    void testFindByIdShouldReturnTrip()
    {
        var trip = new Trip();
        trip.setCity("Ouro Preto");
        trip.setCountry("Brasil");
        trip.setArrival(LocalDate.of(2025, 12, 28));
        trip.setDeparture(LocalDate.of(2026, 1, 3));

        var savedTrip = tripRepository.save(trip);

        var response = restTemplate.getForEntity(BASE_URL + "/" + savedTrip.getId(), TripResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedTrip.getId(), response.getBody().id());
        assertEquals("Ouro Preto", response.getBody().city());
        assertEquals("Brasil", response.getBody().country());
    }

    @Test
    @DisplayName("find by id return NotFoundException")
    void testFindByIdShouldReturnNotFound()
    {
        var response = restTemplate
                .getForEntity(BASE_URL + "/019a6c04-f429-7510-bb21-fe7e54f26824", String.class);

        assertNotNull(response.getBody());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());

        var mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        try {
            var jsonNode = mapper.readTree(response.getBody());

            assertEquals("/errors/not-found", jsonNode.get("type").asText());
            assertEquals("Resource Not Found", jsonNode.get("title").asText());
            assertEquals(404, jsonNode.get("status").asInt());
            assertEquals("Trip not found", jsonNode.get("detail").asText());

        } catch (Exception e) {
            fail("Falha ao processar JSON de resposta: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("create trip successfully")
    void testCreateTripShouldReturnCreatedTrip() {
        var request = new TripPostRequest(
                "Ouro Preto",
                "Brasil",
                LocalDate.of(2025, 12, 28),
                LocalDate.of(2026, 1, 3)
        );

        var entity = new HttpEntity<>(request);

        var response = restTemplate.postForEntity(BASE_URL, entity, TripResponse.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().id());
        assertEquals("Ouro Preto", response.getBody().city());
        assertEquals("Brasil", response.getBody().country());
        assertEquals( LocalDate.of(2025, 12, 28), response.getBody().arrival());
        assertEquals(LocalDate.of(2026, 1, 3), response.getBody().departure());
    }

    @Test
    @DisplayName("create trip with invalid data")
    void testCreateTripShouldThrowError() {
        var request = new TripPostRequest(
                "Ouro Preto",
                "", // mandatory field blank
                LocalDate.of(2025, 12, 28),
                LocalDate.of(2026, 1, 3)
        );

        var entity = new HttpEntity<>(request);

        var response = restTemplate.postForEntity(BASE_URL, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());

        var mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        try {
            var jsonNode = mapper.readTree(response.getBody());

            assertEquals("/errors/validation", jsonNode.get("type").asText());
            assertEquals("Validation Error", jsonNode.get("title").asText());
            assertEquals(400, jsonNode.get("status").asInt());
            assertTrue(jsonNode.get("errors").has("country"));
            assertEquals("country is mandatory", jsonNode.get("errors").get("country").asText());

        } catch (Exception e) {
            fail("Falha ao processar JSON de resposta: " + e.getMessage());
        }
    }

}