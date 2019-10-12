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

import com.squareup.javapoet.JavaFile;
import graphql.language.*;

import java.io.File;
import java.io.IOException;

abstract public class AbstractGenerator {

    protected static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private String generationFolder;
    protected String packageName;


    public AbstractGenerator(String generationFolder, String packageName) {
        this.generationFolder = generationFolder;
        this.packageName = packageName;
    }


    public abstract void generate();


    protected String comment(Description description) {
        return (description != null ? description.getContent() : "") + LINE_SEPARATOR;
    }


    protected void writeModel(JavaFile javaFile) {
        File file = new File(generationFolder);
        try {
            javaFile.writeTo(file);
        } catch (IOException e) {
            String message = "Folder not available: " + file.getPath();
            throw new IllegalArgumentException(message, e);
        }
    }


    protected String extractTypeName(Type type) {
        String typeName = null;
        if (type instanceof TypeName) {
            typeName = ((TypeName) type).getName();
        } else if (type instanceof NonNullType) {
            Type aType = ((NonNullType) type).getType();
            typeName = ((TypeName) aType).getName();
        } else if (type instanceof ListType) {
            Type aType = ((ListType) type).getType();
            typeName = ((TypeName) aType).getName();
        }
        return typeName;
    }


}
