package lt.vu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Hotel.findAll", query = "select hotel from Hotel as hotel")
})
@Table(name = "HOTEL")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotEmpty
    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms = new ArrayList<>();

    @Size(max = 30)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    @Column(name = "NAME")
    private String name;

    @Size(max = 80)
    @NotBlank
    @Column(name = "ADDRESS")
    private String address;

    @Min(1)
    @Max(10)
    @NotNull
    @Column(name = "RATING")
    private Integer rating;
}
