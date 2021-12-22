package cl.tswoo.lab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.tswoo.lab.app.models.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

}
