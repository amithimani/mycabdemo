package com.newcab.backend_application.controller;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerTest {

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private MockMvc mockMvc;

    @Test
    public void returnsSuccessfulResponseForValidCarIdForGetEndPoint() throws Exception {
        final String url = "http://localhost:" + port + "/v1/cars/{carId}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("carId", "5");
        ResponseEntity<String> response = restTemplate.getForEntity(UriComponentsBuilder.fromUriString(url).build(params), String.class);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void returnsErrorResponseForInValidCarIdForGetEndPoint() throws Exception {
        final String url = "http://localhost:" + port + "/v1/cars/{carId}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("carId", "22");
        ResponseEntity<String> response = restTemplate.getForEntity(UriComponentsBuilder.fromUriString(url).build(params), String.class);
        assertEquals(404, response.getStatusCodeValue());
        assertThat(response.getBody(), StringContains.containsString("Could not find entity with id."));
    }

    @Test
    public void returnsSuccessfulResponseForValidCarIdForDeleteEndPoint() throws Exception {
        final String url = "http://localhost:" + port + "/v1/cars/{carId}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("carId", "1");
        restTemplate.delete(url, params);
        ResponseEntity<String> response = restTemplate.getForEntity(UriComponentsBuilder.fromUriString(url).build(params), String.class);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void returnsSuccessfulResponseForValidCarIdForUpdateEndPoint() throws Exception {
        final String url = "http://localhost:" + port + "/v1/cars/{carId}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("carId", "1");
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                // Add query parameter
                .queryParam("licencePlate", "789")
                .queryParam("seatCount", "5")
                .queryParam("rating", "3")
                .queryParam("convertible", "true")
                .queryParam("engineType", "GAS");

        restTemplate.put(builder.buildAndExpand(params).toUri(), params);
        ResponseEntity<String> response = restTemplate.getForEntity(UriComponentsBuilder.fromUriString(url).build(params), String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertThat(response.getBody(), StringContains.containsString("rating\":3"));
        assertThat(response.getBody(), StringContains.containsString("seatCount\":5"));
        assertThat(response.getBody(), StringContains.containsString("licencePlate\":\"789"));

    }

}
