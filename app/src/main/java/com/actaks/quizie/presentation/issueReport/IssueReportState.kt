package com.actaks.quizie.presentation.issueReport

import com.actaks.quizie.domain.model.QuizQuestion

data class IssueReportState(
    val quizQuestion: QuizQuestion? = null,
    val isExpanded: Boolean = false,
    val issueType: IssueType = IssueType.OTHER,
    val otherIssueText: String = "",
    val additionalComment: String = "",
    val followUpEmail: String = ""
)

enum class IssueType(val text: String) {
    INCORRECT_ANSWER("Incorrect answer"),
    UNCLEAR_QUESTION("Question is unclear"),
    EXPLANATION_MISSING("Missing explanation"),
    OTHER("Other")
}
