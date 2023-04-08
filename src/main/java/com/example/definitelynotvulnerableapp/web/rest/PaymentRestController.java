package com.example.definitelynotvulnerableapp.web.rest;

import com.example.definitelynotvulnerableapp.domain.model.PaymentData;
import com.example.definitelynotvulnerableapp.domain.repository.PaymentDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

@RestController
public class PaymentRestController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentDataRepository paymentDataRepository;

    @RequestMapping(path = "/rest/payment", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPayment(@RequestBody String content) throws IOException {

        Object obj = objectMapper.readValue(content, java.lang.Object.class);
        if (!(obj instanceof PaymentData)) {
            return "Received data is invalid: " + objectMapper.writeValueAsString(obj);
        }
        paymentDataRepository.save((PaymentData) obj);
        return objectMapper.writeValueAsString(obj);
    }
    @RequestMapping(path = "/rest/payment/import", method = RequestMethod.POST)
    public String importPayment(@RequestBody String content) throws IOException, ClassNotFoundException {

        Object obj = deserialize(Base64.getDecoder().decode(content));
        return objectMapper.writeValueAsString(obj);
    }

    public static Object deserialize(final byte[] serialized) throws IOException, ClassNotFoundException {
        final ByteArrayInputStream in = new ByteArrayInputStream(serialized);
        return deserialize(in);
    }

    public static Object deserialize(final InputStream in) throws ClassNotFoundException, IOException {
        final ObjectInputStream objIn = new ObjectInputStream(in);
        return objIn.readObject();
    }


}
