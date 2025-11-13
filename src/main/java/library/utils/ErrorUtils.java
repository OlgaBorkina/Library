package library.utils;

import jakarta.ws.rs.core.Response;
import library.dto.ErrorDTO;

import java.util.Date;

public class ErrorUtils {

    public static Response buildError(String message, Response.Status status) {
        return Response.status(status)
                .entity(new ErrorDTO(message, new Date()))
                .build();
    }
}
