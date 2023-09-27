package com.example.block6pathvariableheaders;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Datos {
    String body;
    List<String> headers;
    List<String> requestParams;
}
