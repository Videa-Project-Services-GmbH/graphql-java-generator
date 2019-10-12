/*
 * Copyright (c) 2019.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 *  BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 *  CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 *  ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package services.videa.graphql.java;

import graphql.language.*;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GqlSchemaParser {
    private static Logger logger = LoggerFactory.getLogger(GqlSchemaParser.class);

    private TypeDefinitionRegistry typeRegistry;

    private Map<String, ObjectTypeDefinition> objectTypes;
    private Map<String, InputObjectTypeDefinition> inputTypes;
    private Map<String, EnumTypeDefinition> enums;
    private Map<String, InterfaceTypeDefinition> interfaces;

    public GqlSchemaParser(String schemaFileClassPath) {
        logger.trace("schemaFileClassPath={}", schemaFileClassPath);

        SchemaParser schemaParser = new SchemaParser();
        URL url = getClass().getResource(schemaFileClassPath);
        String path = url.getFile();
        File file = new File(path);

        typeRegistry = schemaParser.parse(file);

        logger.trace("typeRegistry={}", typeRegistry);
    }

    /**
     * Search for a type definition with given name and return if existent.
     * Return null otherwise.
     */
    public TypeDefinition findByName(String name) {
        if(this.scalars().containsKey(name)) {
            return this.scalars().get(name);
        }
        if(this.objectTypes().containsKey(name)) {
            return this.objectTypes().get(name);
        }
        if(this.inputTypes().containsKey(name)) {
            return this.inputTypes().get(name);
        }
        if(this.enums().containsKey(name)) {
            return this.enums().get(name);
        }
        if(this.interfaces().containsKey(name)) {
            return this.interfaces().get(name);
        }
        return null;
    }

    public Map<String, ScalarTypeDefinition> scalars() {
        return typeRegistry.scalars();
    }

    /**
     * Return both object and input types.
     */
    public Map<String, TypeDefinition> types() {
        return typeRegistry.types();
    }

    public Map<String, ObjectTypeDefinition> objectTypes() {
        if (this.objectTypes == null) {
            this.objectTypes = new HashMap<>();

            this.typeRegistry.types().entrySet().forEach(entry -> {
                if (entry.getValue() instanceof ObjectTypeDefinition) {
                    this.objectTypes.put(entry.getKey(), (ObjectTypeDefinition) entry.getValue());
                }
            });
        }
        return this.objectTypes;
    }

    public Map<String, InputObjectTypeDefinition> inputTypes() {
        if (this.inputTypes == null) {
            this.inputTypes = new HashMap<>();

            this.typeRegistry.types().entrySet().forEach(entry -> {
                if (entry.getValue() instanceof InputObjectTypeDefinition) {
                    this.inputTypes.put(entry.getKey(), (InputObjectTypeDefinition) entry.getValue());
                }
            });
        }
        return this.inputTypes;
    }

    public Map<String, EnumTypeDefinition> enums() {
        if (this.enums == null) {
            this.enums = new HashMap<>();

            this.typeRegistry.types().entrySet().forEach(entry -> {
                if (entry.getValue() instanceof EnumTypeDefinition) {
                    this.enums.put(entry.getKey(), (EnumTypeDefinition) entry.getValue());
                }
            });
        }
        return this.enums;
    }

    public Map<String, InterfaceTypeDefinition> interfaces() {
        if (this.interfaces == null) {
            this.interfaces = new HashMap<>();

            this.typeRegistry.types().entrySet().forEach(entry -> {
                if (entry.getValue() instanceof InterfaceTypeDefinition) {
                    this.interfaces.put(entry.getKey(), (InterfaceTypeDefinition) entry.getValue());
                }
            });
        }
        return this.interfaces;
    }

}
