package br.com.zup.academy.contadigital.contadigital;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
class ContaDigitalControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContaDigitalRepository contaDigitalRepository;

    @Test
    void deveAtualizarSaldoAposCreditarNaContaERetornarStatus200() throws Exception {

        // ambiente
        ContaDigital contaDigital = new ContaDigital(1L,new BigDecimal("1000.0"),"1","adriano@zup.com.br");
        contaDigitalRepository.save(contaDigital);

        ContaDigitalRequest registra = new ContaDigitalRequest(new BigDecimal("20.0"),TipoTransacao.CREDITAR);
        String request = mapper.writeValueAsString(registra);

        // Ação
        MockHttpServletRequestBuilder chamada = MockMvcRequestBuilders.post("/api/v1/contasdigitas/1/transacoes")
                        .contentType(MediaType.APPLICATION_JSON).content(request);

        // Corretude

        mockMvc.perform(chamada)
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                );
    }
}