node {
	stage ('SCM checkout'){
		git 'https://github.com/naseer1ce/Passwordvalid'
}

	stage ('Maven Build') {
          
                sh 'mvn clean install'
            
            }
        stage('Docker Build') {

                sh 'docker build -t demopass:latest .'
                sh 'docker tag demopass:latest naseerce1/demopass:latest'

        }
                stage ('DockerHub Push'){
                           sh "docker login -u naseerce1 -p Naseerider@ca1i"
                           sh 'docker push naseerce1/demopass'

                }
        stage('Deploy in K8s') {

                sshagent(['deployinstance']) {
                sh "scp -o StrictHostKeyChecking=no Deployment.yml services.yml ec2-user@172.31.6.208:/home/ec2-user/"
            script{
	            try{
	                sh "ssh ec2-user@172.31.6.208 kubectl create -f ."
	            }catch(error){
	                sh "ssh ec2-user@172.31.6.208 kubectl apply -f ."
            }
        }
    }
  }
}
