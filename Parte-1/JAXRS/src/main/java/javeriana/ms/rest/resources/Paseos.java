package javeriana.ms.rest.resources;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import javeriana.ms.rest.control.PaseoControl;
import javeriana.ms.rest.entities.Paseo;
import javeriana.ms.rest.entities.PaseoDTO1;
import javeriana.ms.rest.entities.PaseoDTO2;

@Path("paseos")
public class Paseos {

    @GET
    @Produces(MediaType.TEXT_XML)
    public ArrayList<Paseo> getX() {
        return PaseoControl.getPaseos();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Paseo> getJ() {
        return PaseoControl.getPaseos();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getP() {
        return PaseoControl.getPaseos().toString();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@QueryParam(value = "id") String id) {
        String s = "OK";
        if (!PaseoControl.deletePaseo(Long.parseLong(id)))
            s = "NO OK";
        return s;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_XML)
    public Paseo putX(PaseoDTO1 paseo) {
        return PaseoControl.updatePaseo(paseo);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Paseo putJ(PaseoDTO1 paseo) {
        return PaseoControl.updatePaseo(paseo);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String putP(PaseoDTO1 paseo) {
        return PaseoControl.updatePaseo(paseo).toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String newP(PaseoDTO2 p) {
        String s = "OK";
        if (!PaseoControl.newPaseo(p))
            s = "NO OK";
        return s;
    }
}
