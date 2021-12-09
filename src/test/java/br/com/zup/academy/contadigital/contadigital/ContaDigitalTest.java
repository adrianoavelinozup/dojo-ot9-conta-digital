package br.com.zup.academy.contadigital.contadigital;

import br.com.zup.academy.contadigital.contadigital.commons.errors.ApiResponseException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContaDigitalTest {

    @Test
    void deveAtualizarSaldoQuandoTransacaoForDeCredito() {

        ContaDigital contaDigital = new ContaDigital(1L, new BigDecimal("1000.0"), "1", "raphael@zup.com.br");
        ContaDigitalRequest registra = new ContaDigitalRequest(new BigDecimal("20.0"),TipoTransacao.CREDITAR);
        contaDigital.atualizarSaldo(registra);

        assertEquals(new BigDecimal("1020.0"),contaDigital.getSaldo());
    }

    @Test
    void deveAtualizarSaldoQuandoTransacaoForDeDebito() {

        ContaDigital contaDigital = new ContaDigital(1L, new BigDecimal("1000.0"), "1", "raphael@zup.com.br");
        ContaDigitalRequest registra = new ContaDigitalRequest(new BigDecimal("200.0"),TipoTransacao.DEBITAR);
        contaDigital.atualizarSaldo(registra);

        assertEquals(new BigDecimal("800.0"),contaDigital.getSaldo());
    }

    @Test
    void naoDeveAtualizarSaldoQuandoValorDaTransacaoDeDebitoForMaiorQueSaldo() {

        ContaDigital contaDigital = new ContaDigital(1L, new BigDecimal("1000.0"), "1", "raphael@zup.com.br");
        ContaDigitalRequest registra = new ContaDigitalRequest(new BigDecimal("1200.0"),TipoTransacao.DEBITAR);

        assertThrows(ApiResponseException.class, () -> {
            contaDigital.atualizarSaldo(registra);
        });
    }

    @Test
    void DeveLancarExcecaoQuandoValorDaTransacaoDeDebitoForMaiorQueSaldo() {

        ContaDigital contaDigital = new ContaDigital(1L, new BigDecimal("1000.0"), "1", "raphael@zup.com.br");
        ContaDigitalRequest registra = new ContaDigitalRequest(new BigDecimal("1200.0"),TipoTransacao.DEBITAR);

        ApiResponseException exception = assertThrows(ApiResponseException.class, () -> {
            contaDigital.atualizarSaldo(registra);
        });
        assertEquals("Saldo insuficiente !",exception.getMessage());
    }
}