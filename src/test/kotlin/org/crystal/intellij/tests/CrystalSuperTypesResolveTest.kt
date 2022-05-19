package org.crystal.intellij.tests

import com.intellij.psi.util.parentOfType
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import junit.framework.TestCase
import org.crystal.intellij.config.crystalSettings
import org.crystal.intellij.psi.CrTypeSource
import org.crystal.intellij.resolve.scopes.asSequence
import org.crystal.intellij.resolve.symbols.CrModuleLikeSym
import org.crystal.intellij.tests.util.findDirective
import org.crystal.intellij.tests.util.getCrystalTestFilesAsParameters
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.File

@RunWith(Parameterized::class)
class CrystalSuperTypesResolveTest(private val testFile: File) : BasePlatformTestCase() {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun testFiles() = getCrystalTestFilesAsParameters("resolve/superClass")
    }

    override fun setUp() {
        super.setUp()
        myFixture.testDataPath = File("src/testData/resolve/superClass").absolutePath
    }

    @Test
    fun testHighlighting() {
        myFixture.testDataPath = testFile.parent
        myFixture.configureByFile(testFile.name)

        val file = myFixture.file
        project.crystalSettings.update {
            mainFilePath = file.virtualFile.path
        }

        val expectedSuperClass = file.findDirective("# SUPER_CLASS:")!!
        val expectedParents = file.findDirective("# PARENTS:") ?: expectedSuperClass

        myFixture.editor.caretModel.runForEachCaret { caret ->
            val offset = caret.offset
            val typeSource = file.findElementAt(offset)!!.parentOfType<CrTypeSource>()!!
            val symbol = typeSource.resolveSymbol() as CrModuleLikeSym
            val superClass = symbol.superClass
            val parents = symbol.parents
            TestCase.assertEquals(expectedSuperClass, superClass?.fqName?.fullName ?: "")
            TestCase.assertEquals(
                expectedParents,
                parents?.asSequence()?.joinToString { it.fqName!!.fullName } ?: ""
            )
        }
    }
}