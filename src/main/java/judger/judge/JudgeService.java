package judger.judge;

import judger.judge.util.ShellCommandUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class JudgeService {
    private static final Logger logger = LoggerFactory.getLogger(JudgeService.class);

    private static final String TESTER_DIR = "/Users/hyogeon/Downloads/JudgerProject/Desktop/tester";
    private static final String DOCKER_DIR = "/home";

    private static final String LOCAL_INIT_COMMAND = "sh /Users/hyogeon/Downloads/JudgerProject/Desktop/tester/init.sh";

    private static final String DOCKER_C_COMPILE_COMMAND = "docker run --rm -v " + TESTER_DIR + ":" + DOCKER_DIR + ' '
            + "gcc gcc " + DOCKER_DIR + "/test.c -o " + DOCKER_DIR + "/test.o  -O2 -Wall -lm -static -std=c99 -DONLINE_JUDGE -DBOJ";

    private static final String DOCKER_RUN_LIBJUDGE_COMMAND = "docker run --rm -v " + TESTER_DIR + ":" + DOCKER_DIR + ' '
            + "gcc " + DOCKER_DIR + "/libjudger.so " +
            "--max_cpu_time=1000 --max_real_time=2000 " +
            "--max_memory=536870912 --max_process_number=200 --max_output_size=16384 " +
            "--exe_path=\"" + DOCKER_DIR + "/test.o\" " +
            "--input_path=\"" + DOCKER_DIR + "/input.txt\" " +
            "--output_path=\"" + DOCKER_DIR + "/output.txt\" " +
            "--error_path=\"" + DOCKER_DIR + "/error.txt\" " +
            "--uid=0 --gid=0 --seccomp_rule_name=\"c_cpp\"";


    public String run(String source, String language, String input, String answer) throws IOException {
        createInputFile(input);

        ShellCommandUtil.execCommand(LOCAL_INIT_COMMAND);
        createSourceFile(source);
        ShellCommandUtil.execCommand(DOCKER_C_COMPILE_COMMAND);
        ShellCommandUtil.execCommand(DOCKER_RUN_LIBJUDGE_COMMAND);

        return checkAnswer(answer);
    }

    private void createInputFile(String input) throws IOException {
        FileWriter fileWriter = new FileWriter(TESTER_DIR + "/input.txt");
        fileWriter.write(input);
        fileWriter.close();
    }

    private void createSourceFile(String source) throws IOException {
        String fileName = "test.c";
        logger.info(String.valueOf(new File(TESTER_DIR).exists()));
        FileWriter fileWriter = new FileWriter(TESTER_DIR + "/" + fileName);
        fileWriter.write(source);
        fileWriter.close();
    }

    private String checkAnswer(String answer) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(TESTER_DIR + "/output.txt"));
        String output = "";
        String temp;
        while ((temp = br.readLine()) != null) output += temp;

        if (output.equals(answer)) {
            logger.info("CORRECT ANSWER !!");
            return "CORRECT ANSWER !!";
        }
        return "WRONG";
    }
}