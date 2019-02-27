package com.danielebottillo.blog

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.JavaContext
import org.jetbrains.uast.UImportStatement

class HamcrestNamingPatternDetector : Detector(), Detector.UastScanner {
    override fun getApplicableUastTypes() = listOf(UImportStatement::class.java)

    override fun createUastHandler(context: JavaContext) = HamcrestInvalidImportHandler(context)
}

class HamcrestInvalidImportHandler(private val context: JavaContext) : UElementHandler() {
    override fun visitImportStatement(node: UImportStatement) {
        node.importReference?.let { importReference ->
            if (importReference.asSourceString().contains("org.hamcrest.")) {
                context.report(IssueHamcrestImport, node, context.getLocation(importReference), "Forbidden import")
            }
        }
    }
}