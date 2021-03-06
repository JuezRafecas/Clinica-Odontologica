package com.ctd_backend_final.clinica_odontologica.integrationtests.tests;

import com.ctd_backend_final.clinica_odontologica.components.DataLoader;
import com.ctd_backend_final.clinica_odontologica.model.DTO.OdontologoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class GenericControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;


    @Test
    public void buscarPorId() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}", DataLoader.ODONTOLOGO_ID)
                    .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Optional<String> response = mapper.convertValue(result.getResponse().getContentAsString(), Optional.class);
        OdontologoDTO odontologoDTOResponse = mapper.readValue(response.get(), OdontologoDTO.class);

        assert(response.isPresent());
        assert (odontologoDTOResponse.getId().equals(DataLoader.ODONTOLOGO_ID));
    }

    @Test
    public void crear() throws Exception {
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("test");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                .content(mapper.writeValueAsString(odontologoDTO))
                .contentType("application/json")
                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Optional<OdontologoDTO> odontologoDTOResponse = mapper.convertValue(result.getResponse().getContentAsString(), Optional.class);


        assert(odontologoDTOResponse.isPresent());
    }

    @Test
    public void actualizar() throws Exception {
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setId(DataLoader.ODONTOLOGO_ID);
        odontologoDTO.setNombre("test");

        mockMvc.perform(MockMvcRequestBuilders.put("/odontologos")
                .content(mapper.writeValueAsString(odontologoDTO))
                .contentType("application/json")
                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}", DataLoader.ODONTOLOGO_ID)
                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Optional<String> response = mapper.convertValue(result.getResponse().getContentAsString(), Optional.class);
        OdontologoDTO odontologoDTOResponse = mapper.readValue(response.get(), OdontologoDTO.class);


        assert(response.isPresent());
        assert(odontologoDTOResponse.getId().equals(DataLoader.ODONTOLOGO_ID));
    }

    @Test
    public void borrar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/odontologos/{id}", DataLoader.ODONTOLOGO_ID)
                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError()); //
    }

    @Test
    public void buscarTodos() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos")
                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Optional<String> response = mapper.convertValue(result.getResponse().getContentAsString(), Optional.class);
        OdontologoDTO[] odontologoDTOResponse = mapper.readValue(response.get(), OdontologoDTO[].class);

        assert(response.isPresent());
        assert(odontologoDTOResponse.length > 0);
    }
}


