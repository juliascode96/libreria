package com.egg.libreria.repository;

import com.egg.libreria.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, String> {

    @Query("SELECT c FROM Autor c WHERE c.apellido = :nombre")
    public Autor buscarPorNombre(@Param("nombre") String nombre);
}
