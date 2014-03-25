/*
 * Stratio Meta
 *
 * Copyright (c) 2014, Stratio, All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */

package com.stratio.meta.sh.help;

import java.io.IOException;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class HelpManager {

	private final String HELP_PATH = "com/stratio/meta/sh/help/MetaClientHelp.yaml";
	
	public HelpContent loadHelpContent(){
		HelpContent result = null;
		InputStream is = HelpManager.class.getClassLoader().getResourceAsStream(HELP_PATH);
        try{
        	Constructor constructor = new Constructor(HelpContent.class);
        	Yaml yaml = new Yaml(constructor);
            result = yaml.loadAs(is, HelpContent.class);
            result.loadMap();
        }finally{
        	try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return result;
	}
}