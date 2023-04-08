package com.example.definitelynotvulnerableapp.web.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.XMLInputFactory;
import java.io.IOException;

@RestController
public class UserDataRestController {

    private XmlMapper xmlMapper = new XmlMapper(new XmlFactory(XMLInputFactory.newInstance()));

    @RequestMapping(path = "/rest/user/import", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public String importUserData(@RequestBody String rawXml) throws IOException {

        JsonNode jsonNode = xmlMapper.readTree(rawXml);

        return xmlMapper.writeValueAsString(jsonNode);
    }
}
