package judge;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JudgeControllerTest {
    private static final String TEST_C = "#include<stdio.h>\n" +
            "int main(){\n" +
            "\tint a, b;\n" +
            "\tscanf(\"%d %d\", &a, &b);\n" +
            "\tprintf(\"%d\", a+b);\n" +
            "\treturn 0;\n" +
            "}";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void requestJudge() {
        SubmissionInfo submissionInfo = new SubmissionInfo(TEST_C, "c", "1 2", "3");

        webTestClient
                .post()
                .uri("/api/judge")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(submissionInfo), SubmissionInfo.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.scoringCode").isEqualTo("CORRECT ANSWER !!");
    }
}
