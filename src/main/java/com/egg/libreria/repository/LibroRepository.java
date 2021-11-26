package com.egg.libreria.repository;

import com.egg.libreria.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, String> {
    @Query("SELECT c FROM Libro c WHERE c.titulo = :titulo")
    public Libro buscarPorTitulo(@Param("titulo")String titulo);

    @Query("SELECT c FROM Libro c WHERE c.autor.nombre = :autor ORDER BY c.titulo DESC")
    public List<Libro> buscarPorAutor(@Param("autor")String autor);

    @Query("SELECT c FROM Libro c WHERE c.editorial.nombre = :editorial ORDER BY c.titulo DESC")
    public List<Libro> buscarPorEditorial(@Param("editorial")String editorial);

}
