pipeline {
  agent any

  stages {
    stage('Prepare Artifacts') {
       steps {
       sh '''
         zip -r ../jenkins-1.zip *
       '''
       }
    }
    stage('Upload Artifacts') {
      steps {
       sh '''
       curl -v -u admin:kavya --upload-file /var/lib/jenkins/workspace/jenkins-1.zip http://172.31.7.43:8081/repository/jenkins-1/jenkins-1.zip
       '''
      }
    }
  }
}
