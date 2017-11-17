node {
  try{
        def workspace = pwd()
        def exists = fileExists 'target/'
        stage ('Build') {
                        if(env.BRANCH_NAME == 'master'){
                            sh "echo 'On branch master'"
                            sh "echo '${workspace}'"
                            sh "echo '${exists}'"
                            if(exists){
                                dir ('target/universal') {
                                    sh "kill 'cat save_pid.txt'"
                                }
                            }
                        }
                sh "ls -l"
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
