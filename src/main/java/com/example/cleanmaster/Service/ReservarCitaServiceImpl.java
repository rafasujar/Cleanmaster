package com.example.cleanmaster.Service;

import com.example.cleanmaster.Repository.ReservarCitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservarCitaServiceImpl implements ReservarCitaService{
    @Autowired
    private ReservarCitaRepository reservarCitaRepository;

}
