package MHR.practice;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/calculator")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class CalculatorResource {

    @Inject
    CalculatorService calculatorService;

    @Inject
    UriInfo UriInfo;

    @GET
    @Path("/logs")
    @Produces(APPLICATION_JSON)
    public Response getErrors() {
        List<Error> errors = calculatorService.getAllErrors();
        if (errors.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(errors).build();

    }

    @GET
    @Path("/logs/{id}")
    @Produces(APPLICATION_JSON)
    public String hello2() {
        return "Hello from Quarkus REST";
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response calculate(CalculationRequest request) {
        try {
            CalculationResult result = calculatorService.calculate(request.getExpression());
            return Response.ok(result).build();
        } catch (Exception e) {
            Error error = calculatorService.create(e, request.getExpression());

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();
        }

    }
}
