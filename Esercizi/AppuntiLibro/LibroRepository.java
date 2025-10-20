
import java.security.Policy;
import java.time.LocalDate;
import java.util.Optional;


@ApplicationScoped
public class LibroRepository implements PanacheRepository<Libro, Long> {
    public Optional<Libro> findByIsbn(String isbn) {
        return find(
            "select l from Libro l where l.isbn = :isbn",
            Parameters.with("isbn", isbn)
        ).firstResultOptional();
    }
    public Collection<Libro> findPublishedBeforeDate(LocalDate date) {
        return findByIsbn(
            "select l from Libro l where l.dataPubblicazione < :date",
            Sort.by("dataPubblicazione", Sort.Direction.Descending),
            Parameters.with("date", date)
        ).list();
    }
}