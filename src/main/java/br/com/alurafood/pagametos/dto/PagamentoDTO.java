package br.com.alurafood.pagametos.dto;

import br.com.alurafood.pagametos.model.Pagamento;
import br.com.alurafood.pagametos.model.Status;

import java.math.BigDecimal;

public record PagamentoDTO(
        Long id,
        BigDecimal valor,
        String nome,
        String numero,
        String expiracao,
        String codigo,
        Status status,
        Long pedidoId,
        Long formaPagamentoId) {
    public PagamentoDTO(Pagamento pagamento) {
        this(
                pagamento.getId(),
                pagamento.getValor(),
                pagamento.getNome(),
                pagamento.getNumero(),
                pagamento.getExpiracao(),
                pagamento.getCodigo(),
                pagamento.getStatus(),
                pagamento.getPedidoId(),
                pagamento.getFormaPagamentoId()
        );
    }
}
