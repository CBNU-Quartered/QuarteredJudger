package judger.judge

data class RunResult( val cpuTime: Long,  val realTime: Long,  val memeory: Long,
                 val signal: Int,  val exitCode: Int,  val error: Int,  val result: Int)