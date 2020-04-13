node {

	stage ('Maven Build') {
          
                sh 'mvn install'
            
            }
        stage('Build') {

                sh 'docker build -t demopass:latest .'
                sh 'docker tag demopass:latest naseerce1/demopass:latest'

        }
                stage ('Push'){
                           sh "docker login -u naseerce1 -p Naseerider@ca1i"
                           sh 'docker push naseerce1/demopass'

                }
        stage('Run') {

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
