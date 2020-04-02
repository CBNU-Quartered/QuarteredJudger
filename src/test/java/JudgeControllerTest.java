import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JudgeControllerTest {
    private static final String TEST_C = "#include<stdio.h>\n" +
            "Int main(){\n" +
            "\tint a, b;\n" +
            "\tscanf(“%d %d”, &a, &b);\n" +
            "\tprintf(“%d”, a+b);\n" +
            "\treturn 0;\n" +
            "}";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void requestJudge() {
        webTestClient
                .post()
                .uri("/judge")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters
                        .fromFormData("src", TEST_C)
                        .with("language", "C"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("result").isEqualTo("Correct-Answer");
    }
}
