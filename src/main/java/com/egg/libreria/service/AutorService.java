package com.egg.libreria.service;

import com.egg.libreria.exception.ServiceError;
import com.egg.libreria.model.Autor;
import com.egg.libreria.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public void altaAutor(String nombre, String apellido) throws ServiceError {
        validar(nombre);
        validar(apellido);

        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setApellido(apellido);
        autor.setAlta(true);

        autorRepository.save(autor);
    }

    public void actualizarAutor(String id, String nombre, String apellido) throws ServiceError{
        validar(nombre);
        validar(apellido);

        Optional<Autor> respuesta = autorRepository.findById(id);
        if(respuesta.isPresent()){
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autor.setApellido(apellido);

            autorRepository.save(autor);
        } else {
            throw new ServiceError("No se encontró la editorial");
        }

    }

    public void bajaAutor(String id) throws ServiceError{

        Optional<Autor> respuesta = autorRepository.findById(id);
        if(respuesta.isPresent()){
            Autor autor = respuesta.get();
            autor.setAlta(false);

            autorRepository.save(autor);
        } else {
            throw new ServiceError("No se encontró la editorial");
        }
    }

    public boolean borrarAutor(String id){
        try {
            autorRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private void validar(String nombre) throws ServiceError{
        if(nombre == null || nombre.isEmpty()){
            throw new ServiceError("Este campo no puede estar vacío");
        }
    }
}
