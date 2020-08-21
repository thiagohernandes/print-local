package com.br.print.core.entrypoint;

import com.br.print.builder.data.PlaceBuildDataTest;
import com.br.print.core.usecase.PlaceUseCase;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PlaceEntrypointTest {

    @Mock
    private PlaceUseCase stateUseCase;

    @InjectMocks
    private PlaceEntrypoint placeEntrypoint;

    private MockMvc mockMvc;

    private String URL_API = "/v1/places";

    private Gson gson;

    private final String uf = "SP";

    @Before
    public void init() throws Exception {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(this.placeEntrypoint).build();
    }

    @Test
    public void listStates__Success__Test() throws Exception {
        Mockito.when(this.stateUseCase.listStates())
                .thenReturn(PlaceBuildDataTest.makeMockListObjectStates());
        this.placeEntrypoint.listStates();

        MvcResult result = mockMvc.perform(get(URL_API.concat("/states"))
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(PlaceBuildDataTest.makeMockListObjectStates()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void listCitiesByStates__Success__Test() throws Exception {
        Mockito.when(this.stateUseCase.listCitiesByState(uf))
                .thenReturn(PlaceBuildDataTest.makeMockListObjectStates());
        this.placeEntrypoint.listCitiesByState(uf);

        MvcResult result = mockMvc.perform(get(URL_API.concat("/states/SP/cities"))
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(PlaceBuildDataTest.makeMockListCities()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void listCitiesByStatePdfReport__Success__Test() throws Exception {
        final byte[] byteArray = "Test data".getBytes();

        Mockito.when(this.stateUseCase.citiesPdfReport(uf))
                .thenReturn(byteArray);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL_API.concat("/states/SP/cities/pdf"))
                .contentType(MediaType.APPLICATION_OCTET_STREAM))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        assertNotNull(result);
        assertTrue(result.getResponse().getContentLength() > 0);
    }
}
