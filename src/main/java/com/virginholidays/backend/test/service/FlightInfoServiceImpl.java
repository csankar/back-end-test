package com.virginholidays.backend.test.service;

import com.virginholidays.backend.test.api.Flight;
import com.virginholidays.backend.test.repository.FlightInfoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

/**
 * The service implementation of FlightInfoService
 *
 * @author Geoff Perks
 */
@Service
public class FlightInfoServiceImpl implements FlightInfoService {

  private final FlightInfoRepository flightInfoRepository;

  /**
   * The constructor
   *
   * @param flightInfoRepository the flightInfoRepository
   */
  public FlightInfoServiceImpl(FlightInfoRepository flightInfoRepository) {
    this.flightInfoRepository = flightInfoRepository;
  }

  @Override
  public CompletionStage<Optional<List<Flight>>> findFlightByDate(LocalDate outboundDate) {

    // FIXME - applicant to complete
    CompletionStage<Optional<List<Flight>>> allFlights = flightInfoRepository.findAll();
    return allFlights.thenApplyAsync(flights -> {
      return Optional.of(
              flights.get()
                      .stream()
                      .filter(flight ->
                              flight
                                      .days()
                                      .contains(outboundDate.getDayOfWeek())
                      ).collect(Collectors.toList())
                      .stream()
                      .sorted(Comparator.comparing(Flight::departureTime))
                      .collect(Collectors.toList()));
    });
  }
}
