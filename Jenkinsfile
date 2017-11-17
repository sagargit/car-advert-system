node {
  try{
        def workspace = pwd()
        def exists = fileExists 'target/'
        stage ('Build') {
                sh "ls -l"
                deleteDir()
                checkout scm
                sh "echo 'Inside build'"
                if(env.BRANCH_NAME == 'master'){
                    sh "echo 'On branch master'"
                    sh "echo '${workspace}'"
                    sh "echo '${exists}'"
                    dir ('target/universal/') {
                        sh "ls -l"
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
