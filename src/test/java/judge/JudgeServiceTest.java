package judge;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JudgeServiceTest {
    private static final String TEST_C = "#include<stdio.h>\n" +
            "int main(){\n" +
            "\tint a, b;\n" +
            "\tscanf(\"%d %d\", &a, &b);\n" +
            "\tprintf(\"%d\", a+b);\n" +
            "\treturn 0;\n" +
            "}";

    @Test
    void name() throws Exception {
        JudgeService judgeService = new JudgeService();
        assertThat(judgeService.run(TEST_C, "C", "1 2", "3")).contains("CORRECTg");
    }
}