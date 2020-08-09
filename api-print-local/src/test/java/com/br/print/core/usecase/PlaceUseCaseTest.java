package com.br.print.core.usecase;

import com.br.print.dataprovider.PlaceDataProvider;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlaceUseCaseTest {

    @InjectMocks
    private PlaceUseCase placeUseCase;

    @Mock
    private PlaceDataProvider placeDataProvider;


}
