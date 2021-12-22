package cl.tswoo.lab.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.tswoo.lab.app.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	

}
