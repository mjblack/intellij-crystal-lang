package org.crystal.intellij.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.elementType
import org.crystal.intellij.lexer.CR_CLASS_VAR
import org.crystal.intellij.lexer.CR_GLOBAL_VAR
import org.crystal.intellij.lexer.CR_INSTANCE_VAR
import org.crystal.intellij.parser.CR_SIMPLE_NAME_ELEMENT
import org.crystal.intellij.stubs.api.CrNameStub

class CrSimpleNameElement : CrStubbedElementImpl<CrNameStub<*>>, CrNameElement {
    companion object {
        val EMPTY_ARRAY = arrayOf<CrSimpleNameElement>()
    }

    constructor(stub: CrNameStub<*>) : super(stub, CR_SIMPLE_NAME_ELEMENT)

    constructor(node: ASTNode) : super(node)

    override fun accept(visitor: CrVisitor) = visitor.visitSimpleNameElement(this)

    override fun getName(): String? {
        greenStub?.let { return it.actualName }

        val e = firstChild
        if (e is CrStringLiteralExpression) return e.stringValue
        val name = text
        return when (e.elementType) {
            CR_INSTANCE_VAR, CR_GLOBAL_VAR -> name.substring(1)
            CR_CLASS_VAR -> name.substring(2)
            else -> name
        }
    }

    override val sourceName: String?
        get() {
            greenStub?.let { return it.sourceName }

            val e = firstChild
            return if (e is CrStringLiteralExpression) e.text else name
        }

    val innerElementType: IElementType?
        get() = firstChild?.elementType

    val isQuestion: Boolean
        get() = name?.lastOrNull() == '?'

    val isExclamation: Boolean
        get() = name?.lastOrNull() == '?'
}