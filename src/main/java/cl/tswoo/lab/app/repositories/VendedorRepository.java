package cl.tswoo.lab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.tswoo.lab.app.models.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

}
