package br.com.projetos.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.projetos.Domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
