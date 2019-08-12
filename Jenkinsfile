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
        sh 'pwd'
        sh 'ls'
        sh './gradlew clean test'
      }
    }

    stage('deploy') {
        environment {
            CONTAINER_REGISTRY_USERNAME = credentials('CONTAINER_REGISTRY_USERNAME')
            CONTAINER_REGISTRY_PASSWORD = credentials('CONTAINER_REGISTRY_PASSWORD')
        }
        steps {
            sh './gradlew clean pushDockerImage'
        }
    }
  }
}