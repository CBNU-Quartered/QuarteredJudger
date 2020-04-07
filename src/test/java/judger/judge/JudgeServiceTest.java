package judger.judge;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JudgeServiceTest {
    @Autowired
    private JudgeService judgeService;

    private static final String TEST_C = "#include<stdio.h>\n" +
            "int main(){\n" +
            "\tint a, b;\n" +
            "\tscanf(\"%d %d\", &a, &b);\n" +
            "\tprintf(\"%d\", a+b);\n" +
            "\treturn 0;\n" +
            "}";

    @Test
    @DisplayName("C 코드 채점 테스트")
    void name() throws Exception {
        assertThat(judgeService.run(TEST_C, "C", "1 2", "3")).contains("CORRECT");
    }
}