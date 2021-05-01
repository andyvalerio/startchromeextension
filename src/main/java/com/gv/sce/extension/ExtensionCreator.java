/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gv.sce.extension;

import com.gv.sce.config.ExtensionConfiguration;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author giuse
 */
@Component
@RequiredArgsConstructor
public class ExtensionCreator {

    private final ExtensionConfiguration extensionConfiguration;

    public Path createExtension(String name, String description, boolean popup) throws IOException {
	Path folderPath = Files.createTempDirectory("extension");
	JSONObject jsonManifest = jsonManifest(name, description, popup);
	toFile(folderPath, jsonManifest);
	createPopupHtml(folderPath);
	return folderPath;
    }

    protected JSONObject jsonManifest(String name, String description, boolean popup) {
	JSONObject jsonManifest = new JSONObject();
	jsonManifest.put("name", name);
	jsonManifest.put("description", description);
	jsonManifest.put("version", extensionConfiguration.getInitialVersion());
	jsonManifest.put("manifest_version", extensionConfiguration.getManifestVersion());
	if (popup) {
	    JSONObject popupObject = new JSONObject();
	    popupObject.put("default_popup", "popup.html");
	    jsonManifest.put("action", popupObject);
	}
	return jsonManifest;
    }

    private void toFile(Path folderPath, JSONObject jsonManifest) throws IOException {
	File manifest = new File(folderPath.toString() + File.separator + "manifest.json");
	try (FileWriter fileWriter = new FileWriter(manifest)) {
	    fileWriter.write(jsonManifest.toJSONString());
	    fileWriter.flush();
	}
    }

    private void createPopupHtml(Path folderPath) throws IOException {
	File popupFile = new File(folderPath.toString() + File.separator + "popup.html");
	String html = "<html>\n"
		+ "  <head>\n"
		+ "    <title>New Chrome Extension</title>\n"
		+ "  </head>\n"
		+ "  <body>\n"
		+ "      <img src='./chrome.png' id='chromeLogo' width=\"200\" height=\"200\">\n"
		+ "      <h1>Congratulation, you just created a new Chrome extension!</h1>\n"
		+ "      <h2>What now?</h2>\n"
		+ "      <ul>\n"
		+ "      <li>Extract the extension.zip file to a folder and start developing</li>\n"
		+ "      <li>Modify the content of popup.html to edit this popup page</li>\n"
		+ "      <li>The sky is the limit!</li>\n"
		+ "      <ul>\n"
		+ "  </body>\n"
		+ "</html>";
	try (FileWriter fileWriter = new FileWriter(popupFile)) {
	    fileWriter.write(html);
	    fileWriter.flush();
	}

	// Create image file
	InputStream initialStream = getClass().getClassLoader().getResourceAsStream("chrome.png");
	File targetFile = new File(folderPath.toString() + File.separator + "chrome.png");

	java.nio.file.Files.copy(
		initialStream,
		targetFile.toPath(),
		StandardCopyOption.REPLACE_EXISTING);

	IOUtils.closeQuietly(initialStream);

    }

}
