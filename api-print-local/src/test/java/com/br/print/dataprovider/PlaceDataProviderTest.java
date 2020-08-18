package com.br.print.dataprovider;

import com.br.print.builder.data.PlaceBuildDataTest;
import com.br.print.dataprovider.feign.PlaceFeign;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PlaceDataProviderTest {

    @InjectMocks
    private PlaceDataProvider placeDataProvider;

    @Mock
    private PlaceFeign placeFeign;

    @Autowired
    private PlaceBuildDataTest placeBuildDataTest;

    @Test
    public void listStates__Success__Test() {
        Mockito.when(this.placeFeign.listStates())
                .thenReturn(this.placeBuildDataTest.makeMockListObjectStates());
        List<Object> listStates = this.placeDataProvider.listStates();
        assertNotNull(listStates);
        assertTrue(listStates.size() > 0);
    }

    @Test
    public void listCities__By__State__Success__Test(){
        Mockito.when(this.placeFeign.listCitiesByState("MG"))
                .thenReturn(this.placeBuildDataTest.makeMockListCities());
        List<Object> listCities = this.placeDataProvider.listCitiesByState("MG");
        assertNotNull(listCities);
        assertTrue(listCities.size() > 0);
    }

    @Test
    public void citiesPDF__Report__Success__Test() throws IOException {
        byte[] reportPDF =  this.placeDataProvider.citiesPdfReport("MG");
        assertNotNull(reportPDF);
    }

}
