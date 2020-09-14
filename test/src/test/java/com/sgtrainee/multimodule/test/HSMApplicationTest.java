package com.sgtrainee.multimodule.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgtrainee.multimodule.dao.doctor.DoctorRepository;
import com.sgtrainee.multimodule.launch.HMSApplication;
import com.sgtrainee.multimodule.model.doctor.Doctor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(properties = "application-test.yml", classes = HMSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc()
public class HSMApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext context;

    @LocalServerPort
    int port;

    @Autowired
    private DoctorRepository repository;


    @Before
    public void setUp() {
        this.repository.saveAll(Stream.of(
                new Doctor(101L,"John","Cardiac"),
                new Doctor(102L,"Peter","Eye")
        ).collect(Collectors.toList()));
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void CreateDoctorShouldBeReturnStatusCode200Ok() throws Exception {

        Doctor doctor = Doctor.builder()
                .id(1L)
                .name("Kelvy")
                .specialist("Eye")
                .build();

        String valueAsString = mapper.writeValueAsString(doctor);
        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", Charset.forName("UTF-8"));

        MockHttpServletRequestBuilder post = post("/v1/doctor");
        post.content(valueAsString);
        post.accept(MEDIA_TYPE_JSON_UTF8);
        post.contentType(MEDIA_TYPE_JSON_UTF8);

        MvcResult result = this.mockMvc.perform(post)
                .andExpect(status().isOk()).andReturn();

        String resultContentAsString = result.getResponse().getContentAsString();
        Doctor doctorStr = mapper.readValue(resultContentAsString, Doctor.class);

        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals("Kelvy",doctor.getName() );
        assertTrue(doctorStr.getId() > 0);
    }


    @Test
    public void listAllDoctorsShouldBeReturnStatusCode200OK() throws  Exception{

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", Charset.forName("UTF-8"));
        MockHttpServletRequestBuilder get = get("/v1/doctor/list");
        get.accept(MEDIA_TYPE_JSON_UTF8);
        get.contentType(MEDIA_TYPE_JSON_UTF8);

        MvcResult result = this.mockMvc.perform(get)
                .andExpect(status().isOk()).andReturn();

        String resultContentAsString = result.getResponse().getContentAsString();

        Doctor[] doctors = mapper.readValue(resultContentAsString, Doctor[].class);
        System.out.println(doctors[0]);
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals("John",doctors[0].getName());
   }


    @Test
    public void getDoctorsShouldBeReturnStatusCode200OK() throws  Exception{

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", Charset.forName("UTF-8"));
        Long id = 101L;
        MockHttpServletRequestBuilder get = get("/v1/doctor").param("id", "101");
        get.accept(MEDIA_TYPE_JSON_UTF8);
        get.contentType(MEDIA_TYPE_JSON_UTF8);


        MvcResult result = this.mockMvc.perform(get)
                .andExpect(status().isOk()).andReturn();

        String resultContentAsString = result.getResponse().getContentAsString();

        Doctor doctor = mapper.readValue(resultContentAsString, Doctor.class);
        System.out.println(doctor);
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        assertEquals("John",doctor.getName());
    }

}
