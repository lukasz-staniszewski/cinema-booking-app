package com.thaichicken.cinemabooking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void helloWorldReturnMessage() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/welcome_world",
                String.class)).contains("Hello world!");
    }

    @Test
    public void testHello() {
        String url = "http://localhost:" + port;
        URI uri = UriComponentsBuilder.fromHttpUrl(url).path("/hello")
                .queryParam("myName", "Witold").build().toUri();
        assertThat(this.restTemplate.getForObject(uri, String.class)).contains("Hello Witold!");
    }
}
