node {
  try{
        def workspace = pwd()
        def exists = fileExists 'ApiGlobal.scala'
        stage ('Build') {
                sh "ls -l"
                deleteDir()
                checkout scm
                sh "echo 'Inside build'"
                if(env.BRANCH_NAME == 'master'){
                    sh "echo 'On branch master'"
                    sh "cd app/"
                    sh "ls -l"
                    sh "echo '${workspace}'"
                     sh "echo '${exists}'"
                    if (exists) {
                        sh "echo 'running pid exists'"
                        sh "./stop.sh"
                        sh "cd .."
                    } else {
                        sh "cd .."
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
