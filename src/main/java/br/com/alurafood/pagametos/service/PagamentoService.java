package br.com.alurafood.pagametos.service;

import br.com.alurafood.pagametos.dto.PagamentoDTO;
import br.com.alurafood.pagametos.model.Pagamento;
import br.com.alurafood.pagametos.model.Status;
import br.com.alurafood.pagametos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    public Page<PagamentoDTO> obterTodos(Pageable paginacao) {
        return repository.findAll(paginacao).map(PagamentoDTO::new);
    }

    public PagamentoDTO obterPorId(Long id) {
        Pagamento pagamento = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return new PagamentoDTO(pagamento);
    }

    public PagamentoDTO criarPagamento(PagamentoDTO dto) {
        Pagamento pagamento = new Pagamento(
                dto.id(),
                dto.valor(),
                dto.nome(),
                dto.numero(),
                dto.expiracao(),
                dto.codigo(),
                dto.status(),
                dto.pedidoId(),
                dto.formaPagamentoId()
        );
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return new PagamentoDTO(pagamento);
    }

    public PagamentoDTO atualizarPagamento(Long id, PagamentoDTO dto){
        Pagamento pagamento = new Pagamento(
                dto.id(),
                dto.valor(),
                dto.nome(),
                dto.numero(),
                dto.expiracao(),
                dto.codigo(),
                dto.status(),
                dto.pedidoId(),
                dto.formaPagamentoId()
        );
        pagamento.setId(id);
        repository.save(pagamento);

        return new PagamentoDTO(pagamento);
    }

    public void excluirPagamento(Long id){
        repository.deleteById(id);
    }
}
