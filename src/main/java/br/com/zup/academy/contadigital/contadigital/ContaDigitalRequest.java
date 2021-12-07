package br.com.zup.academy.contadigital.contadigital;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ContaDigitalRequest {

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    private TipoTransacao tipoTransacao;

    public ContaDigitalRequest(BigDecimal valor, TipoTransacao tipoTransacao) {
        this.valor = valor;
        this.tipoTransacao = tipoTransacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }
}
