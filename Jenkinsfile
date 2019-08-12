pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh './build-project.sh'
      }
    }

    stage('test') {
      steps {
        sh './test-project.sh'
      }
    }

    stage('deploy') {
        steps {
            sh 'echo deploy'
        }
    }
  }
}