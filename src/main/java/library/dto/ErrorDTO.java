package library.dto;

import java.util.Date;

public class ErrorDTO {
    String message;
    Date date;

    public ErrorDTO(String message, Date date) {
    }

    public ErrorDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
