package com.eventstoday.api.controller;


import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;

@WebMvcTest(controllers = TicketController.class)
@ActiveProfiles("test")
public class TicketControllerTest {



}
