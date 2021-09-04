package org.example.exampleapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc server;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void returnsAllTheProducts() throws Exception {

        productRepository.saveAll(List.of(
                new Product("piano", 1200),
                new Product("libro", 15),
                new Product("televisión", 300)
        ));


        server.perform(get("/products"))
                .andExpect(jsonPath("$[*]",hasSize(3)))
                .andExpect(jsonPath("$[0].name",equalTo("piano")))
                .andExpect(jsonPath("$[0].price",equalTo(1200)))
                .andExpect(jsonPath("$[1].name",equalTo("libro")))
                .andExpect(jsonPath("$[1].price",equalTo(15)))
                .andExpect(jsonPath("$[2].name",equalTo("televisión")))
                .andExpect(jsonPath("$[2].price",equalTo(300)));
    }

    @Test
    void createsNewProduct() throws Exception {
        server.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"piano\", \"price\": 2000}"))
                .andExpect(status().isOk());

        server.perform(get("/products"))
                .andExpect(jsonPath("$[*]",hasSize(1)))
                .andExpect(jsonPath("$[0].name",equalTo("piano")))
                .andExpect(jsonPath("$[0].price",equalTo(2000)));

    }
}
