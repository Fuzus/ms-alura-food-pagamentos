package br.com.alurafood.pagametos.repository;

import br.com.alurafood.pagametos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
