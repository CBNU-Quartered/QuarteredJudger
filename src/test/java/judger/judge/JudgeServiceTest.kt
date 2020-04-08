package judger.judge

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class JudgeServiceTest(@Autowired private val judgeService: JudgeService) {
    @Test
    @DisplayName("C 코드 채점 테스트")
    @Throws(Exception::class)
    fun run() {
        val code = "#include<stdio.h>\n" +
                "int main(){\n" +
                "\tint a, b;\n" +
                "\tscanf(\"%d %d\", &a, &b);\n" +
                "\tprintf(\"%d\", a+b);\n" +
                "\treturn 0;\n" +
                "}"

        assertThat(judgeService.run(code, "C", "1 2", "3")).contains("CORRECT")
    }
}