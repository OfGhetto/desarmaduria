package cl.tswoo.lab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.tswoo.lab.app.models.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {

}
