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
	        'unit': {
	            sh "echo 'run unit tests for the project'"
	        },
	        'integration': {
	            sh "echo 'run integration tests for the project'"
	        }
        }
      	stage ('Deploy') {
            sh "echo 'shell scripts to deploy to server...'"
      	}
    } catch (err) {
        currentBuild.result = 'FAILED'
        throw err
    }
}
