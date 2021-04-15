package com.gv.sce.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author giuse
 */
@RestController
@Slf4j
public class StartExtensionController {

    @RequestMapping(path = "/start", method = RequestMethod.GET)
    public ResponseEntity<Resource> start(String param) throws IOException {
        System.out.println("Something");
        Path path = Path.of("path", "morepath");
        final byte[] allBytes = Files.readAllBytes(path);

        ByteArrayResource resource = new ByteArrayResource(allBytes);
        HttpHeaders httpHeaders = new HttpHeaders();
        
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentLength(allBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
    

}
