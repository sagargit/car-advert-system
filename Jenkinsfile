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
                       sh "cd target/universal/"
                       sh "unzip car-advert-system-1.0.0.zip -d car-advert-system-1.0.0"
                       sh "cd car-advert-system-1.0.0"
                       sh "bin/car-advert-system -Dhttp.port=9005"
                       sh "echo 'car advert system started successfully...'"
                 	}
        }

    } catch (err) {
        currentBuild.result = 'FAILED'
        throw err
    }
}
