node {

    def runningPidPath = "target/universal/RUNNING_PID"

    def file = new File(runningPidPath)

    try {
        if(env.BRANCH_NAME == 'master' && file.exists()){
            stage ('Kill Nohup'){
                    sh "./stop.sh"
            }
        }
        stage ('Build') {
             deleteDir()
             checkout scm
        }
        stage ('Clean') {
        	sh "sbt clean"
        }
        stage ('Compile') {
        	sh "sbt compile"
        }
        stage ('Tests') {
	        sh "echo 'scripts to test project...'"
        }
        if(env.BRANCH_NAME == 'masterr'){
           stage ('Deploy') {
                       sh "echo 'shell scripts to deploy to server....'"
                       sh "./start.sh"
                 	}
        }

    } catch (err) {
        currentBuild.result = 'FAILED'
        throw err
    }
}
