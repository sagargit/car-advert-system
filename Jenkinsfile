node {
  try{
        def workspace = pwd()
        stage ('Build') {
                sh "ls -l"
                deleteDir()
                checkout scm
                sh "echo 'Inside build'"

                sh "echo 'The workspace is: ${workspace}'"
                sh "echo 'The workspace is: ${env.WORKSPACE}'"
                if(env.BRANCH_NAME == 'master'){
                    sh "echo 'On branch master'"
                    if (fileExists("/target/universal/RUNNING_PID")) {
                        sh "echo 'running pid exists'"
                        sh "./stop.sh"
                    }
                }
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
