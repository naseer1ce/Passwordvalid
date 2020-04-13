node {
        stage('Build') {
            steps {
                sh 'docker build -t demopass:latest .'
                sh 'docker tag demopass:latest naseerce1/demopass:latest'
            }
        }
                stage ('Push'){
                  steps {
                     withCredentials([string(credentialsId: 'Dockerpwd', variable: 'dockerpwd')])
                           sh "docker login -u naseerce1 -p ${dockerpwd}"
                           sh 'docker push naseerce1/nodejs'
                        }
                }
        stage('Run') {
            steps {
                sshagent(['deployinstance']) {
                sh "scp -o StrictHostKeyChecking=no Deployment.yml services.yml ec2-user@172.31.24.213:/home/ec2-user/"
            script{
	            try{
	                sh "ssh ec2-user@172.31.24.213 kubectl create -f ."
	            }catch(error){
	                sh "ssh ec2-user@172.31.24.213 kubectl apply -f ."
            }
        }
    }
  }
}
}

