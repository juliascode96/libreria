package com.egg.libreria.service;

import com.egg.libreria.exception.ServiceError;
import com.egg.libreria.model.Autor;
import com.egg.libreria.model.Editorial;
import com.egg.libreria.model.Libro;
import com.egg.libreria.repository.AutorRepository;
import com.egg.libreria.repository.EditorialRepository;
import com.egg.libreria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditorialRepository editorialRepository;

    @Autowired
    private LibroRepository libroRepository;

    public void guardarLibro(String autor, String editorial, String titulo, Long isbn, Integer anio, Integer ejemplares, Integer ejemplaresPrestados) throws ServiceError{
        validarTodo(autor, editorial, titulo, isbn, anio, ejemplares, ejemplaresPrestados);

        Autor a = autorRepository.buscarPorNombre(autor);
        Editorial e = editorialRepository.buscarPorNombre(editorial);

        Libro l = new Libro();
        l.setTitulo(titulo);
        l.setAnio(anio);
        l.setIsbn(isbn);
        l.setEjemplares(ejemplares);
        l.setEjemplaresPrestados(ejemplaresPrestados);
        l.setEjemplaresRestantes(ejemplares - ejemplaresPrestados);
        l.setAlta(true);
        l.setAutor(a);
        l.setEditorial(e);

        libroRepository.save(l);
    }

    public void modificarLibro(String id, String autor, String editorial, String titulo, Long isbn, Integer anio, Integer ejemplares, Integer ejemplaresPrestados) throws ServiceError{

        validarTodo(autor, editorial, titulo, isbn, anio, ejemplares, ejemplaresPrestados);

        Autor a = autorRepository.buscarPorNombre(autor);
        Editorial e = editorialRepository.buscarPorNombre(editorial);

        Optional<Libro> respuesta = libroRepository.findById(id);
        if(respuesta.isPresent()){
            Libro l = respuesta.get();
            l.setTitulo(titulo);
            l.setAnio(anio);
            l.setIsbn(isbn);
            l.setEjemplares(ejemplares);
            l.setEjemplaresPrestados(ejemplaresPrestados);
            l.setEjemplaresRestantes(ejemplares - ejemplaresPrestados);
            l.setAutor(a);
            l.setEditorial(e);
            libroRepository.save(l);

        } else {
            throw new ServiceError("Libro no encontrado");
        }

    }

    public boolean borrarLibro(String id){
        try {
            libroRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void validarString(String s) throws ServiceError {
        if(s == null || s.isEmpty()){
            throw new ServiceError("Este campo no puede quedar vacío");
        }
    }

    public void validarInt(Long i) throws ServiceError{
        if(i == null){
            throw new ServiceError("Este campo no puede quedar vacío");
        }
    }

    public void validarTodo(String autor, String editorial, String titulo, Long isbn, Integer anio, Integer ejemplares, Integer ejemplaresPrestados) throws ServiceError{
        validarString(autor);
        validarString(editorial);
        validarString(titulo);
        validarInt(isbn);
        validarInt((long) anio);
        validarInt((long) ejemplares);
        validarInt((long) ejemplaresPrestados);
    }
}
