pipeline {
    agent any

    stages {
        stage('Checkout') { 
            steps{
                git url: 'https://github.com/dropTabl3/jgsu-spring-petclinic.git', branch: 'main'
            }    
        }
        stage('Build') {
            steps {
                bat "mvn clean package"
            }

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
