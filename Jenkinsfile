pipeline {
        agent {
            node {
                label 'my-defined-label'
                customWorkspace '/home/prod-user/car-advert-system'
            }
        }

        stage ('Clean') {
            steps{
                sh "ls -l"
                sh "cd car-advert-system"
                sh "sbt clean"
            }

        }
        stage ('Compile') {
            steps{
                sh "sbt compile"
            }

        }
        stage ('Tests') {
	        parallel 'static': {
	            sh "echo 'shell scripts to run static tests...'"
	        },
	        'unit': {
	            sh "echo 'shell scripts to run unit tests...'"
	        },
	        'integration': {
	            sh "echo 'shell scripts to run integration tests...'"
	        }
        }
      	stage ('Deploy') {
            sh "echo 'shell scripts to deploy to server...'"
      	}
}
