package lt.vu.rest.contracts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDTO {
    private String housekeeperNames;

    private String hotelName;

    private Integer number;

    private Integer capacity;

    private Integer floor;

    private Integer dollarPricePerNight;

    private String cleanStatus;
}
