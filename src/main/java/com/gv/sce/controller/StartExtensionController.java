package com.gv.sce.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zeroturnaround.zip.ZipUtil;

/**
 *
 * @author giuse
 */
@RestController
@Slf4j
public class StartExtensionController {

    @RequestMapping(path = "/start", method = RequestMethod.GET)
    public ResponseEntity<Resource> start(String name, String description) throws IOException {
        // TODO create JSON files based on parameters
        JSONObject jsonManifest = new JSONObject();
	jsonManifest.put("name", name);
	jsonManifest.put("description", description);
	jsonManifest.put("version", "1.0");
	jsonManifest.put("manifest_version", 3);
	Path folderPath = Files.createTempDirectory("extension");
	File manifest = new File(folderPath.toString() + File.separator + "manifest.json");
	try (FileWriter fileWriter = new FileWriter(manifest)) {
	    fileWriter.write(jsonManifest.toJSONString());
	    fileWriter.flush();
	}
	// TODO zip into a single archive
	File zipFile = new File(folderPath.toString() + File.separator + "extension.zip");
	ZipUtil.pack(folderPath.toFile(), zipFile);
	
        final byte[] allBytes = Files.readAllBytes(zipFile.toPath());
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
