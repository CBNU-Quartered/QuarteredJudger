package judger.judge

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/judge")
class JudgeController(private val judgeService: JudgeService) {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(JudgeController::class.java)
    }

    @PostMapping
    fun grade(@RequestBody submissionInfo: SubmissionInfo) : ResponseEntity<ScoringResult> {
        val scoringCode = judgeService.run(submissionInfo.source, submissionInfo.language, submissionInfo.input, submissionInfo.answer)
        val scoringResult = ScoringResult(scoringCode)

        return ResponseEntity.ok().body(scoringResult)
    }
}