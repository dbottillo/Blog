package com.danielebottillo.blog

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

class HamcrestImportDetectorTest {

    @Test
    fun `should not report imports that are not hamcrest`() {
        TestLintTask.lint()
                .files(TestFiles.java("""
            package foo;
            import foo.R;
            class Example {
            }""").indented())
                .issues(IssueHamcrestImport)
                .run()
                .expectClean()
    }

    @Test
    fun `should warning about hamcrest import`() {
        TestLintTask.lint()
                .files(TestFiles.java("""
          package foo;
          import org.hamcrest.MatcherAssert.assertThat;
          class Example {
          }""").indented())
                .issues(IssueHamcrestImport)
                .run()
                .expect("""
          |src/foo/Example.java:2: Warning: Forbidden import [HamcrestImport]
          |import org.hamcrest.MatcherAssert.assertThat;
          |       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
          |0 errors, 1 warnings""".trimMargin())
    }
}