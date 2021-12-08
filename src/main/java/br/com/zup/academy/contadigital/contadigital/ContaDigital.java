package br.com.zup.academy.contadigital.contadigital;

import br.com.zup.academy.contadigital.contadigital.commons.errors.ApiResponseException;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
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

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Deprecated
    public ContaDigital() {
    }

    public ContaDigital(Long idCliente, BigDecimal saldo, String numeroConta, String email) {
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.numeroConta = numeroConta;
        this.email = email;
    }
    public ContaDigital(Long idCliente, String numeroConta, String email) {
        this.idCliente = idCliente;
        this.numeroConta = numeroConta;
        this.email = email;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public String getEmail() {
        return email;
    }

    public void atualizarSaldo(ContaDigitalRequest request) {

        if (request.getTipoTransacao().equals(TipoTransacao.DEBITAR)) {
            if (request.getValor().compareTo(this.saldo) > 0) {
                throw new ApiResponseException("saldo" , "Saldo insuficiente !" , HttpStatus.UNPROCESSABLE_ENTITY);
            }
            this.saldo = new BigDecimal(this.saldo.toString()).subtract(request.getValor());
        } else {
            this.saldo = new BigDecimal(this.saldo.toString()).add(request.getValor());
        }
    }
}