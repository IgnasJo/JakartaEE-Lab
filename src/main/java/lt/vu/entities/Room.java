package lt.vu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lt.vu.enums.CleanStatus;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Room.findAll", query = "select room from Room as room")
})
@Table(name = "ROOM")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    private List<Housekeeper> housekeepers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="HOTEL_ID")
    Hotel hotel;

    @Min(1)
    @Max(99)
    @NotNull
    @Column(name = "NUMBER")
    private Integer number;

    @Min(1)
    @Max(10)
    @NotNull
    @Column(name = "CAPACITY")
    private Integer capacity;

    @Min(1)
    @Max(99)
    @NotNull
    @Column(name = "FLOOR")
    private Integer floor;

    @Min(1)
    @NotNull
    @Column(name = "DOLLAR_PRICE_PER_NIGHT")
    private Integer dollarPricePerNight;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLEAN_STATUS")
    private CleanStatus cleanStatus = CleanStatus.UNDER_MAINTENANCE;
}
