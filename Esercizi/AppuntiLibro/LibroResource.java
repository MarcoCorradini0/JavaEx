package AppuntiLibro;

import java.util.List;
import javax.annotation.processing.Generated;
import jakarta.ws.rs.Path;

@DenyAll
@Path("/libri")
@RequiredArgsConstructor
public class LibroResource {

    private final LibroRepository libroRepository;
    public LibroResource(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public LibriResponce findAll() {
        List<Libro> libriADatabase = libroRepository.listAll();
        return new LibriResponce();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Responce findOne(@PathParam("id") Long id) {
        Libro byId=libroRepository.findById(id);
        if(byId==null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return new Responce.ok(new LibroResponce()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(LibroRequest libro) {
        return Responce.ok(new LibroResponce()).build();
}
