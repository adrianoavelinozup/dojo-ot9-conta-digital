package br.com.zup.academy.contadigital.contadigital;

import br.com.zup.academy.contadigital.contadigital.commons.errors.ApiResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/contasdigitas")
public class ContaDigitalController {

    @Autowired
    private ContaDigitalRepository contaDigitalRepository;

    @PostMapping("/{idCliente}/transacoes")
    @Transactional
    public ResponseEntity<?> registraTransacao(@PathVariable Long idCliente,
                                               @RequestBody @Valid ContaDigitalRequest request) {

        ContaDigital contaDigital = contaDigitalRepository.findByIdCliente(idCliente)
                .orElseThrow(() -> new ApiResponseException("Cliente não encontrado !" , HttpStatus.NOT_FOUND));

        contaDigital.atualizarSaldo(request);

        contaDigitalRepository.save(contaDigital);

        return ResponseEntity.ok(new ContaDigital(contaDigital.getIdCliente(), contaDigital.getNumeroConta(), contaDigital.getEmail()));
    }
}