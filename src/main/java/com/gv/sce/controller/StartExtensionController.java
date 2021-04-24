package com.gv.sce.controller;

import com.gv.sce.extension.ExtensionCreator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zeroturnaround.zip.ZipUtil;

/**
 *
 * @author giuse
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class StartExtensionController {
    
    private final ExtensionCreator extensionCreator;

    @CrossOrigin
    @RequestMapping(path = "/start", method = RequestMethod.POST)
    public ResponseEntity<Resource> start(
	    @RequestParam(defaultValue = "My new Chrome extension") String name, 
	    @RequestParam(defaultValue = "Think carefully about a good description") String description) throws IOException {
	Path folderPath = extensionCreator.createExtension(name, description);
	
	File tempZipFile = Files.createTempFile("extension", "zip").toFile();
	ZipUtil.pack(folderPath.toFile(), tempZipFile);
	Path destinationPath = Files.move(tempZipFile.toPath(), Paths.get(folderPath.toString() + File.separator + "extension.zip"));
	
        final byte[] allBytes = Files.readAllBytes(destinationPath);
        ByteArrayResource resource = new ByteArrayResource(allBytes);
        HttpHeaders httpHeaders = new HttpHeaders();
	httpHeaders.add("Content-Disposition", "attachment; filename=\"extension.zip\"");
        
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentLength(allBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
