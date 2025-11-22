package br.com.hadryan.triply.api.mapper;

import br.com.hadryan.triply.api.entity.Trip;
import br.com.hadryan.triply.api.mapper.request.trip.TripPostRequest;
import br.com.hadryan.triply.api.mapper.response.trip.TripResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TripMapper {

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "id", ignore = true)
    Trip postToTrip(TripPostRequest request);

    TripResponse tripToResponse(Trip trip);

}
