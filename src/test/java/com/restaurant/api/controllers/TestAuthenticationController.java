package com.restaurant.api.controllers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestAuthenticationController {
	
    private WebApplicationContext wac;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();

    
    private MockMvc mockMvc;

    

    @Test
    public void testSendValidGoogleSecurityToken() throws Exception {
    	
    }
    
    
    @Test
    public void testSendInvalidGoogleSecurityToken() {
    
    }
    
    @Test
    public void testUnavailableConnectionWithAntiCorruptionAuthorization() {

    }

}
