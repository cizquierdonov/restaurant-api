package com.restaurant.api.domain.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseStatus  implements Serializable{

    private static final long serialVersionUID = 654730659373349706L;
    
    private int code;
    private String type;
    private String message;
    private String timestamp;
    
}
