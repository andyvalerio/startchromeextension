/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gv.sce.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author giuse
 */
@Configuration
@RequiredArgsConstructor
@Data
@ConfigurationProperties(prefix = "extension")
public class ExtensionConfiguration {
    private int manifestVersion;
    private String initialVersion;
}
