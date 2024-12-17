package com.example.cleanmaster.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class utilsCleanMaster {

    public static String encodeBase64(String texto) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(texto.getBytes(StandardCharsets.UTF_8));
    }


    public static String decodeBase54(String texto) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(texto);
        return new String(decodedByteArray, StandardCharsets.UTF_8);
    }


    public static String decoderUser(String texto) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(texto);
        String respuesta = new String(decodedByteArray, StandardCharsets.UTF_8);
        System.out.println("respuesta encriptada: " + respuesta);
        respuesta = respuesta.replace("/", "'");
        respuesta = respuesta.replace("+", ":");
        respuesta = respuesta.replace("'", "\"");

        System.out.println("respuesta desencriptada:\n" + respuesta);
        return respuesta;
    }

    public static String generateToken(Boolean empleado, int id, String correo, String nombre) {

        String respuesta = "{ 'empleado' : " + empleado + ", 'correo': '" + correo + "'," +
                "'id': " + id + "," +
                "'nombre': '" + nombre + "'}";

        respuesta = respuesta.replace("'", "\"");
        respuesta = respuesta.replace(":", "+");
        respuesta = respuesta.replace("\"", "/");
        return utilsCleanMaster.encodeBase64(respuesta);
    }

}