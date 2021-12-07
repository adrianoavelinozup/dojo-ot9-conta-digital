package br.com.zup.academy.contadigital.contadigital;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_contas_digitais")
public class ContaDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long idCliente;

    @Column(nullable = false)
    @Positive
    private BigDecimal saldo;

    @Column(nullable = false, unique = true)
    private String numeroConta;

    @Deprecated
    public ContaDigital() {
    }

    public ContaDigital(Long idCliente, BigDecimal saldo, String numeroConta) {
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.numeroConta = numeroConta;
    }

    public void atualizarSaldo(ContaDigitalRequest request) {
        if (request.getTipoTransacao().equals(TipoTransacao.DEBITAR)) {
            this.saldo = new BigDecimal(this.saldo.toString()).subtract(request.getValor());
        } else {
            this.saldo = new BigDecimal(this.saldo.toString()).add(request.getValor());
        }
    }
}