package MHR.practice;

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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalculatorResource {

    @Inject
    CalculatorService calculatorService;

    @Inject
    UriInfo UriInfo;

    @GET
    @Path("/logs")
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/logs/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String hello2() {
        return "Hello from Quarkus REST";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response calculate(CalculationRequest request) {
        try{
            CalculationResult result = calculatorService.calculate(request.getExpression());
            return Response.ok(result).build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new CalculationResult(e.getMessage(), e))
                    .build();
        }

    }
}
