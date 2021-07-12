package org.crystal.intellij.psi

import com.intellij.psi.tree.IElementType

class CrOctalEscapeElement(type: IElementType, text: CharSequence) : CrEscapeElement(type, text) {
    override fun accept(visitor: CrVisitor) = visitor.visitOctalEscapeElement(this)

    val escapedChar: Char
        get() = text.substring(1).toInt(8).toChar()
}