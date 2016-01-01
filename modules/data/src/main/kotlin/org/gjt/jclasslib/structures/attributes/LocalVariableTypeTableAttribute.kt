/*
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the license, or (at your option) any later version.
*/
package org.gjt.jclasslib.structures.attributes

import org.gjt.jclasslib.structures.ClassFile
import java.io.DataInput

/**
 * Describes an  LocalVariableTypeTable attribute structure.

 * @author [Vitor Carreira](mailto:vitor.carreira@gmail.com)
 */
class LocalVariableTypeTableAttribute(classFile: ClassFile) : LocalVariableCommonAttribute<LocalVariableTypeTableEntry>(classFile) {

    override var localVariableEntries: Array<LocalVariableTypeTableEntry> = emptyArray()

    override fun readData(input: DataInput) {
        val localVariableTypeTableLength = input.readUnsignedShort()
        localVariableEntries = Array(localVariableTypeTableLength) {
            LocalVariableTypeTableEntry().apply {
                read(input)
            }
        }
    }

    override fun getAttributeLength(): Int = super.getAttributeLength() + localVariableEntries.size * LocalVariableCommonEntry.LENGTH

    companion object {
        /**
         * Name of the attribute as in the corresponding constant pool entry.
         */
        val ATTRIBUTE_NAME = "LocalVariableTypeTable"
    }
}