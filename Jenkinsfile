def stopRunningProcess(String branch) {
  if(branch == 'master'){
    if (fileExists("target/universal/RUNNING_PID")) {
        sh "./stop.sh"
    }
  }
}

node {

  try{
        stage ('Build') {
             stopRunningProcess(env.BRANCH_NAME)
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
        if(env.BRANCH_NAME == 'master'){
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
