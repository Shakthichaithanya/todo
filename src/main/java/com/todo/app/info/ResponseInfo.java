package com.todo.app.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInfo {
    private HttpStatus status;
    private String message;
    private Object data;

    public ResponseInfo(HttpStatus staus,String message){
        this.status = staus;
        this.message = message;
    }
}
