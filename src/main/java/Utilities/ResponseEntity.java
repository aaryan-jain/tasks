package Utilities;

import lombok.Data;

@Data
public class ResponseEntity<T> {

    public T responseData;
    String responseMessage;
    Integer statusCode;

    public ResponseEntity(T o, String msg, Integer i) {

    }
}
