package lt.vu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Housekeeper.findAll", query = "select housekeeper from Housekeeper as housekeeper")
})
@Table(name = "HOUSEKEEPER")
public class Housekeeper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "housekeepers")
    private List<Room> rooms = new ArrayList<>();

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Min(1)
    @Max(70)
    @NotNull
    @Column(name = "WEEKLY_WORKING_HOURS")
    private Integer weeklyWorkingHours;
}
