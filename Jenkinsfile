pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh './gradlew clean build'
      }
    }

    stage('test') {
      steps {
        sh './gradlew clean test'
      }
    }

    stage('deploy') {
        environment {
            CONTAINER_REGISTRY_USERNAME = credentials('CONTAINER_REGISTRY_USERNAME')
        }
        steps {
            sh 'echo $CONTAINER_REGISTRY_USERNAME'
            sh './gradlew clean pushDockerImage'
        }
    }
  }
}