pipeline {
    agent any

    tools {
        maven 'Maven'
        allure 'Allure'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            allure(
                results: [[path: 'target/allure-results']]
            )
        }
    }
}
