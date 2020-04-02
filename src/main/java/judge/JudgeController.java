package judge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/judge")
public class JudgeController {
    private static final Logger logger = LoggerFactory.getLogger(JudgeController.class);
    private JudgeService judgeService;

    public JudgeController(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    @PostMapping
    public ResponseEntity<ScoringResult> create(@RequestBody SubmissionInfo submissionInfo) throws Exception {
        String scoringCode = judgeService.run(submissionInfo.getSource(), submissionInfo.getLanguage(), submissionInfo.getInput(), submissionInfo.getAnswer());
        ScoringResult scoringResult = new ScoringResult(scoringCode);
        logger.info("scoringRsult: {}", scoringResult.toString());
        return ResponseEntity.ok().body(scoringResult);
    }
}
