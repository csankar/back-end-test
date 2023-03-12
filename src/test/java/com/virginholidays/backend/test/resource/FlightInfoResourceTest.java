package com.virginholidays.backend.test.resource;

import com.virginholidays.backend.test.api.Flight;
import com.virginholidays.backend.test.service.FlightInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The FlightInfoResource unit tests
 *
 * @author Geoff Perks
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FlightInfoResource.class)
class FlightInfoResourceTest {

  // FIXME - applicant to complete.
  @MockBean
  FlightInfoService infoService;

  @Autowired
  MockMvc mvc;

  @Test
  public void test400Response() throws Exception {
    this.mvc.perform(get("/2023-03/results"))
            .andExpect(status().is4xxClientError());
  }

  @Test
  public void test200Response() throws Exception {
    when(infoService.findFlightByDate(any())).thenReturn(
            CompletableFuture.completedFuture(getFlights()));
    this.mvc.perform(get("/2023-03-12/results"))
            .andExpect(status().is2xxSuccessful());
  }

  private Optional<List<Flight>> getFlights() {
    return Optional.of(Arrays.asList(
            new Flight(LocalTime.parse("09:00"), "Antigua", "ANU", "VS033", Arrays.asList(DayOfWeek.TUESDAY)),
            new Flight(LocalTime.parse("10:15"), "Havana", "HAV", "VS063", Arrays.asList(DayOfWeek.TUESDAY)),
            new Flight(LocalTime.parse("12:20"), "Cancun", "CUN", "VS093", Arrays.asList(DayOfWeek.TUESDAY)),
            new Flight(LocalTime.parse("09:00"), "St Lucia", "UVF", "VS097", Arrays.asList(DayOfWeek.MONDAY)),
            new Flight(LocalTime.parse("09:00"), "Tobago", "TAB", "VS097", Arrays.asList(DayOfWeek.MONDAY))
    ));
  }
}

