package judge;

import org.junit.jupiter.api.Test;

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
        judgeService.run(TEST_C, "C");
    }
}