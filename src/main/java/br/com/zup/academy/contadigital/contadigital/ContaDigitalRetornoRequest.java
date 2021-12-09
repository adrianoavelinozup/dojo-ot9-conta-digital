package br.com.zup.academy.contadigital.contadigital;

public class ContaDigitalRetornoRequest {

    private Long idCliente;
    private String numeroConta;
    private String email;

    @Deprecated
    public ContaDigitalRetornoRequest() {
    }

    public ContaDigitalRetornoRequest(Long idCliente, String numeroConta, String email) {
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
}
