/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gv.sce.extension;

import com.gv.sce.config.ExtensionConfiguration;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 *
 * @author giuse
 */
@Component
@RequiredArgsConstructor
public class ExtensionCreator {
    
    private final ExtensionConfiguration extensionConfiguration;
    
    public Path createExtension(String name, String description) throws IOException {
	Path folderPath = Files.createTempDirectory("extension");
	JSONObject jsonManifest = new JSONObject();
	jsonManifest.put("name", name);
	jsonManifest.put("description", description);
	jsonManifest.put("version", extensionConfiguration.getInitialVersion());
	jsonManifest.put("manifest_version", extensionConfiguration.getManifestVersion());
	File manifest = new File(folderPath.toString() + File.separator + "manifest.json");
	try (FileWriter fileWriter = new FileWriter(manifest)) {
	    fileWriter.write(jsonManifest.toJSONString());
	    fileWriter.flush();
	}
	return folderPath;
    }
    
}
