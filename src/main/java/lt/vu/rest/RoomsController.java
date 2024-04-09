package lt.vu.rest;

import lombok.*;
import lt.vu.entities.Housekeeper;
import lt.vu.enums.CleanStatus;
import lt.vu.persistence.RoomsDAO;
import lt.vu.rest.contracts.RoomDTO;
import lt.vu.entities.Room;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/rooms")
public class RoomsController {

    @Inject
    @Setter @Getter
    private RoomsDAO roomsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Room room = roomsDAO.findOne(id);
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        RoomDTO roomDTO = RoomDTO.builder()
                .housekeeperNames(
                        room.getHousekeepers().stream()
                                .map(Housekeeper::getName)
                                .collect(Collectors.joining(", "))
                )
                .hotelName(room.getHotel().getName())
                .number(room.getNumber())
                .capacity(room.getCapacity())
                .floor(room.getFloor())
                .dollarPricePerNight(room.getDollarPricePerNight())
                .cleanStatus(room.getCleanStatus().toString())
                .build();
        return Response.ok(roomDTO).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer roomId,
            RoomDTO roomDTO) {
        try {
            Room existingRoom = roomsDAO.findOne(roomId);
            if (existingRoom == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingRoom.setNumber(roomDTO.getNumber());
            existingRoom.setCapacity(roomDTO.getCapacity());
            existingRoom.setFloor(roomDTO.getFloor());
            existingRoom.setDollarPricePerNight(roomDTO.getDollarPricePerNight());
            existingRoom.setCleanStatus(CleanStatus.valueOf(roomDTO.getCleanStatus()));
            roomsDAO.update(existingRoom);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
