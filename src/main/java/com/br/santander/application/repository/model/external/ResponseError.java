package com.br.santander.application.repository.model.external;

import java.io.Serializable;

public record ResponseError(Integer code, String message) implements Serializable{

}
