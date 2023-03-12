package com.virginholidays.backend.test.service;

import com.virginholidays.backend.test.api.Flight;
import com.virginholidays.backend.test.repository.FlightInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

/**
 * The FlightInfoServiceImpl unit tests
 *
 * @author Geoff Perks
 */

@ExtendWith(MockitoExtension.class)
class FlightInfoServiceImplTest {

  // FIXME - applicant to complete
  @InjectMocks
  private FlightInfoServiceImpl infoService;

  @Mock
  private FlightInfoRepository flightInfoRepository;

  @Test
  public void testFindFlightByDate() {
    when(flightInfoRepository.findAll()).thenReturn(CompletableFuture.completedFuture(loadFlights()));
    Optional<List<Flight>> flights = infoService.findFlightByDate(
            LocalDate.of(2023, 03, 14)).toCompletableFuture().join();
    assertThat(flights.get().size(), equalTo(3));
  }

  private Optional<List<Flight>> loadFlights() {
    return Optional.of(Arrays.asList(
            new Flight(LocalTime.parse("09:00"), "Antigua", "ANU", "VS033", Arrays.asList(DayOfWeek.TUESDAY)),
            new Flight(LocalTime.parse("10:15"), "Havana", "HAV", "VS063", Arrays.asList(DayOfWeek.TUESDAY)),
            new Flight(LocalTime.parse("12:20"), "Cancun", "CUN", "VS093", Arrays.asList(DayOfWeek.TUESDAY)),
            new Flight(LocalTime.parse("09:00"), "St Lucia", "UVF", "VS097", Arrays.asList(DayOfWeek.MONDAY)),
            new Flight(LocalTime.parse("09:00"), "Tobago", "TAB", "VS097", Arrays.asList(DayOfWeek.MONDAY))
    ));
  }

}
