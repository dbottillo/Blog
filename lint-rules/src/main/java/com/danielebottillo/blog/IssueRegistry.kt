package com.danielebottillo.blog

import com.android.tools.lint.detector.api.Category.Companion.CORRECTNESS
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import java.util.EnumSet

class IssueRegistry : com.android.tools.lint.client.api.IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(IssueHamcrestImport)

    override val api: Int = com.android.tools.lint.detector.api.CURRENT_API
}

val IssueHamcrestImport = Issue.create("HamcrestImport",
        "Hamcrest is deprecated",
        "Use Google Truth instead",
        CORRECTNESS,
        5,
        Severity.WARNING,
        Implementation(HamcrestNamingPatternDetector::class.java,
                EnumSet.of(Scope.JAVA_FILE, Scope.TEST_SOURCES))
)