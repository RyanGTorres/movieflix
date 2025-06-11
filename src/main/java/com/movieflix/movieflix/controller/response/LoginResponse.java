package com.movieflix.movieflix.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse (@Schema(type = "string", description = "Nome do Token") String token) {


}
