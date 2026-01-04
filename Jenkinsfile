pipeline {
    agent any

    parameters {
        choice( //Buradaki yapı, hangi testin çalışacağının seçilmesine olanak sağlar
             name: 'TEST_TAG',
             choices: ['@smoke','@regression', '@login'],
             description: 'Which test tag to run?'
        )
        booleanParam(
            name: 'RUN_UI_TESTS',
            defaultValue: true,
            description: 'UI testleri çalışsın mı?'
        )
    }
     triggers {
                cron('H 2 * * 1-5') //Zaman ayarı eklenmiştir.
            }

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
            git branch :'main',
                url: 'https://github.com/ICanAkin/selenium-cucumber-allure-jenkins'
            }
        }

        stage('Build') {
            steps {
            echo "Build alınıyor..."
           }
        }

        stage('UI Tests'){
            when {
            expression {params.RUN_UI_TESTS}
            }
            steps{
                echo "UI testleri ${params.ENV} ortamında çalışıyor"
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

        success{
            emailext(
            subject: "✅ UI Tests SUCCESS - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: """
            <p>UI tests completed successfully.</p>
                            <p><b>Job:</b> ${env.JOB_NAME}</p>
                            <p><b>Build:</b> ${env.BUILD_NUMBER}</p>
                            <p><b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                """,
            mimeType: 'text/html',
            to: 'qa-team@company.com'
            )
        }

        failure {
                    emailext(
                    subject: "❌ UI Tests FAILED - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                                body: """
                                    <p><b>UI tests failed.</b></p>
                                    <p>Please check logs and Allure report.</p>
                                    <p><b>Job:</b> ${env.JOB_NAME}</p>
                                    <p><b>Build:</b> ${env.BUILD_NUMBER}</p>
                                    <p><b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                                """,
                                mimeType: 'text/html',
                                to: 'qa-team@company.com'
                                )
                }
    }
}
