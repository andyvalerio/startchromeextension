/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gv.sce.extension;

import com.gv.sce.config.ExtensionConfiguration;
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
	boolean service = false;
	ExtensionConfiguration extensionConfiguration = new ExtensionConfiguration();
	extensionConfiguration.setInitialVersion("1.0");
	extensionConfiguration.setManifestVersion(3);
	ExtensionCreator instance = new ExtensionCreator(extensionConfiguration);
	String expResult = "{\"name\":\"My Extension\",\"manifest_version\":3,\"description\":\"My Description\",\"version\":\"1.0\"}";
	String result = instance.jsonManifest(name, description, popup, service).toJSONString();
	assertEquals(expResult, result);
    }
    
    @Test
    public void testCreateManifest() throws Exception {
	System.out.println("createExtension");
	String name = "My Extension";
	String description = "My Description";
	boolean popup = true;
	boolean service = true;
	ExtensionConfiguration extensionConfiguration = new ExtensionConfiguration();
	extensionConfiguration.setInitialVersion("1.0");
	extensionConfiguration.setManifestVersion(3);
	ExtensionCreator instance = new ExtensionCreator(extensionConfiguration);
	String expResult = "{\"permissions\":[\"scripting\"],\"background\":{\"service_worker\":\"worker.js\"},\"name\":\"My Extension\",\"manifest_version\":3"
		+ ",\"host_permissions\":[\"http:\\/\\/*\\/*\",\"https:\\/\\/*\\/*\"],\"description\":\"My Description\",\"action\":{\"default_popup\":\""
		+ "popup.html\"},\"version\":\"1.0\"}";
	String result = instance.jsonManifest(name, description, popup, service).toJSONString();
	System.out.println(result);
	assertEquals(expResult, result);
    }
    
}
