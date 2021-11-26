package com.egg.libreria.repository;

import com.egg.libreria.model.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, String> {
    @Query("SELECT c FROM Editorial c WHERE c.nombre = :nombre")
    public Editorial buscarPorNombre(@Param("nombre") String nombre);
}

