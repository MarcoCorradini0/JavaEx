package AppuntiLibro;

//Libreria utile "lombok" per i getter e setter generati

@Entity
@Table(name="Libro")
//@Getter/@Setter/@Data(getter, setter, ashcode, equals, tostring)

public class Libro {

    @Id
    @GenertateValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    
    @Column(name="isbn", nullable=false, unique=true, lenght=13)
    private String isbn;

    @
}

