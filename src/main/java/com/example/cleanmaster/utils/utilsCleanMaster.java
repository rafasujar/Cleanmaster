package com.example.cleanmaster.utils;

import java.util.Base64;

public class utilsCleanMaster {

    public static String  encodeUser(String texto){
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(texto.getBytes());
    }


    public static String decoderUser (String texto){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(texto);
        return new String(decodedByteArray);
    }

}
