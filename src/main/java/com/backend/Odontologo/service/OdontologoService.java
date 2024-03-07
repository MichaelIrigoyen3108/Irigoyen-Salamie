package com.backend.Odontologo.service;
import com.backend.Odontologo.dao.IDao;
import com.backend.Odontologo.dao.implement.OdontologoDaoH2;
import com.backend.Odontologo.entity.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao){
        this.odontologoIDao = odontologoIDao;
    }
    public Odontologo registrar(Odontologo odontologo){
        return odontologoIDao.registrar(odontologo);
    }
    public List<Odontologo> listarTodos(){
        return odontologoIDao.listarTodos();
    }

}
