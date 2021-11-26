package com.egg.libreria.service;

import com.egg.libreria.exception.ServiceError;
import com.egg.libreria.model.Editorial;
import com.egg.libreria.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditorialService {
    @Autowired
    private EditorialRepository editorialRepository;

    public void altaEditorial(String nombre) throws ServiceError{
        validar(nombre);

        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);

        editorialRepository.save(editorial);
    }

    public void actualizarEditorial(String id, String nombre) throws ServiceError{
        validar(nombre);

        Optional<Editorial> respuesta = editorialRepository.findById(id);
        if(respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);

            editorialRepository.save(editorial);
        } else {
            throw new ServiceError("No se encontró la editorial");
        }

    }

    public void bajaEditorial(String id) throws ServiceError{

        Optional<Editorial> respuesta = editorialRepository.findById(id);
        if(respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            editorial.setAlta(false);

            editorialRepository.save(editorial);
        } else {
            throw new ServiceError("No se encontró la editorial");
        }
    }

    public boolean borrarEditorial(String id){
        try {
            editorialRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private void validar(String nombre) throws ServiceError{
        if(nombre == null || nombre.isEmpty()){
            throw new ServiceError("El nombre no puede estar vacío");
        }
    }
}
