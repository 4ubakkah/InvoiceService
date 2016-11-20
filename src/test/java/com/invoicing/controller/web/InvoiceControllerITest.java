package com.invoicing.controller.web;

import com.invoicing.BootStrapper;
import com.invoicing.model.dto.GetByAddressRequestDto;
import com.invoicing.model.dto.GetMonthlyRequestDto;
import com.invoicing.model.dto.InvoiceFixture;
import com.invoicing.model.dto.RequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootStrapper.class)
@WebAppConfiguration
@Transactional
public class InvoiceControllerITest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private HttpMessageConverter jsonToHttpMessageConverter;

    private final MediaType jsonContentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        jsonToHttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
    }

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldSucceedOnGetAll() throws Exception {
        RequestDto requestDto = new RequestDto(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/invoicing/getAll")
                .content(this.convertToJson(requestDto))
                .contentType(jsonContentType))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void shouldFail_onGetAll_whenNonValidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/invoicing/getAll")
                .contentType(jsonContentType))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void shouldSucceedOnGetByAddress() throws Exception {
        GetByAddressRequestDto requestDto = new GetByAddressRequestDto(1L, 1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/invoicing/getAll")
                .content(this.convertToJson(requestDto))
                .contentType(jsonContentType))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void shouldFail_onGetByAddress_whenNonValidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/invoicing/getAll")
                .contentType(jsonContentType))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldSucceedOnGetMonthlyHousing() throws Exception {
        GetMonthlyRequestDto requestDto = new GetMonthlyRequestDto(1L, 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/invoicing/getMonthlyHousing")
                .content(this.convertToJson(requestDto))
                .contentType(jsonContentType))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void shouldFail_onGetMonthlyHousing_whenNonValidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/invoicing/getMonthlyHousing")
                .contentType(jsonContentType))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldSucceedOnGetMonthlyShopping() throws Exception {
        GetMonthlyRequestDto requestDto = new GetMonthlyRequestDto(1L, 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/invoicing/getMonthlyShopping")
                .content(this.convertToJson(requestDto))
                .contentType(jsonContentType))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void shouldFail_onGetMonthlyShopping_whenNonValidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/invoicing/getMonthlyShopping")
                .contentType(jsonContentType))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void shouldSucceedOnCreate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/invoicing/create")
                .content(this.convertToJson(InvoiceFixture.anInvoice()))
                .contentType(jsonContentType))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void shouldFail_onCreate_whenNonValidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/invoicing/create")
                .contentType(jsonContentType))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void shouldSucceedOnGenerate() throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setCustomerId(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/invoicing/generateMonthlyInvoices")
                .content(this.convertToJson(requestDto))
                .contentType(jsonContentType))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void shouldFail_onGenerate_whenNonValidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/invoicing/generateMonthlyInvoices")
                .contentType(jsonContentType))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private String convertToJson(Object input) throws IOException {
        MockHttpOutputMessage httpMessage = new MockHttpOutputMessage();
        jsonToHttpMessageConverter.write(input, MediaType.APPLICATION_JSON, httpMessage);
        return httpMessage.getBodyAsString();
    }
}