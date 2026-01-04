pipeline {
    agent any

    tools {
        maven 'Maven'
        allure 'Allure'
    }

  environment {
        ALLURE_RESULTS = 'target/allure-results'
        ALLURE_REPORT  = 'target/allure-report'
}
    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                 bat '''
                 mvn clean test
                  '''
            }
        }

        stage('Generate Allure Report') {
            steps {
                 bat '''
                 allure generate %ALLURE_RESULTS% --clean -o %ALLURE_REPORT%
                 '''
            }
        }
    }

    post {
        always {
          echo 'Archiving test artifacts...'

                    archiveArtifacts artifacts: '**/target/screenshots/*.png', allowEmptyArchive: true
                    archiveArtifacts artifacts: '**/target/allure-results/**', allowEmptyArchive: true

            allure(
            includeProperties: false,
            jdk: '',
                results: [[path: 'target/allure-results']]
            )
        }
        failure {
                    echo 'Pipeline failed ❌'
                }

                success {
                    echo 'Pipeline succeeded ✅'
                }
    }
}
}