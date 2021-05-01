/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gv.sce.extension;

import com.gv.sce.config.ExtensionConfiguration;
import java.nio.file.Path;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author giuse
 */
public class ExtensionCreatorTest {
    
    public ExtensionCreatorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createExtension method, of class ExtensionCreator.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateManifestWithoutPopup() throws Exception {
	System.out.println("createExtension");
	String name = "My Extension";
	String description = "My Description";
	boolean popup = false;
	ExtensionConfiguration extensionConfiguration = new ExtensionConfiguration();
	extensionConfiguration.setInitialVersion("1.0");
	extensionConfiguration.setManifestVersion(3);
	ExtensionCreator instance = new ExtensionCreator(extensionConfiguration);
	String expResult = "{\"name\":\"My Extension\",\"manifest_version\":3,\"description\":\"My Description\",\"version\":\"1.0\"}";
	String result = instance.jsonManifest(name, description, popup).toJSONString();
	assertEquals(expResult, result);
    }
    
    @Test
    public void testCreateManifest() throws Exception {
	System.out.println("createExtension");
	String name = "My Extension";
	String description = "My Description";
	boolean popup = true;
	ExtensionConfiguration extensionConfiguration = new ExtensionConfiguration();
	extensionConfiguration.setInitialVersion("1.0");
	extensionConfiguration.setManifestVersion(3);
	ExtensionCreator instance = new ExtensionCreator(extensionConfiguration);
	String expResult = "{\"browser_action\":{\"default_popup\":\"popup.html\"},\"name\":\"My Extension\",\"manifest_version\":3,\"description\""
		+ ":\"My Description\",\"version\":\"1.0\"}";
	String result = instance.jsonManifest(name, description, popup).toJSONString();
	System.out.println(result);
	assertEquals(expResult, result);
    }
    
}
