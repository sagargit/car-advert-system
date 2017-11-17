node {
    try {

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
        if(env.BRANCH_NAME == 'master'){
           stage ('Deploy') {
                       sh "echo 'shell scripts to deploy to server...'"
                       sh "sbt dist"
                 	}
        }

    } catch (err) {
        currentBuild.result = 'FAILED'
        throw err
    }
}
