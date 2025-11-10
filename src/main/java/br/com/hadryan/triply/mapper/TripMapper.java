package br.com.hadryan.triply.mapper;

import br.com.hadryan.triply.domain.entity.Trip;
import br.com.hadryan.triply.mapper.request.TripPostRequest;
import br.com.hadryan.triply.mapper.response.TripResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TripMapper {

    Trip postToTrip(TripPostRequest tripPostRequest);

    TripResponse tripToResponse(Trip trip);

}
