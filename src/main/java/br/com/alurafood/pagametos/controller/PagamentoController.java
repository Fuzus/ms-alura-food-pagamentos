package br.com.alurafood.pagametos.controller;

import br.com.alurafood.pagametos.dto.PagamentoDTO;
import br.com.alurafood.pagametos.service.PagamentoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @GetMapping
    public ResponseEntity<Page<PagamentoDTO>> obterTodos(@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.ok(service.obterTodos(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> obterPorId(@PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok(service.obterPorId(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PagamentoDTO> criar(@RequestBody PagamentoDTO dto, UriComponentsBuilder uriBuilder) {
        PagamentoDTO pagamento = service.criarPagamento(dto);
        URI uri = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.id()).toUri();

        return ResponseEntity.created(uri).body(pagamento);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PagamentoDTO> atualizar(@PathVariable("id") @NotNull Long id, @RequestBody PagamentoDTO dto) {
        return ResponseEntity.ok(service.atualizarPagamento(id, dto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable("id") @NotNull Long id) {
        service.excluirPagamento(id);
        return ResponseEntity.noContent().build();
    }
}
